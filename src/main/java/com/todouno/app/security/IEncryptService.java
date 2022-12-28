package com.todouno.app.security;

public interface IEncryptService {

	String encrypt(String value);
	
	String decrypt(String value);
	
}
