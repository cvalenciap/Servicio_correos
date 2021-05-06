package pe.com.sedmail.core.entity;
import java.io.Serializable;

import pe.com.sedmail.core.entity.base.Base;

public class Parametro extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer nsParametro;
	private String deParametro;
	private String deObservacion;
	private Integer inProtegido;
	
	public Integer getNsParametro() {
		return nsParametro;
	}
	public void setNsParametro(Integer nsParametro) {
		this.nsParametro = nsParametro;
	}
	public String getDeParametro() {
		return deParametro;
	}
	public void setDeParametro(String deParametro) {
		this.deParametro = deParametro;
	}
	public String getDeObservacion() {
		return deObservacion;
	}
	public void setDeObservacion(String deObservacion) {
		this.deObservacion = deObservacion;
	}
	public Integer getInProtegido() {
		return inProtegido;
	}
	public void setInProtegido(Integer inProtegido) {
		this.inProtegido = inProtegido;
	}
}
