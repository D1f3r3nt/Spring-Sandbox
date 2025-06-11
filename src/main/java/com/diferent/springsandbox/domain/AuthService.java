package com.diferent.springsandbox.domain;

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

}
