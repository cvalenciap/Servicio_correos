package pe.com.sedmail.core.entity;

import java.io.Serializable;

import pe.com.sedmail.core.entity.base.Base;

public class PlantillaCorreoUsuario extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
    private Integer idAsignacion;
    private Integer idPlantilla;
    
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
	public Integer getIdPlantilla() {
		return idPlantilla;
	}
	public void setIdPlantilla(Integer idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

}
