package com.app.dca.exception;

public class UnknownUserException extends Exception{
	public UnknownUserException() {
	}
	public UnknownUserException(String message) {
		super(message);
	}
}
