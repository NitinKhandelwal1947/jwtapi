package com.example.jwt.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 
 * TokenInformationRequest
 *
 */
public class TokenInformationRequest {

	private String oldJwtToken;

	public String getOldJwtToken() {
		return oldJwtToken;
	}

	public void setOldJwtToken(String oldJwtToken) {
		this.oldJwtToken = oldJwtToken;
	}
}
