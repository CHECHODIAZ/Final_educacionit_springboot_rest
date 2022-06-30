package ar.com.educacionit.enums;

public enum EstadoOrdenEnum {
	FINAL(1L)
	;
	
	private Long estado;
	
	private EstadoOrdenEnum(Long estado) {
		this.estado = estado;
	}
	
	public Long getEstado() {
		return this.estado;
	}
}
