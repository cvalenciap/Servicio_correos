package pe.com.sedmail.core.parameters;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ListaOrdenServicioRequest implements Serializable {

	List<OrdenServicioRequest> listaOrdenesServicio;

	public List<OrdenServicioRequest> getListaOrdenesServicio() {
		return listaOrdenesServicio;
	}

	public void setListaOrdenesServicio(List<OrdenServicioRequest> listaOrdenesServicio) {
		this.listaOrdenesServicio = listaOrdenesServicio;
	}
	
	
	
}
