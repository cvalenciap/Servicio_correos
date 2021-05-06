package pe.com.sedmail.core.services;

import java.util.List;

import pe.com.sedmail.core.entity.Parametro;
import pe.com.sedmail.core.services.base.BaseService;
import pe.com.gmd.util.exception.GmdException;


public interface ParametroService extends BaseService<Parametro, Long> {
	 /**
     * Descripcion	metodo listarTodos
     * @return		List<DetalleParametro>
     * @throws 		GmdException
     */
    List<Parametro> listarTodos() throws GmdException;
    
}
