package ar.com.educacionit.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.educacionit.dto.JwtDto;
import ar.com.educacionit.dto.LoginUsuarioDto;
import ar.com.educacionit.secutiry.JwtProvider;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@ApiOperation(value="Recurso de auth")
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(
			@Valid @RequestBody LoginUsuarioDto loginDto
			) {
		
		Authentication authentication = this.authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginDto.getNombreUsuario(), loginDto.getPassword())
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
				
		JwtDto jwtDto = new JwtDto(jwt);
		
		return new ResponseEntity<JwtDto>(jwtDto,HttpStatus.OK);
	}
}
