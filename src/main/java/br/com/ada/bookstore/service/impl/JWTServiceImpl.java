package br.com.ada.bookstore.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl {
	
	private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	//private static SecretKey secretKeyRefresh = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
//	public String generateRefreshToken(String username) {
//		return generateTokenBase(username, secretKeyRefresh, 60l);
//	}

	public String generateToken(String username) {
		return generateTokenBase(username, secretKey, 30l);
	}
	
	private String generateTokenBase(String username, SecretKey key, Long minutosExpiracao) {
		final LocalDateTime dataAtual = LocalDateTime.now(); // Data atual.
		final LocalDateTime dataExpiracao = dataAtual.plusMinutes(minutosExpiracao); // Data de expiração.
		
		return Jwts.builder()
			.setIssuedAt(new Date(dataAtual.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) // Data que foi gerada o token.
			.setExpiration(new Date(dataExpiracao.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) // Data de expiração do token.
			.setSubject(username) // Username como subject
			.signWith(key) // Chave secreta
			.compact();
	}
	
	public Boolean validToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.isSigned(token);
	}
	
//	public Boolean validRefreshToken(String refreshToken) {
//		return Jwts.parserBuilder()
//			.setSigningKey(secretKeyRefresh)
//			.build()
//			.isSigned(refreshToken);
//	}
	
	public String getUsernameByToken(String token) {
		return getUsernameByTokenBase(token,secretKey);
	}
	
//	public String getUsernameByRefreshToken(String refreshToken) {
//		return getUsernameByTokenBase(refreshToken,secretKeyRefresh);
//	}
	
	private String getUsernameByTokenBase(String token, SecretKey key) {
		return Jwts.parserBuilder()
					.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

}
