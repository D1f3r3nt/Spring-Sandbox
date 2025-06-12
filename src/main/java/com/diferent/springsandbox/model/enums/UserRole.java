package com.diferent.springsandbox.model.enums;

public enum UserRole {
	ADMIN("admin"),
	USER("user");

	private String value;

	private UserRole(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	static public UserRole toUserRole(String value) {
		if (value.equalsIgnoreCase("admin")) {
			return ADMIN;
		} else {
			return USER;
		}
	}
}
