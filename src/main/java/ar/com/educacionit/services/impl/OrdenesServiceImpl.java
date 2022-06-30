package ar.com.educacionit.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.educacionit.domain.Ordenes;
import ar.com.educacionit.repository.OrdenesRepository;
import ar.com.educacionit.services.OrdenService;

@Service
public class OrdenesServiceImpl implements OrdenService{

	@Autowired
	private OrdenesRepository repository;
	
	@Override
	public Optional<Ordenes> getById(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public void save(Ordenes ordenDb) {
		this.repository.save(ordenDb);
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public void update(Ordenes orden) {
		this.repository.save(orden);
	}
	
	@Override
	public List<Ordenes> findAll() {
		return this.repository.findAll();
	}
}
