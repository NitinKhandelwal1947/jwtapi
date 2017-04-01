package com.example.jwt.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.jwt.DecodeJWT;
import com.example.jwt.GenerateJWT;
import com.example.jwt.exception.TokenException;
import com.example.jwt.request.TokenGenerationRequest;
import com.example.jwt.request.TokenInformationRequest;
import com.example.jwt.response.TokenInfoResponse;
import com.example.jwt.response.TokenResponse;

@Component
public class JwtTokenService {
	
	private static final Logger logger = Logger.getLogger(JwtTokenService.class);
	/**
	 * 
	 * @param tokenGenerationRequest
	 * @param counter
	 * @return
	 */
	public TokenResponse generateTokenService(TokenGenerationRequest tokenGenerationRequest) {
		logger.info("In generateTokenService method...");
		String jwt = null;
		TokenResponse tokenResponse = null;
		try {
			tokenResponse = new TokenResponse();
			GenerateJWT generateJWT = new GenerateJWT();
			jwt = generateJWT.createJWT(tokenGenerationRequest);
			tokenResponse.setMessage(jwt);
		}
		catch(Exception e) {
			logger.error(e.getMessage());		
		}
		return tokenResponse;
	}
	/**
	 * 
	 * @param refreshTokenRequest
	 * @return
	 * @throws Exception
	 */
	public TokenResponse refreshTokenService(TokenInformationRequest refreshTokenRequest) throws Exception {
		logger.info("In refreshTokenService method...");
		String jwt = null;
		TokenResponse tokenResponse = null;
		DecodeJWT decodeJWT = null;
		try {
			tokenResponse = new TokenResponse();
			decodeJWT = new DecodeJWT();
			jwt = decodeJWT.refreshJWT(refreshTokenRequest.getOldJwtToken());
			tokenResponse.setMessage(jwt);			
		}
		catch(TokenException e){
	    	   logger.error(e.getMessage());
	    	   throw e;
	       } 
		catch(Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return tokenResponse;
	}
	/**
	 * 
	 * @param tokenInformationRequest
	 * @return
	 */
	public TokenInfoResponse getTokenInformationService(TokenInformationRequest tokenInformationRequest) throws Exception {
		logger.info("In getTokenInformationService method...");
		TokenInfoResponse tokenInfoResponse = null;
		DecodeJWT decodeJWT = null;
		try {
			tokenInfoResponse = new TokenInfoResponse();
			decodeJWT = new DecodeJWT();
			tokenInfoResponse = decodeJWT.parseJWT(tokenInformationRequest.getOldJwtToken());						
		}
		catch(TokenException e){
	    	   logger.error(e.getMessage());
	    	   throw e;
	       } 
		catch(Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return tokenInfoResponse;
	}

}
