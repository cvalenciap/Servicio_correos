package pe.com.sedmail.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import pe.com.gmd.notificacion.correo.bean.AdjuntoCorreoBean;
import pe.com.gmd.notificacion.correo.bean.CabeceraCorreoBean;
import pe.com.gmd.notificacion.correo.bean.CorreoBean;
import pe.com.gmd.notificacion.correo.impl.NotificacionCorreoImpl;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.entity.ArchivoAdjunto;
import pe.com.sedmail.core.entity.DetalleGeneral;
import pe.com.sedmail.core.entity.ParametrosCorreo;


public class NotificacionUtil {
	
	final static Logger logger = Logger.getLogger(NotificacionUtil.class);
		
	public static String generarCuerpoCorreo(String body) throws GmdException {
		StringBuffer cuerpoHTML = new StringBuffer();
        cuerpoHTML.append("<html>");
        cuerpoHTML.append("<head>");
        cuerpoHTML.append("</head>");
        cuerpoHTML.append("<body>");
        cuerpoHTML.append(body);
        cuerpoHTML.append("</body>");
        cuerpoHTML.append("</html>");
        return cuerpoHTML.toString();
	}
	
    public static void enviarCorreoElectronicoSMTP(ParametrosCorreo parametros, List<DetalleGeneral> datosSMTP) throws GmdException{
    	Properties configuracionServidor = new Properties();
    	List<AdjuntoCorreoBean> listaAdjunto = new ArrayList<AdjuntoCorreoBean>();
    	try {
//        	config servidor SMTP
//			configuracionServidor.put("mail.transport.protocol", "smtp");//
			configuracionServidor.put("mail.smtp.host", GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_HOST).getValor());
			configuracionServidor.put("mail.smtp.port", GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_PUERTO).getValor());
			configuracionServidor.put("mail.smtp.auth", GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_AUTH).getValor());
			DetalleGeneral detAux = GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_TRUST);
			if(detAux.getValor() != null){
				configuracionServidor.put("mail.smtp.ssl.trust", detAux.getValor());
			}
			configuracionServidor.put("mail.smtp.starttls.enable", GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_STARTTLS).getValor());
			configuracionServidor.put("mail.smtp.connectiontimeout", GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_TIMEOUT).getValor() + "000");
			configuracionServidor.put("mail.smtp.timeout", GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_TIMEOUT).getValor() + "000");
			CorreoBean correoBean = new CorreoBean();
            CabeceraCorreoBean cabeceraCorreoBean = new CabeceraCorreoBean();
            
//            parametros de cabecera
            cabeceraCorreoBean.setCorreoRemitente(parametros.getRemitenteCorreo());
            cabeceraCorreoBean.setClaveRemitente(parametros.getRemitenteClave());
            cabeceraCorreoBean.setNombreRemiente(parametros.getRemitente() != null ? parametros.getRemitente() : parametros.getRemitenteCorreo());
//            destinatario
            cabeceraCorreoBean.setCorreoDestino(parametros.getDestinatario());
//            copia
            cabeceraCorreoBean.setCorreoCopia(parametros.getCopia() != null ? parametros.getCopia() : null);
//            copia oculta
            cabeceraCorreoBean.setCorreoCopiaOculta(parametros.getCopiaOculta() != null ? parametros.getCopiaOculta() : null);
            
//            parametros de correo
            correoBean.setAsunto(parametros.getAsunto() != null ? parametros.getAsunto() : null);
            correoBean.setMensaje(parametros.getCuerpoHTML());
