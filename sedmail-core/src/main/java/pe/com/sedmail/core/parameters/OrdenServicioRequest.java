package pe.com.sedmail.core.parameters;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrdenServicioRequest implements Serializable {

	private int item;
	private int identificador;
	private long idOrdenServicio;
	private long nuOrdenServicio;
	private int idTipoPunto;
	private String identificadorRuta;
	private String identificadorPuntoDestino;

	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public long getIdOrdenServicio() {
		return idOrdenServicio;
	}
	public void setIdOrdenServicio(long idOrdenServicio) {
		this.idOrdenServicio = idOrdenServicio;
	}
	public long getNuOrdenServicio() {
		return nuOrdenServicio;
	}
	public void setNuOrdenServicio(long nuOrdenServicio) {
		this.nuOrdenServicio = nuOrdenServicio;
	}
	public String getIdentificadorRuta() {
		return identificadorRuta;
	}
	public void setIdentificadorRuta(String identificadorRuta) {
		this.identificadorRuta = identificadorRuta;
	}
	public String getIdentificadorPuntoDestino() {
		return identificadorPuntoDestino;
	}
	public void setIdentificadorPuntoDestino(String identificadorPuntoDestino) {
		this.identificadorPuntoDestino = identificadorPuntoDestino;
	}
	public int getIdTipoPunto() {
		return idTipoPunto;
	}
	public void setIdTipoPunto(int idTipoPunto) {
		this.idTipoPunto = idTipoPunto;
	}
	
	
	
	
	
}
