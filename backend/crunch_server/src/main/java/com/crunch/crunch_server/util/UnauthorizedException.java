package com.crunch.crunch_server.util;

public class UnauthorizedException extends RuntimeException{
	private static final long serialVersionUID = -2238030302650813813L;
	
	public UnauthorizedException() {
		super("Invalid authorization info.\nplease login again.");
	}
}
