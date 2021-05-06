package pe.com.sedmail.core.entity;

import java.io.Serializable;

import pe.com.sedmail.core.entity.base.Base;

public class DetalleGeneral extends Base implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idGeneral;
	private Integer idDetGeneral;
	private String desDetGeneral;
	private String desDetCorta;
	private String tipoDato;
	private Double longitudDato;
	private String descripcionEstado;
	private String valor;
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	public Integer getIdGeneral() {
		return idGeneral;
	}
	public void setIdGeneral(Integer idGeneral) {
		this.idGeneral = idGeneral;
	}
	public Integer getIdDetGeneral() {
		return idDetGeneral;
	}
	public void setIdDetGeneral(Integer idDetGeneral) {
		this.idDetGeneral = idDetGeneral;
	}
	public String getDesDetGeneral() {
		return desDetGeneral;
	}
	public void setDesDetGeneral(String desDetGeneral) {
		this.desDetGeneral = desDetGeneral;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public Double getLongitudDato() {
		return longitudDato;
	}
	public void setLongitudDato(Double longitudDato) {
		this.longitudDato = longitudDato;
	}
	public String getDesDetCorta() {
		return desDetCorta;
	}
	public void setDesDetCorta(String desDetCorta) {
		this.desDetCorta = desDetCorta;
	}
	
}
