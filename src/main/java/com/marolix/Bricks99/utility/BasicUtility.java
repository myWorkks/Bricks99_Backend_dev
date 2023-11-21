package com.marolix.Bricks99.utility;

import org.springframework.stereotype.Component;

@Component(value="basicUtility")
public class BasicUtility {

	public String adjustCaseOfName(String... name) {

		String finalWords = "";

		for (String string : name) {
			// System.out.println("name is "+string);
			if (!string.isEmpty()) {
				// System.out.println(string +":is not empty");
				Character c = string.charAt(0);
				finalWords += c.toString().toUpperCase() + string.substring(1) + " ";
			}

		}

		return finalWords.trim();

	}
}
