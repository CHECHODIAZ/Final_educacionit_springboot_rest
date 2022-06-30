package ar.com.educacionit.secutiry;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authenticatipon) { 
		
		UserDetails usuario = (UserDetails)authenticatipon.getPrincipal();
		List<String> roles = usuario.getAuthorities().stream()
			.map(x -> "ROLE_"+x.getAuthority())
			.collect(Collectors.toList());
		
		//libreria jjwt
		Claims claims =  Jwts.claims().setSubject(usuario.getUsername());
		claims.put("roles", roles);
		
		Date ahora = new Date();
		return Jwts.builder()
			.setIssuedAt(ahora)
			.setExpiration(new Date(ahora.getTime() +  expiration * 1000))
			.signWith(SignatureAlgorithm.HS512, secret)
			.setClaims(claims)
			.compact();
	}
	
	public boolean validateToken(String token) {
		Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token);
		return true;
	}

	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser()
		.setSigningKey(this.secret)
		.parseClaimsJws(token)
		.getBody()
		.getSubject();
	}
}
