package pe.com.sedmail.core.util;

import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import pe.com.gmd.notificacion.correo.bean.AdjuntoCorreoBean;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedmail.core.entity.ArchivoAdjunto;

public class FileUtil {
	
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);
	
//	private static final String MAX_FILE_SIZE = "archivo.tamanioMax";
//	
//	private static final String MAX_TOTAL_FILE_SIZE = "total.archivo.tamanioMax";
		
	public static Map<String, Object> saveTempFile(String nombreArchivo, String fileString, byte[] byteArchivo, Long maxSize) throws GmdException{
		String ruta = "";
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try{
//			Long tamanioMax = Long.parseLong(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, MAX_FILE_SIZE));
			byte[] bytes = null;
			if(fileString != null && !StringUtils.isEmpty(fileString)){
				bytes = Base64.decodeBase64(fileString.getBytes());
			}else if(byteArchivo != null && byteArchivo.length > 0){
				bytes = byteArchivo; 
			}else{
				mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
				mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_NO_ENCONTRADO_SUBIR);
				return mapResultado;
			}
			int index = nombreArchivo.lastIndexOf('.');
			String extension = nombreArchivo.substring(index + 1);
			String nombreArchivoInterno = generarNombreArchivo(nombreArchivo.substring(0, index), extension);
			ruta = System.getProperty("java.io.tmpdir") + "\\" + nombreArchivoInterno;
			if(maxSize*1024*1024 >= bytes.length){
				FileOutputStream fileOutputStream = new FileOutputStream(ruta);
				fileOutputStream.write(bytes);
				fileOutputStream.close();
				mapResultado.put("estadoRespuesta", ConstanteServices.OK);
				mapResultado.put("nombreArchivo", nombreArchivo);
				mapResultado.put("nombreArchivoInterno", nombreArchivoInterno);
				mapResultado.put("rutaArchivo", ruta);
			}else{
				mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
				mapResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO + ": " + ConstantesParametros.TAMANIO_MAX_ARCHIVO);
			}
		}catch(Exception exception){
			mapResultado.put("estadoRespuesta", ConstanteServices.ERROR);
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			mapResultado.put("mensajeRespuesta", format(ConstanteServices.MENSAJE_ERROR, error[0]));
			LOGGER.error(error[1], exception);
		}
		return mapResultado;
	}
	
	public static void deleteTempFile(List<AdjuntoCorreoBean> listaAdjuntos) throws GmdException {
		try{
			if(listaAdjuntos.size() > 0){
				for(AdjuntoCorreoBean adjunto : listaAdjuntos){
					File currentFile = new File(adjunto.getPathAdjunto());
					currentFile.delete();
				}
			}
		}catch(Exception exception){
			throw new GmdException(exception);
		}
	}
	
	private static String generarNombreArchivo(String nombre, String extension){
		
		Calendar now = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
		
		String nombreArchivo = nombre + sdf.format(now.getTime()) + "." + extension;
		
		return nombreArchivo;
	}
	
	public static Boolean validTotalSizeFile(List<ArchivoAdjunto> listaArchivos, Long maxSize) throws GmdException{
		Boolean flag = true;
		int total = 0;
		try{
//			Long tamanioMax = Long.parseLong(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, MAX_TOTAL_FILE_SIZE));
			if(listaArchivos.get(0).getFileString() != null){
				for(ArchivoAdjunto archivo : listaArchivos){
					byte[] bytes = Base64.decodeBase64(archivo.getFileString().getBytes());
					total =+ bytes.length;
				}
			}else if(listaArchivos.get(0).getBytesArchivo() != null){
				for(ArchivoAdjunto archivo : listaArchivos){
					byte[] bytes = archivo.getBytesArchivo();
					total =+ bytes.length;
				}
			}
			LOGGER.info("total : " + total);
			if(total > maxSize*1024*1024){
				flag = false;
			}
		}catch(Exception exception){
			throw new GmdException(exception);
		}
		return flag;
	}
	
}
