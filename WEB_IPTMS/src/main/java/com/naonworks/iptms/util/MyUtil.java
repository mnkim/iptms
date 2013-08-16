package com.naonworks.iptms.util;

public class MyUtil {
	public static String replaceNull(String msg) {
		if (msg==null) {
			return "";
		}
		else {
			return msg;
		}
	}
}
