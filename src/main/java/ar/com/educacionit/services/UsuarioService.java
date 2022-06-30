package ar.com.educacionit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.educacionit.domain.Users;
import ar.com.educacionit.repository.UsersRepository;

@Service
public class UsuarioService {

	@Autowired
	UsersRepository userRepository;
	
	public Optional<Users> getByNombreUsuario(String nombreUsuario) {
		return this.userRepository.findByUsername(nombreUsuario);
	}
	
	//save
	
	//delete
}
