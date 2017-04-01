package com.example.jwt.constants;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * URIConstants class for all constant variable
 *
 */
public class URIConstants {
	
	private static final String SHAREDSECRET = "caf6673dc6e2236cee232ecdf53bda7e";
	private static final String SUBJECT = "someone@someone.com";
	private static final String ISSUER = "someone";
	private static final long EXPIRATION = 60000; // in miliSecond
	private static final Set<String> JWTKEY = new HashSet<String>();
	
	public static String getSharedsecret() {
		return SHAREDSECRET;
	}
	public static String getSubject() {
		return SUBJECT;
	}
	public static String getIssuer() {
		return ISSUER;
	}
	public static long getExpiration() {
		return EXPIRATION;
	}
	
	public static Set<String> getJwtKey() {
		JWTKEY.add("jti");
		JWTKEY.add("iat");
		JWTKEY.add("sub");
		JWTKEY.add("iss");
		JWTKEY.add("exp");
		return JWTKEY;
	}
}

