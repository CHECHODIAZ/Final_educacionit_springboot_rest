package ar.com.educacionit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.educacionit.domain.Ordenes;

//aspectj
//proxy: dinamicos

@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Long>{
	
}
