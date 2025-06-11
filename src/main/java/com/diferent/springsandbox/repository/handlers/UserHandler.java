package com.diferent.springsandbox.repository.handlers;

import com.diferent.springsandbox.domain.errors.ServiceException;
import com.diferent.springsandbox.model.dto.UserDto;
import com.diferent.springsandbox.model.entity.UserEntity;
import com.diferent.springsandbox.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserHandler {

    final UserRepository userRepository;

    public void save(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new ServiceException.Builder("ERR-1")
                    .withMessage("Duplicated ID")
                    .withHttpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }
}
