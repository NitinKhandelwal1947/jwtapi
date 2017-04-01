package com.example.jwt;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.example.jwt.constants.URIConstants;
import com.example.jwt.exception.TokenException;
import com.example.jwt.request.TokenGenerationRequest;
import com.example.jwt.response.TokenInfoResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class DecodeJWT {
	
	private static final Logger logger = Logger.getLogger(DecodeJWT.class);
	/**
	 * 
	 * @param jwt
	 * @return
	 */
	public TokenInfoResponse parseJWT(String jwt) throws Exception {
		logger.info("In parseJWT method...");
		TokenInfoResponse tokenInfoResponse = null;
		HashMap<String, String> extraParamMap = new HashMap<String, String>();
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(URIConstants.getSharedsecret()))
					.parseClaimsJws(jwt).getBody();		
			Set<String> JWtKeySet = URIConstants.getJwtKey();
			if(claims != null) {
				Iterator<Entry<String, Object>> it = claims.entrySet().iterator();
				while(it.hasNext()) {
					Map.Entry<String, Object> pair = (Map.Entry<String, Object>)it.next();
					if(JWtKeySet.contains(pair.getKey())) {
						continue;
					}
					else {
						extraParamMap.put(pair.getKey(), pair.getValue().toString());
					}
				}
			}
			tokenInfoResponse = new TokenInfoResponse();
			tokenInfoResponse.setId(claims.getId());
			tokenInfoResponse.setSubject(claims.getSubject());
			tokenInfoResponse.setIssuer(claims.getIssuer());
			tokenInfoResponse.setIssueAt(claims.getIssuedAt().getTime());
			tokenInfoResponse.setExpiration(claims.getExpiration().getTime());
			tokenInfoResponse.setExtraParamMap(extraParamMap);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new TokenException("Not a valid old token", 401, HttpStatus.UNAUTHORIZED);
		}
		return tokenInfoResponse;
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private  String getAbsolutePathOfFile(String fileName) 
	{
		File f = new File(fileName);
		return f.getAbsolutePath();
    }
	/**
	 * 
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public String refreshJWT(String jwt) throws Exception {
		logger.info("in refreshJWT method....");
		String refreshedJwt = null;
		FileInputStream file = null;
		TokenInfoResponse tokenInfoResponse = null;
		TokenGenerationRequest tokenGenerationRequest = new TokenGenerationRequest();
		Properties mainProperties;
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(URIConstants.getSharedsecret()))
					.parseClaimsJws(jwt).getBody();
			mainProperties = new Properties();
			String fileName = claims.getId() + ".properties";
			String path = getAbsolutePathOfFile(fileName);
			file = new FileInputStream(path);
			mainProperties.load(file);
			if (mainProperties.get(claims.getId()).equals(jwt)) {
				GenerateJWT generateJwt = new GenerateJWT();
				tokenInfoResponse = parseJWT(jwt);
				tokenGenerationRequest.setSubject(tokenInfoResponse.getSubject());
				tokenGenerationRequest.setIssuer(tokenInfoResponse.getIssuer());
				tokenGenerationRequest.setExpiry(tokenInfoResponse.getExpiration() - tokenInfoResponse.getIssueAt());
				tokenGenerationRequest.setExtraParamMap(tokenInfoResponse.getExtraParamMap());

				refreshedJwt = generateJwt.createJWT(tokenGenerationRequest);
				File f1 = new File(getAbsolutePathOfFile(claims.getId() + ".properties"));
				f1.delete();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new TokenException("Not a valid old token", 401, HttpStatus.UNAUTHORIZED);
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new TokenException("Refreshing token failed", 401, HttpStatus.UNAUTHORIZED);
			}
		}
		return refreshedJwt;
	}

}
