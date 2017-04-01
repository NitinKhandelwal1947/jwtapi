package com.example.jwt.response;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 
 * TokenInfoResponse
 *
 */
public class TokenInfoResponse {
	
	private String id;
	private String subject;
	private String issuer;
	private long issueAt;
	private long expiration;
	private HashMap<String, String> extraParamMap;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public long getIssueAt() {
		return issueAt;
	}
	public void setIssueAt(long issueAt) {
		this.issueAt = issueAt;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
	public HashMap<String, String> getExtraParamMap() {
		return extraParamMap;
	}
	public void setExtraParamMap(HashMap<String, String> extraParamMap) {
		this.extraParamMap = extraParamMap;
	}
	

}
