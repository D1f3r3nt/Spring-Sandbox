package com.diferent.springsandbox.domain.token;

import com.diferent.springsandbox.model.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

	public static final String USERNAME = "username";
	public static final String ROLE = "role";

	@Value("${security.jwt.secret-key}")
	private String secretKey;

	@Value("${security.jwt.expiration-time}")
	private long jwtExpiration;

	public JwtData extractData(String token) {
		final Claims claims = extractAllClaims(token);

		return JwtData.builder()
			.id(Long.parseLong(claims.getSubject()))
			.username(claims.get(USERNAME).toString())
			.role(UserRole.toUserRole(claims.get(ROLE).toString()))
			.build();
	}

	public String generateToken(JwtData data) {
		Map<String, Object> extras = Map.of(USERNAME, data.getUsername(), ROLE, data.getRole());
		return buildToken(extras, data.getId(), jwtExpiration);
	}

	public long getExpirationTime() {
		return jwtExpiration;
	}

	public boolean isTokenExpired(String token) {
		final Claims claims = extractAllClaims(token);
		return claims.getExpiration().before(new Date());
	}

	private String buildToken(
		Map<String, Object> extraClaims,
		Long id,
		long expiration
	) {
		return Jwts
			.builder()
			.setClaims(extraClaims)
			.setSubject(id.toString())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(getSignInKey(), SignatureAlgorithm.HS256)
			.compact();
	}

	private Claims extractAllClaims(String bearerToken) {
		final String token = bearerToken.substring(7);
		return Jwts
			.parserBuilder()
			.setSigningKey(getSignInKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
