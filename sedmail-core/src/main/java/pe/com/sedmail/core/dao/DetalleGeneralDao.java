package pe.com.sedmail.core.dao;

import java.util.List;
import java.util.Map;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.dao.base.BaseDao;
import pe.com.sedmail.core.entity.DetalleGeneral;

public interface DetalleGeneralDao extends BaseDao<DetalleGeneral, Long> {
	
	List<DetalleGeneral> listarDetalleGeneral(Map<String, Object> mapBusqueda) throws GmdException;
	
	List<DetalleGeneral> buscarDetalleGeneral(Map<String, Object> mapBusqueda) throws GmdException;
	
}
