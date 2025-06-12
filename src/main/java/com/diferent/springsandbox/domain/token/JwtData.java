package com.diferent.springsandbox.domain.token;

import com.diferent.springsandbox.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class JwtData {

	private Long id;

	private String username;

	private UserRole role;
}
