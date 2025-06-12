package com.diferent.springsandbox.model.api.request;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;

    private String email;

    private String password;

    private String role;

}
