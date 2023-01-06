package com.adtech.exception;

public class FileExistingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileExistingException(String message)
	{
		super(message);
	}
}
