package pe.com.sedmail.core.util;

import static java.text.MessageFormat.format;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import pe.com.sedmail.core.entity.DetalleGeneral;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.gmd.util.properties.PropiedadesUtil;

public class GeneralUtil {

	private static final Logger LOGGER = Logger.getLogger(GeneralUtil.class);
	
	public static Map<String, Object> procesarSubirArchivo(String imagenString,String nombreArchivo,String nombrePersonalizado){
		
//		MultipartFile fileObj,
		String mensajeError ="";
		String ruta="";
		Map<String, Object> mapResultado = new HashMap<String,Object>(); 

		try{
			String path = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "archivo.rutaGuardado");	
			Long tamanioMax = Long.parseLong(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "archivo.tamanioMax"));
		
			//MultipartFile  fileObj = request.getFile("fileObj");

			if(imagenString != null){
				
				 byte[] bytes =  Base64.decodeBase64(imagenString.getBytes());

//				File convFile = new File(fileObj.getOriginalFilename());
//				String nombreArchivo = convFile.getPath();           
				int index = nombreArchivo.lastIndexOf('.'); 
				String extension = nombreArchivo.substring(index + 1);
				String nombre = nombrePersonalizado.equals("") ? nombreArchivo.substring(0, index) : nombrePersonalizado;

				String nombreArchivoInterno = generarNombreArchivo(nombre, extension);
				ruta = path + "\\" + nombreArchivoInterno;

				if(tamanioMax >= bytes.length){
				
					FileOutputStream fileOuputStream = new FileOutputStream(ruta); 
					fileOuputStream.write(bytes);
					fileOuputStream.close();
											
					mapResultado.put("estadoRespuesta", ConstanteServices.OK);
					mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_GRABO_CORRECTAMENTE);
					mapResultado.put("nombreArchivoInterno", nombreArchivoInterno);
				}else{
					mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
					mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO + ": " + ConstantesParametros.TAMANIO_MAX_ARCHIVO);
				}
				
				mapResultado.put("nombreArchivo", nombre);
				
			}else{
				mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
				mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_NO_ENCONTRADO_SUBIR);
			}

		} catch (Exception exception) {		  
			mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			mensajeError = format(ConstanteServices.MENSAJE_ERROR, error[0]);
			mapResultado.put("mensajeRespuesta", mensajeError);			
			LOGGER.error(error[1], exception);
		}

		return mapResultado;	
	}
	
	
	public static Map<String, Object> procesarSubirArchivo(String imagenString, String nombreArchivoInterno){
		String mensajeError = "";
		String ruta = "";
		Map<String, Object> mapResultado = new HashMap<String,Object>(); 

		try{
			String path = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "archivo.rutaGuardado");	
			Long tamanioMax = Long.parseLong(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "archivo.tamanioMax"));
			
			ruta = path + "\\" + nombreArchivoInterno;
			
			if(imagenString != null){
				
				byte[] bytes =  Base64.decodeBase64(imagenString.getBytes());
				if(tamanioMax >= bytes.length){				
					FileOutputStream fileOuputStream = new FileOutputStream(ruta); 
					fileOuputStream.write(bytes);
					fileOuputStream.close();
											
					mapResultado.put("estadoRespuesta", ConstanteServices.OK);
					mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_GRABO_CORRECTAMENTE);
					mapResultado.put("nombreArchivoInterno", nombreArchivoInterno);
				}else{
					mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
					mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO + ": " + ConstantesParametros.TAMANIO_MAX_ARCHIVO);
				}
				
				mapResultado.put("nombreArchivo", nombreArchivoInterno);
				
			}else{
				mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
				mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_NO_ENCONTRADO_SUBIR);
			}

		} catch (Exception exception) {		  
			mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			mensajeError = format(ConstanteServices.MENSAJE_ERROR, error[0]);
			mapResultado.put("mensajeRespuesta", mensajeError);			
			LOGGER.error(error[1], exception);
		}
		return mapResultado;	
	}

	
	private static String generarNombreArchivo(String nombre, String extension){
		
		Calendar now = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
		
		String nombreArchivo = nombre + sdf.format(now.getTime()) + "." + extension;
		
		return nombreArchivo;
	}
	
	
	public static DetalleGeneral getDetalleGeneralForDescript(List<DetalleGeneral> listaDetalleGeneral, String desDetalleGeneral){
		DetalleGeneral detalle = new DetalleGeneral();
		for(DetalleGeneral detalleAux : listaDetalleGeneral){
			if(detalleAux.getDesDetCorta().equals(desDetalleGeneral)){
				detalle = detalleAux;
				break;
			}
		}
		return detalle;
	} 

}
