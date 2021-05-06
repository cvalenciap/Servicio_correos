package pe.com.sedmail.core.parameters;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SegmentoResponse implements Serializable {
	private long distancia;
	private long duracion;
	private String direccionNormalizada;
	private Long nsPuntoDestino;
	private String identificador;
	
	
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Long getNsPuntoDestino() {
		return nsPuntoDestino;
	}

	public void setNsPuntoDestino(Long nsPuntoDestino) {
		this.nsPuntoDestino = nsPuntoDestino;
	}

	public String getDireccionNormalizada() {
		return direccionNormalizada;
	}

	public void setDireccionNormalizada(String direccionNormalizada) {
		this.direccionNormalizada = direccionNormalizada;
	}

	public SegmentoResponse() {
		
	}

	public long getDistancia() {
		return distancia;
	}

	public void setDistancia(long distancia) {
		this.distancia = distancia;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long horario) {
		this.duracion = horario;
	}
	
	
	
}
