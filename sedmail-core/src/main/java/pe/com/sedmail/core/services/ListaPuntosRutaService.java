/**
 * Resumen.
 * Objeto               :   ListaPuntosRutaService.java.
 * Descripción          :   Interface del Servicio REST Puntos Ruta.
 * Fecha de Creación    :   18/05/2017.
 * Autor                :   Edinson Muñoz Guia.
 * -------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo       Fecha       Nombre              Descripción
 * -------------------------------------------------------------------------------------------------
 *
 */

package pe.com.sedmail.core.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pe.com.gmd.util.exception.GmdException;

public interface ListaPuntosRutaService {
    
	/**
	 * Método que permite obtener los puntos destino pendientes para perfiles: Courier, Pudo, Destinatario.
	 * @param idRuta id de ruta, tipo int.
	 * @param codLogueo codigo de logueo, tipo String.
	 * @param idPerfil id de perfil, tipo int.
	 * @param idPudo id de pudo, tipo int.
	 * @param version codigo de version, tipo int.
	 * @return mapaSalida mapa de salida, tipo Map<String, Object>.
	 * @throws GmdException
	 */
	Map<String, Object> obtenerPuntosDestinoPendientes(long idRuta, String codLogueo, int idPerfil, 
			long idPudo, int version, String verAceptados) throws GmdException;
	
	List<Map<String, Object>> listarPiezas(HashMap<String, Object> mapParametros) throws GmdException;

	/* GA - 31/08/17 - INICIO */
	Map<String, Object> obtenerPiezasAceptarRuta(long idRuta) throws GmdException;
	/* GA - 31/08/17 - FIN */
}
