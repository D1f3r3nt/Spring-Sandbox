package com.diferent.springsandbox.web;

import com.diferent.springsandbox.domain.AuthService;
import com.diferent.springsandbox.model.api.request.LogInRequest;
import com.diferent.springsandbox.model.api.request.SignUpRequest;
import com.diferent.springsandbox.model.api.response.LogInResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<LogInResponse> login(@RequestBody LogInRequest request) {

        final LogInResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignUpRequest request) {

        authService.signUpUser(request);

        return ResponseEntity.ok().build();
    }

}
