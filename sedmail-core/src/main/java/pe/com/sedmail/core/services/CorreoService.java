package pe.com.sedmail.core.services;

import java.util.List;
import java.util.Map;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.entity.DetalleGeneral;
import pe.com.sedmail.core.entity.ParametrosCorreo;
import pe.com.sedmail.core.services.base.BaseService;

public interface CorreoService extends BaseService <Object, Long> {
	
	Map<String, String> enviarCorreo(ParametrosCorreo parametrosCorreo) throws GmdException;
	
	DetalleGeneral buscarDetalleGeneral(Integer idDetGeneral, String estado) throws GmdException;
	
	List<DetalleGeneral> listarDetalleGeneral(Integer idGeneral) throws GmdException;
}
