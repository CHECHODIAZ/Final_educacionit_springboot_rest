package ar.com.educacionit.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenesDto {
	private Long id;
	@NotNull
	private Long socioId;
	@NotNull
    private Long estadoId;
	@NotNull
    private Double montoTotal;

    private Long cuponId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSocioId() {
		return socioId;
	}

	public void setSocioId(Long socioId) {
		this.socioId = socioId;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Long getCuponId() {
		return cuponId;
	}

	public void setCuponId(Long cuponId) {
		this.cuponId = cuponId;
	}
    
    
}
