package com.blinkurban.backend.utils;
import java.util.regex.*;
public class Regex{
	public static boolean email(String input){
		String reg = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		return check(reg, input, true);
	}
	
	private static boolean check(String regex, String input){
		return Pattern.compile(regex).matcher(input).find();
	}
	private static boolean check(String regex, String input, boolean ignoreCase){
		if (ignoreCase){
			return Pattern.compile(regex,Pattern.CASE_INSENSITIVE).matcher(input).find();
		}
		else{
			return check(regex, input);
		}
	}
}