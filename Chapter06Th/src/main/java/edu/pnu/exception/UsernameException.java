package edu.pnu.exception;

public class UsernameException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public UsernameException(String message) {
		super(message);
	}
}
