package com.diferent.springsandbox.domain;

import com.diferent.springsandbox.domain.errors.ServiceException;
import com.diferent.springsandbox.domain.token.JwtData;
import com.diferent.springsandbox.domain.token.JwtService;
import com.diferent.springsandbox.model.api.request.LogInRequest;
import com.diferent.springsandbox.model.api.request.SignUpRequest;
import com.diferent.springsandbox.model.api.response.LogInResponse;
import com.diferent.springsandbox.model.dto.UserDto;
import com.diferent.springsandbox.model.enums.UserRole;
import com.diferent.springsandbox.repository.handlers.UserHandler;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    final UserHandler userHandler;
    final JwtService jwtService;

    public void signUpUser(SignUpRequest request) {
        if (!Objects.equals(request.getRole(), "admin") &&
            !Objects.equals(request.getRole(), "user")) {

            throw new ServiceException.Builder("ERR-00")
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withMessage("Just admin or user role available")
                .build();
        }

        UserDto userDto = UserDto.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(UserRole.toUserRole(request.getRole()))
                .build();

        userHandler.save(userDto);
    }

    public LogInResponse login(LogInRequest request) {
        UserDto userDto = UserDto.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .build();

        Pair<Long, UserRole> response = userHandler.getUserByCredentials(userDto);

        final String token = jwtService.generateToken(JwtData.builder()
                                     .id(response.a)
                                     .role(response.b)
                                     .username(request.getUsername())
                                     .build());

        return LogInResponse.builder().token(token).build();
    }

}
