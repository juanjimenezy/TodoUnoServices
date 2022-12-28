package com.todouno.app.security;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xml.internal.security.utils.Base64;

@SuppressWarnings("restriction")
@Service
public class EncryptService implements IEncryptService {

	@Autowired
	private Environment environment;

	@Override
	public String encrypt(String value) {
		try {
			SecretKeySpec secretKeySpec = crearKey(getKey());
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] cadena = value.getBytes("UTF-8");
			byte[] encriptada = cipher.doFinal(cadena);
			String cadena_encriptada = Base64.encode(encriptada);
			return cadena_encriptada;
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String decrypt(String value) {
		try {
			SecretKeySpec secretKeySpec = crearKey(getKey());
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] cadena = Base64.decode(value);
			byte[] desencriptacioon = cipher.doFinal(cadena);
			String cadena_desencriptada = new String(desencriptacioon);
			return cadena_desencriptada;
		} catch (Exception e) {
			return "";
		}
	}

	public SecretKeySpec crearKey(String llave) {
		try {
			byte[] cadena = llave.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			cadena = md.digest(cadena);
			cadena = Arrays.copyOf(cadena, 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
			return secretKeySpec;
		} catch (Exception e) {
			return null;
		}
	}

	public String getKey() {
		return environment.getProperty("keySecutiry");
	}

}
