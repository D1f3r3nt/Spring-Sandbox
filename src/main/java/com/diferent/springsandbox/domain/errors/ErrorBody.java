package com.diferent.springsandbox.domain.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorBody {

	private String code;
	private String message;
}
