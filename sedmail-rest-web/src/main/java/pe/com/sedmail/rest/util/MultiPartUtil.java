package pe.com.sedmail.rest.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.entity.ArchivoAdjunto;
import org.glassfish.jersey.media.multipart.BodyPartEntity;

public class MultiPartUtil {
	
	private static final Logger LOGGER = Logger.getLogger(MultiPartUtil.class);
	
	public static List<ArchivoAdjunto> addFilesMultiPart(List<FormDataBodyPart> bodyParts) throws GmdException{
		List<ArchivoAdjunto> listaArchivos = new ArrayList<ArchivoAdjunto>();
		try{
			if(bodyParts != null){
				for(int i= 0; i < bodyParts.size(); i++){
					BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
					String fileName = bodyParts.get(i).getContentDisposition().getFileName();
					int index = fileName.lastIndexOf('.');
					String extension = fileName.substring(index + 1);
					fileName = fileName.substring(0, index);
					index = fileName.lastIndexOf('.');
					fileName = fileName.substring(0, index);
					ArchivoAdjunto file = new ArchivoAdjunto();
					file.setNombreArchivo(fileName + '.' + extension);
					file.setBytesArchivo(IOUtils.toByteArray(bodyPartEntity.getInputStream()));
					listaArchivos.add(file);
				}
			}
		}catch(Exception exception){
			LOGGER.info("error : " + exception.getMessage());
			throw new GmdException(exception);
		}
		return listaArchivos;
	}
	
}
