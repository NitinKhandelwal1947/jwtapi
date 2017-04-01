package com.example.jwt.request;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 
 * TokenGenerationRequest
 *
 */
public class TokenGenerationRequest {

	
	private String subject;
	private String issuer;
	private long expiry;
	private HashMap<String, String> extraParamMap;


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public long getExpiry() {
		return expiry;
	}

	public void setExpiry(long expiry) {
		this.expiry = expiry;
	}

	public HashMap<String, String> getExtraParamMap() {
		return extraParamMap;
	}

	public void setExtraParamMap(HashMap<String, String> extraParamMap) {
		this.extraParamMap = extraParamMap;
	}
	
	
}
