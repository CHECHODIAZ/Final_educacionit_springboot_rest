package ar.com.educacionit.dto;

public class JwtDto {

	private String token;

	public JwtDto(String token) {	
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
}
