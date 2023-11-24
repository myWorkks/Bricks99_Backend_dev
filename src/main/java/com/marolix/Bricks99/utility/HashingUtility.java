package com.marolix.Bricks99.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.marolix.Bricks99.exception.Bricks99Exception;

public class HashingUtility {
	public static String hashedString(String plainPassword) throws Bricks99Exception {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = messageDigest.digest(plainPassword.getBytes());

			// Convert the byte array to a hexadecimal string
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : hashBytes) {
				stringBuilder.append(String.format("%02x", b));
			}

			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new Bricks99Exception("Error while hashing password. " + e.getMessage());
		}
	}
}
