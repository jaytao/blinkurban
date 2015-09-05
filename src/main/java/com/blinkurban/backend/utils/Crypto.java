package com.blinkurban.backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto{
	public static byte[] SHA256(String input){
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new byte[]{0x00};
		
	}
}