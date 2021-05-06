package pe.com.sedmail.core.entity;

import java.io.Serializable;

import pe.com.sedmail.core.entity.base.Base;

public class PlantillaCorreo extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idPlantilla;
	private String nombrePlantilla;
	private String txtAsuntoPlantilla;
	private String txtHtmlOriginal;
	private String txtHtmlModificado;
//	private Integer idModulo;
//    private String deModulo;
	
//	public Integer getIdModulo() {
//		return idModulo;
//	}
//	public void setIdModulo(Integer idModulo) {
//		this.idModulo = idModulo;
//	}
//	public String getDeModulo() {
//		return deModulo;
//	}
//	public void setDeModulo(String deModulo) {
//		this.deModulo = deModulo;
//	}
	public Integer getIdPlantilla() {
		return idPlantilla;
	}
	public void setIdPlantilla(Integer idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	public String getNombrePlantilla() {
		return nombrePlantilla;
	}
	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}
	public String getTxtAsuntoPlantilla() {
		return txtAsuntoPlantilla;
	}
	public void setTxtAsuntoPlantilla(String txtAsuntoPlantilla) {
		this.txtAsuntoPlantilla = txtAsuntoPlantilla;
	}
	public String getTxtHtmlOriginal() {
		return txtHtmlOriginal;
	}
	public void setTxtHtmlOriginal(String txtHtmlOriginal) {
		this.txtHtmlOriginal = txtHtmlOriginal;
	}
	public String getTxtHtmlModificado() {
		return txtHtmlModificado;
	}
	public void setTxtHtmlModificado(String txtHtmlModificado) {
		this.txtHtmlModificado = txtHtmlModificado;
	}
	
}
