package org.jsp.adminBus.exception;

public class LoginInvalidException extends RuntimeException {
	public LoginInvalidException(String message) {
		super(message);
	}
}
