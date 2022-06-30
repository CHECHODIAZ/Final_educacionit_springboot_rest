package ar.com.educacionit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "estados_ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstadosOrdenes {

	@Id
	private Long id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "descripcion_corta")
	private String descripcionCorta;
	@Column(name = "estado_final")
	private Long estadoFinal;
	public EstadosOrdenes(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public Long getEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(Long estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
	
	
	
}
