package com.example.jwt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.example.jwt.constants.URIConstants;
import com.example.jwt.request.TokenGenerationRequest;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;  

public class GenerateJWT {
	
	private static final Logger logger = Logger.getLogger(GenerateJWT.class);
	/**
	 * 
	 * @param tokenGenerationRequest 
	 * @return JWT token
	 */
	public String createJWT(TokenGenerationRequest tokenGenerationRequest) {
		logger.info("In createJWT method...");
		String jwt = "";
		OutputStream output = null;
		String sharedSecret = "";
		String subject = "";
		String issuer = "";
		Long expiry;
		long expDate;
		HashMap<String, String> extraParamMap = null;
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			sharedSecret = URIConstants.getSharedsecret();	
			expiry = tokenGenerationRequest.getExpiry();			
			subject = tokenGenerationRequest.getSubject() != null ? tokenGenerationRequest.getSubject() : URIConstants.getSubject();			
			issuer = tokenGenerationRequest.getIssuer() != null ? tokenGenerationRequest.getIssuer() : URIConstants.getIssuer();			
			expDate = expiry != 0 ? tokenGenerationRequest.getExpiry() : URIConstants.getExpiration();			
			long nowMillis = System.currentTimeMillis();			
			Date now = new Date(nowMillis);
			long expMillis = nowMillis + expDate;			
			Date exp = new Date(expMillis);
			String id = UUID.randomUUID().toString();
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sharedSecret);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
					.setExpiration(exp).signWith(signatureAlgorithm, signingKey);
			
			extraParamMap = tokenGenerationRequest.getExtraParamMap() != null ? tokenGenerationRequest.getExtraParamMap() : extraParamMap;
			if( extraParamMap != null ) {
				Iterator<Entry<String, String>> it = extraParamMap.entrySet().iterator();
				while(it.hasNext()) {
					Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
					builder.claim(pair.getKey(), pair.getValue());
				}
			}
			jwt = builder.compact();		
			Properties prop = new Properties();
			prop.setProperty(id, jwt);
			output = new FileOutputStream(id + ".properties");
			prop.store(output, "Some File");

		} catch (IOException io) {
			logger.error(io.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return jwt;
	}
}
