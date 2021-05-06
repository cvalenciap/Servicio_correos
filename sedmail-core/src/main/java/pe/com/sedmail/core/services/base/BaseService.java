package pe.com.sedmail.core.services.base;

import java.util.List;


import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.dao.base.BaseDao;

public interface BaseService<E, ID>{

	void insertar(E entidad) throws GmdException;

	void actualizar(E entidad) throws GmdException;

	void eliminar(ID id) throws GmdException;

	E obtener(ID id) throws GmdException;

	List<E> listar() throws GmdException;

	void setBase(BaseDao<?, ?> baseDao);
}
