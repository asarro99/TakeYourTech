package com.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
	private static final String ALGORITMO = "SHA-1";
	
	/*
	 * Metodo per generare lo SHA-1 della password
	 */
	public static String generatore(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITMO);
			md.update(password.getBytes("UTF-8"));
			return toHex(md.digest());
		}catch(UnsupportedEncodingException e) {
			return null;
		}catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/*
	 * Metodo per controllare se la password è corretta
	 */
	public static boolean autenticazione(String password, String hash) {
		return hash.equals(generatore(password));
	}
	
	private static String toHex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for(byte b: data) {
			String digit = Integer.toString(b & 0xFF, 16);
			if(digit.length() == 1) {
				sb.append("0");
			}
			sb.append(digit);
		}
		return sb.toString();
	}
	
}
