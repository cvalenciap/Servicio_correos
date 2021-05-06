package pe.com.sedmail.core.util;

import java.util.Map;

import org.apache.log4j.Logger;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.gmd.util.properties.PropiedadesUtil;

public class ExchangeUtil {
	final static Logger logger = Logger.getLogger(ExchangeUtil.class);
	
	private static ExchangeService exchangeService;
	
	private static final String URL_SERVICE_OFFICE_365 = "asmx.url.office.365";
	
	public static  ExchangeService getExchangeService(Map<String, String> configCorreo) throws GmdException{
		try{
			exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
			WebCredentials credentials = new WebCredentials(configCorreo.get("credentialUser"), configCorreo.get("credentialPass"));
			exchangeService.setCredentials(credentials);
			String URI = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, URL_SERVICE_OFFICE_365);
			exchangeService.setUrl(new java.net.URI(URI));
			exchangeService.setTraceEnabled(true);
			logger.info("Conexion al correo establecida");
		}catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
		}
		return exchangeService;
	}
	
}
