package pe.com.sedmail.core.entity;

import java.io.Serializable;

import pe.com.sedmail.core.entity.base.Base;


@SuppressWarnings("serial")
public class Proceso extends Base implements Serializable {
	
	private Integer idProceso;
	private String deProceso;
	
	public Proceso(){
		
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public String getDeProceso() {
		return deProceso;
	}

	public void setDeProceso(String deProceso) {
		this.deProceso = deProceso;
	}
}
