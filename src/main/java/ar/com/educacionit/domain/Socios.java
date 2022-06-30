package ar.com.educacionit.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name = "socios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Socios {

	@Id
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="email")
	private String email;
	@Column(name="fecha_alta")
	private Date fechaAlta;
	@Column(name="direccion")
	private String direccion;	
	@ManyToOne
	@JoinColumn(name="paises_id", referencedColumnName = "id")
	private Paises pais;//nacionalidad
//	private Users user;
	public Socios(Long id) {
		this.id = id;
	}
	
}
