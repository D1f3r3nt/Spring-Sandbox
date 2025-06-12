package com.diferent.springsandbox.domain;

import com.diferent.springsandbox.model.api.request.LogInRequest;
import com.diferent.springsandbox.model.api.request.SignUpRequest;
import com.diferent.springsandbox.model.dto.UserDto;
import com.diferent.springsandbox.repository.handlers.UserHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    final UserHandler userHandler;

    public void signUpUser(SignUpRequest request) {
        UserDto userDto = UserDto.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userHandler.save(userDto);
    }

    public Long login(LogInRequest request) {
        UserDto userDto = UserDto.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .build();

        Long id = userHandler.getUserByCredentials(userDto);

        return id;
    }

}
