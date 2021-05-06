package pe.com.sedmail.core.services;

import java.util.List;
import java.util.Map;

import pe.com.sedmail.core.entity.PlantillaCorreo;
import pe.com.sedmail.core.services.base.BaseService;
import pe.com.gmd.util.exception.GmdException;


public interface PlantillaCorreoService extends BaseService<PlantillaCorreo, Long> {

	/**
     * Descripcion	metodo listarTodos
     * @param 		param
     * @return		List<PlantillaCorreo>
     * @throws 		GmdException
     */
    List<PlantillaCorreo> listarTodos(Map<String, Object> param) throws GmdException;
    
    /**
     * Descripcion	metodo anular
     * @param 		parametrosBusqueda
     * @return		boolean
     * @throws 		GmdException
     */
    boolean anular(Map<String, Object> parametrosBusqueda) throws GmdException;
    
    /**
     * Descripcion	metodo listarCuerpoHtml
     * @param 		param
     * @return		objeto PlantillaCorreo
     * @throws 		GmdException
     */
    PlantillaCorreo listarCuerpoHtml(Map<String, Object> param) throws GmdException;

}
