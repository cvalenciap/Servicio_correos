package pe.com.sedmail.core.services;
import java.util.Map;

public interface NotificacionService{
	
	Map<String, Object> generarCorreo(Map<String, Object> parametros);
	
}
