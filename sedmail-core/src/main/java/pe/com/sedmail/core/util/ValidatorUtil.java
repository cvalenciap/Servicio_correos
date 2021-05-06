package pe.com.sedmail.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.primitives.Ints;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.entity.DetalleGeneral;
import pe.com.sedmail.core.entity.ParametrosCorreo;


public class ValidatorUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ValidatorUtil.class);
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static boolean validateEmail(String email) {
//		Compiles the given regular expression into a pattern.
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		
//		Match the given input against this pattern
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean emailValidator(String email){
		boolean isValid = false;
		try{
//			Create InternetAddres object and validated the supplied
//			address which is this case is an email address
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		}catch(AddressException e){
			LOGGER.info("Correo no válido: " + email);
		}
		return isValid;
	}
	

	public static Map<String, String> validarParametroCorreo(ParametrosCorreo parametrosCorreo) throws GmdException {
		Map<String, String> validateResponse = new HashMap<String, String>();
		try{
			/*validacion de tipo de envio*/
			if(parametrosCorreo.getIdTipoEnvio() != null){
				validateResponse.put("estadoRespuesta", ConstanteServices.OK);
				/*validacion de remitente*/
				if(parametrosCorreo.getRemitenteCorreo() != null){
					if(!(validateEmail(parametrosCorreo.getRemitenteCorreo())) || !(emailValidator(parametrosCorreo.getRemitenteCorreo()))){
						validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
						validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_WRONG_SENDER);
						return validateResponse;
					}
				}else{
					validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
					validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_EMPTY_SENDER);
					return validateResponse;
				}
				/*validacion de destinatario*/
				if(parametrosCorreo.getDestinatario() != null || parametrosCorreo.getDestinatario().size() > 0){
					for(String correoDestino : parametrosCorreo.getDestinatario()){
						if(!(validateEmail(correoDestino)) || !(emailValidator(correoDestino))){
							validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
							validateResponse.put("mensajeRepsuesta", ConstanteServices.MENSAJE_WRONG_TO);
						}
					}
				}else{
					validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
					validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_EMPTY_LIST_TO);
					return validateResponse;
				}
				/*validacion de mensaje*/
				if(parametrosCorreo.getCuerpoHTML() == null && !StringUtils.isEmpty(parametrosCorreo.getCuerpoHTML())){
					validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
					validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_WRONG_BODY);
					return validateResponse;
				}
				/*validacion de archivos adjuntos*/
//				if(parametrosCorreo.getArchivosAdjuntos() != null && parametrosCorreo.getArchivosAdjuntos().size() > 0){
//					if(!FileUtil.validTotalSizeFile(parametrosCorreo.getArchivosAdjuntos())){
//						validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
//						validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO);
//						return validateResponse;
//					}					
//				}
				switch(parametrosCorreo.getIdTipoEnvio()){
					case ConstantesParametros.TIPO_ENVIO_SMTP:
						/*validaciones propias de envio smtp*/
						if(parametrosCorreo.getRemitenteClave() == null && !StringUtils.isEmpty(parametrosCorreo.getRemitenteClave())){
							validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
							validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_EMPTY_PASS);
							return validateResponse;
						}
						break;
					case ConstantesParametros.TIPO_ENVIO_OFF_365:
						/*validaciones propias de envio office 365*/
						if(parametrosCorreo.getUserOffice365() == null && !StringUtils.isEmpty(parametrosCorreo.getUserOffice365())){
							validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
							validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_EMPTY_USR_OFF365);
							return validateResponse;
						}
						if(parametrosCorreo.getPassOffice365() == null && !StringUtils.isEmpty(parametrosCorreo.getPassOffice365())){
							validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
							validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_EMPTY_PASS_OFF365);
							return validateResponse;
						}
						break;
						/*prueba*/
					case 3:
						break;
					default:
						validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
						validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_TIPO_ENVIO_INVALIDO);
						return validateResponse;
				}
			}else{
				validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
				validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_TIPO_ENVIO_VACIO);
				return validateResponse;
			}
		}catch(Exception exception){
			throw new GmdException(exception);
		}
		return validateResponse;
	}
	
	public static Map<String, String> validarDetalleGeneral(List<DetalleGeneral> datos) throws GmdException{
		Map<String, String> validateResponse = new HashMap<String, String>();
		try{
			validateResponse.put("estadoRespuesta", ConstanteServices.OK);
			if(datos != null && datos.size() > 0){
				for(DetalleGeneral dato : datos){
					switch (dato.getTipoDato()){
						case ConstantesParametros.TIPO_DATO_STRING:
							if(dato.getValor().length() > dato.getLongitudDato().intValue()){
								validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
								validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_WRONG_DETAIL);
								return validateResponse;
							}
							break;
						case ConstantesParametros.TIPO_DATO_NUMBER:
							Integer value = Ints.tryParse(dato.getValor());
							if(value != null){
								if(Math.log10(value) > dato.getLongitudDato()){
									validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
									validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_WRONG_DETAIL);
									return validateResponse;
								}
							}else{
								validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
								validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_WRONG_DETAIL);
								return validateResponse;
							}
							break;
					}
				}
			}else{
				validateResponse.put("estadoRespuesta", ConstanteServices.ERROR);
				validateResponse.put("mensajeRespuesta", ConstanteServices.MENSAJE_WRONG_DET_GENERAL);
				return validateResponse;
			}
		}catch(Exception exception){
			throw new GmdException(exception);
		}
		return validateResponse;		
	} 

}