//            archivos adjuntos
            if(parametros.getArchivosAdjuntos() != null && parametros.getArchivosAdjuntos().size() > 0){
            	Long maxSize = Long.valueOf(GeneralUtil.getDetalleGeneralForDescript(datosSMTP, ConstantesParametros.DES_PARAMETRO_SIZEFILE).getValor());
            	if(FileUtil.validTotalSizeFile(parametros.getArchivosAdjuntos(), maxSize)){
            		for(ArchivoAdjunto archivo : parametros.getArchivosAdjuntos()){
    					Map<String, Object> adjuntos = new HashMap<String, Object>();
    					adjuntos = FileUtil.saveTempFile(archivo.getNombreArchivo(), archivo.getFileString(), archivo.getBytesArchivo(), maxSize);
    					if(adjuntos.get("estadoRespuesta").equals(ConstanteServices.ERROR)){
    						throw new GmdException((String) adjuntos.get("mensajeRespuesta"));
    					}
    					AdjuntoCorreoBean adjuntoCorreo = new AdjuntoCorreoBean((String) adjuntos.get("rutaArchivo"));
    					adjuntoCorreo.setNombreArchivo((String) adjuntos.get("nombreArchivo"));
    					listaAdjunto.add(adjuntoCorreo);
    				}
    				correoBean.setArchivosAdjuntos(listaAdjunto);
            	}else{
            		throw new GmdException(ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO + ": " + ConstantesParametros.TAMANIO_MAX_ARCHIVO);
            	}
            }
            correoBean.setCabeceraCorreoBean(cabeceraCorreoBean);
            NotificacionCorreoImpl.obtenerInstancia().enviarCorreo(configuracionServidor, correoBean);
            FileUtil.deleteTempFile(listaAdjunto);
        } catch (Exception exception) {
        	if(listaAdjunto.size() > 0){
        		FileUtil.deleteTempFile(listaAdjunto);
        	}
            throw new GmdException(exception);
        }
    }
    
    public static void enviarCorreoElectronicoOff365(ParametrosCorreo parametros, List<DetalleGeneral> datosCloud) throws GmdException{
    	ExchangeService exchangeService = null;
    	Map<String, String> configExchange = new HashMap<String, String>();
    	Boolean flagAux = false;
    	try{
			configExchange.put("credentialUser", parametros.getUserOffice365());
			configExchange.put("credentialPass", parametros.getPassOffice365());
			exchangeService = ExchangeUtil.getExchangeService(configExchange);
			EmailMessage emailMessage = new EmailMessage(exchangeService);
//			parametros emisor	
			EmailAddress from = new EmailAddress(configExchange.get("remitenteCorreo"));
			emailMessage.setFrom(from);
			EmailAddress sender = new EmailAddress(configExchange.get("remitente"));
			emailMessage.setSender(sender);
//			destinatarios
			for(String destinatario : parametros.getDestinatario()){
				EmailAddress to = new EmailAddress(destinatario);
				emailMessage.getToRecipients().add(to);
			}
//			copia
			if(parametros.getCopia() != null && parametros.getCopia().size() > 0){
				for(String copia : parametros.getCopia()){
					EmailAddress cc = new EmailAddress(copia);
					emailMessage.getCcRecipients().add(cc);
				}
			}
//			copia oculta
			if(parametros.getCopiaOculta() != null && parametros.getCopiaOculta().size() > 0){
				for(String copiaOculta : parametros.getCopiaOculta()){
					EmailAddress bcc = new EmailAddress(copiaOculta);
					emailMessage.getBccRecipients().add(bcc);
				}
			}
//			parametros de correo
			emailMessage.setSubject(parametros.getAsunto() != null ? parametros.getAsunto() : null);
			MessageBody messageBody = new MessageBody(parametros.getCuerpoHTML());
			emailMessage.setBody(messageBody);
//			archivos adjuntos
			if(parametros.getArchivosAdjuntos() != null && parametros.getArchivosAdjuntos().size() > 0){
				Long maxSize = Long.valueOf(GeneralUtil.getDetalleGeneralForDescript(datosCloud, ConstantesParametros.DES_PARAMETRO_SIZEFILE).getValor());
				if(FileUtil.validTotalSizeFile(parametros.getArchivosAdjuntos(), maxSize)){
					if(parametros.getArchivosAdjuntos().get(0).getFileString() != null){
						for(ArchivoAdjunto archivo : parametros.getArchivosAdjuntos()){
							emailMessage.getAttachments().addFileAttachment(archivo.getNombreArchivo(), Base64.decodeBase64(archivo.getFileString().getBytes()));
						}
					}else{
						for(ArchivoAdjunto archivo : parametros.getArchivosAdjuntos()){
							emailMessage.getAttachments().addFileAttachment(archivo.getNombreArchivo(), archivo.getBytesArchivo());
						}
					}
				}else{
					throw new GmdException(ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO + ": " + ConstantesParametros.TAMANIO_MAX_ARCHIVO);
				}
			}
			emailMessage.send();
			flagAux = true;
    	}catch(Exception exception) {
    		throw new GmdException(exception);
    	}finally{
    		if(!flagAux){
    			throw new GmdException(ConstanteServices.MENSAJE_OUT_LANG_OFF365);
    		}
    	}
    }
}
