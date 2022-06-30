package ar.com.educacionit.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import ar.com.educacionit.enums.EstadoOrdenEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//O.R.M > JPA > hibernate
//modelo realacionl > mysql 
//
@Entity
@Table(name="ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Ordenes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name="socios_id", referencedColumnName = "id")
	private Socios socio;
	
	@ManyToOne
	@JoinColumn(name="estados_ordenes_id", referencedColumnName = "id")
	private EstadosOrdenes estado;
	
	@Column(name="monto_total", nullable = false)
	private Double montoTotal;
	
	@OneToOne
	@JoinColumn(name="cupones_id", referencedColumnName = "id")
	private Cupones cupon;
	
	
	
	public Ordenes(Date fechaCreacion, Socios socio, EstadosOrdenes estado, Double montoTotal, Cupones cupon) {
		
		this.fechaCreacion = fechaCreacion;
		this.socio = socio;
		this.estado = estado;
		this.montoTotal = montoTotal;
		this.cupon = cupon;
	}

	public boolean isEstadoFinal() {
		return this.getEstado().getEstadoFinal().equals(EstadoOrdenEnum.FINAL.getEstado());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Socios getSocio() {
		return socio;
	}

	public void setSocio(Socios socio) {
		this.socio = socio;
	}

	public EstadosOrdenes getEstado() {
		return estado;
	}

	public void setEstado(EstadosOrdenes estado) {
		this.estado = estado;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Cupones getCupon() {
		return cupon;
	}

	public void setCupon(Cupones cupon) {
		this.cupon = cupon;
	}
	
	
}
