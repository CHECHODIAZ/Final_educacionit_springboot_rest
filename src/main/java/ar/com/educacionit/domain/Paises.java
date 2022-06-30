package ar.com.educacionit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*MAESTROS*/
@Entity
@Table(name="paises")
public class Paises {

	@Id
	private Long id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name="descripcion_corta")
	private String descripcionCorta;
	@Column(name="habilitada")
	private Long habilitada;
	
}
