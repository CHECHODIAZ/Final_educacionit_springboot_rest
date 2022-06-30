package ar.com.educacionit.secutiry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import ar.com.educacionit.services.UserDetailSericeImpl;

public class JwtTokenFIlter extends OncePerRequestFilter {

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailSericeImpl userDetailSericeImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String token = getToken(request);
		
		if(token != null && jwtProvider.validateToken(token)) {
			String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
			UserDetails userDetails =  userDetailSericeImpl.loadUserByUsername(nombreUsuario);
			//cargar en el contexto de seguridad de spring los datos del user
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header == null || !header.startsWith("Bearer")) {
			return null;
		}
		return header.replace("Bearer ", "");
	}

}
