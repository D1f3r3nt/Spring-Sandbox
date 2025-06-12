package com.diferent.springsandbox.domain.utils;

import static com.diferent.springsandbox.model.enums.UserRole.ADMIN;

import com.diferent.springsandbox.domain.errors.ServiceException;
import com.diferent.springsandbox.domain.token.JwtData;
import com.diferent.springsandbox.domain.token.JwtService;
import com.diferent.springsandbox.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthFirewall {

	@Autowired
	private JwtService jwtService;

	public void checkToken(String token) {
		if (token == null) {
			throw new ServiceException.Builder("AUTH")
				.withHttpStatus(HttpStatus.BAD_REQUEST)
				.withMessage("Need a token")
				.build();
		}

		if (jwtService.isTokenExpired(token)) {
			throw new ServiceException.Builder("AUTH")
				.withHttpStatus(HttpStatus.UNAUTHORIZED)
				.withMessage("Invalid token")
				.build();
		}
	}

	public void justAdminUsers(String token) {
		checkToken(token);

		final JwtData data = jwtService.extractData(token);

		if (data.getRole() != ADMIN) {
			throw new ServiceException.Builder("AUTH")
				.withHttpStatus(HttpStatus.UNAUTHORIZED)
				.withMessage("You don't have permissions")
				.build();
		}
	}
}
