package pe.com.sedmail.rest.security;

import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import pe.com.sedmail.core.util.ConstanteServices;
import pe.com.gmd.util.properties.PropiedadesUtil;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.ws.SeguridadClienteWs;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);
	
	private static final String COD_SEDMAIL_SEG = "codigo.sistema.mensajeria";
		
	@Autowired
	private SeguridadClienteWs stub;
		
	public CustomAuthenticationProvider() {
        super();
    }
	
    @SuppressWarnings("unused")
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	Authentication objAuthentication = null;
    	Retorno retorno = null;
    	Map<String, Object> parametros = new HashMap<String, Object>();
    	String mensajeRespuesta = "";
    	try {
    		//Obtener datos de usuario
    		String usuario = authentication.getName().toUpperCase();
            String password = authentication.getCredentials().toString();
            Integer codSistema = Integer.parseInt(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, COD_SEDMAIL_SEG));
            //autenticar usuario servicio seguridad
            retorno = stub.autenticacionUsuarioActWs(usuario, codSistema, password);
            LOGGER.info("mensaje retorno Servicio "+retorno.getMensaje());
            mensajeRespuesta = retorno.getMensaje();
            //validando respuesta servicio seguridad
            if(ConstanteServices.SUCCESS.equals(retorno.getCodigo())){
            	String strToken = retorno.getToken();
            	String ipCliente = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
            	LOGGER.info("TOKEN : " + strToken);
            	if(retorno.getFlagCambiarClave() == null || retorno.getFlagCambiarClave().equals(ConstanteServices.ID_CAMB_CLAVE_USER)){
            		throw new BadCredentialsException(format(ConstanteServices.SERVICE_AUTENTICACION_CAMBIAR_CLAVE, usuario));
            	}else{
            		objAuthentication = new UsernamePasswordAuthenticationToken(usuario, password, new ArrayList<>());
            		stub.autenticacionUsuarioCompletaWs(usuario, ipCliente, strToken, codSistema);
            	}
            }else{
            	throw new BadCredentialsException(format(ConstanteServices.SERVICE_AUTENTICACION_ERROR, usuario));
            }
		} catch (BadCredentialsException badCredentialsException) {
			throw badCredentialsException;
		}
    	return objAuthentication;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
