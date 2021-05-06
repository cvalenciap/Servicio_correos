package pe.com.sedmail.core.services;

import java.util.Map;

import pe.com.sedmail.core.entity.PlantillaCorreoUsuario;
import pe.com.sedmail.core.services.base.BaseService;
import pe.com.gmd.util.exception.GmdException;


public interface PlantillaCorreoUsuarioService extends BaseService<PlantillaCorreoUsuario, Long> {

	/**
     * Descripcion	metodo anular
     * @param 		parametrosBusqueda
     * @return		boolean
     * @throws 		GmdException
     */
    boolean anular(Map<String, Object> parametrosBusqueda) throws GmdException;
    
}
