package com.diferent.springsandbox.model.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class LogInResponse {
	private String token;
}
