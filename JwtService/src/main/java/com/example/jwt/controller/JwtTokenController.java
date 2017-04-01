package com.example.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.jwt.exception.TokenException;
import com.example.jwt.request.TokenGenerationRequest;
import com.example.jwt.request.TokenInformationRequest;
import com.example.jwt.response.TokenInfoResponse;
import com.example.jwt.response.TokenResponse;
import com.example.jwt.service.JwtTokenService;


@Controller
@Component
@EnableWebMvc

/**
 * JwtTokenController for JWT Services.
 *
 */
public class JwtTokenController {
	
	private static final Logger logger = Logger.getLogger(JwtTokenController.class);
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	/**
	 * generateToken to generate JWT token.
	 * @param tokenRequest
	 * @param request
	 * @param response
	 * @return TokenResponse
	 */
	@RequestMapping(value = "/token/generateToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TokenResponse generateToken(@RequestBody TokenGenerationRequest tokenGenerationRequest , HttpServletRequest request, HttpServletResponse response) {	
	   TokenResponse tokenResponse = null;
       logger.info("Inside getJwtToken controller");
       try{   	   
    	   tokenResponse = new TokenResponse();   	   
    	   tokenResponse = jwtTokenService.generateTokenService(tokenGenerationRequest);
       }catch(TokenException e){
    	   logger.error(e.getMessage());
    	   throw e;
       } 
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return tokenResponse;
    }
	
	/**
	 * getTokenInfo to get JWT token information.
	 * @param tokenRequest
	 * @param request
	 * @param response
	 * @return TokenInfoResponse
	 */
	@RequestMapping(value = "/token/getTokenInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TokenInfoResponse getTokenInfo(@RequestBody TokenInformationRequest tokenInformationRequest , HttpServletRequest request, HttpServletResponse response) {
	   TokenInfoResponse tokenInfoResponse = null;
       logger.info("Inside getTokenInfo controller");
       try{   	   
    	   tokenInfoResponse = new TokenInfoResponse();   	   
    	   tokenInfoResponse = jwtTokenService.getTokenInformationService(tokenInformationRequest);
       }catch(TokenException e){
    	   logger.error(e.getMessage());
    	   throw e;
       } 
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return tokenInfoResponse;
    }
	
	/**
	 * refreshToken to refresh last generated JWT token.
	 * @param tokenRequest
	 * @param request
	 * @param response
	 * @return TokenInfoResponse
	 */
	@RequestMapping(value = "/token/refreshToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody TokenResponse refreshToken(@RequestBody TokenInformationRequest tokenInformationRequest , HttpServletRequest request, HttpServletResponse response) {
	   TokenResponse tokenResponse = null;
       logger.info("Inside refreshToken controller");
       try{   	   
    	   tokenResponse = new TokenResponse();   	   
    	   tokenResponse = jwtTokenService.refreshTokenService(tokenInformationRequest);
       }catch(TokenException e){
    	   logger.error(e.getMessage());
    	   throw e;
       } 
       catch(Exception e){
    	   logger.error(e.getMessage());
       }     
       return tokenResponse;
    }
}
