package com.diferent.springsandbox.model.dto;

import com.diferent.springsandbox.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private UserRole role;
}


