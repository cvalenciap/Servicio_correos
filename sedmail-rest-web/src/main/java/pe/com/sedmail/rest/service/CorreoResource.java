package pe.com.sedmail.rest.service;

import static java.text.MessageFormat.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedmail.core.entity.ArchivoAdjunto;
import pe.com.sedmail.core.entity.ParametrosCorreo;
import pe.com.sedmail.core.services.CorreoService;
import pe.com.sedmail.core.util.ConstanteServices;
import pe.com.sedmail.core.util.ValidatorUtil;
import pe.com.sedmail.rest.bean.RespuestaBean;
import pe.com.sedmail.rest.util.JsonUtil;
import pe.com.sedmail.rest.util.MultiPartUtil;

@Component
@Path("/correo")
public class CorreoResource {
	
	private static final Logger LOGGER = Logger.getLogger(CorreoResource.class);
	
	private RespuestaBean respuestaBean = new RespuestaBean();
	
	@Autowired
	private CorreoService correoService;
	
	@POST
	@Path("/enviarCorreo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String enviarCorreo(@RequestBody String map) throws GmdException {
		Map<String, String> resultado = new HashMap<String, String>();
		try{
			ParametrosCorreo parametrosCorreo = JsonUtil.convertirCadenaJsonAObjeto(map, ParametrosCorreo.class);
			LOGGER.info("Inicio enviarCorreo");
			LOGGER.info("JSON inicio: " + map);
			if(parametrosCorreo != null){
				resultado = ValidatorUtil.validarParametroCorreo(parametrosCorreo);
				if(resultado.get("estadoRespuesta").equals(ConstanteServices.OK)){
					resultado = correoService.enviarCorreo(parametrosCorreo);
					if(resultado.get("estadoRespuesta").equals(ConstanteServices.OK)){
						respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
						respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ENVIADO);
					}else{
						respuestaBean.setEstadoRespuesta(resultado.get("estadoRespuesta"));
						respuestaBean.setMensajeRespuesta(resultado.get("mensajeRespuesta"));
					}
				}else{
					respuestaBean.setEstadoRespuesta(resultado.get("estadoRespuesta"));
					respuestaBean.setMensajeRespuesta(resultado.get("mensajeRespuesta"));
				}	
			}else{
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_EMPTY_VALUE);
			}
		}catch (Exception e){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(e);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]) + ".Traza inicial del error : " +e.getMessage());
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], e);
		}
		LOGGER.info("Fin enviarCorreo");
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	@POST
    @Path("/enviarCorreoMultiPart")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    public String enviarCorreoMultiPart(
    		@FormDataParam("fileString") List<FormDataBodyPart> bodyParts,
    		@FormDataParam("fileString") FormDataContentDisposition cdh,
            @FormDataParam("map") String map
            ) throws GmdException{
		Map<String, String> resultado = new HashMap<String, String>();
		try{
			List<ArchivoAdjunto> listFiles = MultiPartUtil.addFilesMultiPart(bodyParts);
			ParametrosCorreo parametrosCorreo = JsonUtil.convertirCadenaJsonAObjeto(map, ParametrosCorreo.class);
			LOGGER.info("Inicio enviarCorreo");
			LOGGER.info("JSON inicio: " + map);
			if(parametrosCorreo != null){
				parametrosCorreo.setArchivosAdjuntos(listFiles);
				resultado = ValidatorUtil.validarParametroCorreo(parametrosCorreo);
				if(resultado.get("estadoRespuesta").equals(ConstanteServices.OK)){
					Map<String, String> resultado2 = new HashMap<String, String>();
					resultado2 = correoService.enviarCorreo(parametrosCorreo);
					if(resultado2.get("estadoRespuesta").equals(ConstanteServices.OK)){
						respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
						respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ENVIADO);
					}else{
						respuestaBean.setEstadoRespuesta(resultado.get("estadoRespuesta"));
						respuestaBean.setMensajeRespuesta(resultado.get("mensajeRespuesta"));
					}
				}else{
					respuestaBean.setEstadoRespuesta(resultado.get("estadoRespuesta"));
					respuestaBean.setMensajeRespuesta(resultado.get("mensajeRespuesta"));
				}	
			}else{
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_EMPTY_VALUE);
			}
		}catch (Exception e){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(e);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]) + ".Traza inicial del error : " +e.getMessage());
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], e);
		}
		LOGGER.info("Fin enviarCorreo");
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
    }
	
}
