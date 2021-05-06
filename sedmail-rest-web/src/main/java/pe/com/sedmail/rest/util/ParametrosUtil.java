package pe.com.sedmail.rest.util;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.apache.cxf.common.util.StringUtils;

public class ParametrosUtil {
	
	/**
	 * Recorta una cadena desde el lado izquierdo
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String left(String cadena, int longitud)
	{
		String result = "";

		if (longitud > cadena.length())
		{
			result = cadena;
		}
		else
		{
			result = cadena.substring(0, longitud);
		}

		return result;
	}
	/**
	 * Recorta una cadena desde el lado derecho
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String right(String cadena, int longitud)
	{
		String result = "";

		if (longitud > cadena.length())
		{
			result = cadena;
		}
		else
		{
			result = cadena.substring(cadena.length() - longitud);
		}

		return result;
	}	
	
	/**
	 * Permite extraer una subcadena de una cadena principal dado un valor
	 * inicial y una longitud
	 * 
	 * @param cadena
	 * @param inicio
	 * @return
	 */
	public static String mid(String cadena, int inicio)
	{
		String result = "";

		if (inicio > cadena.length())
		{
			result = "";
		}
		else
		{
			inicio = inicio - 1;
			result = cadena.substring(inicio);
		}

		return result;
	}
	/**
	 * Permite extraer una subcadena de una cadena principal dado un valor
	 * inicial y una longitud
	 * 
	 * @param cadena
	 * @param inicio
	 * @param longitud
	 * @return
	 */
	public static String mid(String cadena, int inicio, int longitud)
	{
		String result = "";

		if (inicio > cadena.length())
		{
			result = "";
		}
		else
		{
			inicio = inicio - 1;
			if (inicio+longitud>cadena.length()){
				result = cadena.substring(inicio, cadena.length());
			}else{
				result = cadena.substring(inicio, inicio + longitud);
			}
		}

		return result;
	}

	/**
	 * Convierte una cadena de caracteres a mayúscula
	 * 
	 * @param cadena
	 * @return
	 */
	public static String uCase(String cadena)
	{
		return cadena.toUpperCase();
	}

	/**
	 * Convierte una cadena de caracteres a minúsculas
	 * 
	 * @param cadena
	 * @return
	 */
	public static String lCase(String cadena)
	{
		return cadena.toLowerCase();
	}
	/**
	 * Devuelve un valor numérico que especifica la posición de la primera
	 * aparición de una cadena en otra, desde el principio de la cadena
	 * 
	 * @param cadena
	 * @param cadenaBuscada
	 * @return
	 */
	public static int inStr(String cadena, String cadenaBuscada)
	{
		int posicion = cadena.indexOf(cadenaBuscada);
		if (posicion == -1)
		{
			return 0;
		}
		else
		{
			return posicion + 1;
		}

	}
	/**
	 * Devuelve la longitud de una cadena de caracteres
	 * 
	 * @param cadena
	 * @return
	 */
	public static int len(String cadena)
	{
		return cadena.length();
	}
	/**
	 * Quita espacios en blanco al inicio y fin de una cadena de caracteres
	 * 
	 * @param cadena
	 * @return
	 */
	public static String trim(String cadena)
	{
		return cadena.trim();
	}
	/**
	 * Devuelve la representaci�n String de un n�mero.
	 * 
	 * @param numero
	 * @return
	 */
	public static String str(int numero)
	{
		return String.valueOf(numero);
	}

	/**
	 * Devuelve la representaci�n String de un n�mero.
	 * 
	 * @param numero
	 * @return
	 */
	public static String str(double numero)
	{
		return String.valueOf(numero);
	}
	/**
	 * Adiciona o recorta espacios a la cadena de acuerdo a la longitud
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rightBlankPad(String cadena,int longitud){
		String cadenaRepetida=string(longitud," ");
		cadena=cadena+cadenaRepetida;
		cadena=left(cadena, longitud);
		return cadena;		
	}
	
	/* Método que rellena de ceros el lado derecho */
	public static String padRight(String str,String pad){
		String retorno="";
		if(str !=null && pad!=null){
			retorno = str + pad.substring(0, pad.length() - str.length());
		}
		return retorno;		
	}

	/**
	 * Repite una cadena de caracteres n (cantidad) veces
	 * 
	 * @param cantidad
	 * @param cadenaRepetir
	 * @return
	 */
	public static String string(int cantidad, String cadenaRepetir)
	{
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < cantidad; i++)
		{
			sb.append(cadenaRepetir);
		}

		return sb.toString();
	}
	/* Método que rellena de ceros el lado izquierdo  */
	public static String padleft(String str,String pad){
		String retorno="";
		if(str !=null && pad!=null){
		retorno = pad.substring(0, pad.length() - str.length()) + str;
		}
		return retorno;		
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static String getFechaActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	    return formateador.format(ahora);
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static String getFechaActualconFormato(String formato) {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat(formato);
	    return formateador.format(ahora);
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static String getFechaconFormato(Date date, String formato) {
	    SimpleDateFormat formateador = new SimpleDateFormat(formato);
	    return formateador.format(date);
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static Date getFechaActualToDate() {
		Calendar now = Calendar.getInstance();
		
	    return now.getTime();
	}
	
	public static Timestamp getFechaToTimestamp(String fecha, String formato){
		Timestamp timestamp = null;

		try{
			
			if(!StringUtils.isEmpty(fecha)){
			    fecha = fecha.replaceAll("/", "-");
				SimpleDateFormat dateFormat = new SimpleDateFormat(formato); //"yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(fecha);
				timestamp = new java.sql.Timestamp(parsedDate.getTime());
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}

		return timestamp;
	}
	
	/*
	 * Permite convertir un string dd/MM/yyyy to Timestamp
	 * */
	public static Timestamp convertStringToTimestamp(String fecha) throws ParseException{
	    Timestamp timestamp = null;  //declare timestamp
	    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	    Date d=dt.parse(fecha); 
	    		//new Date(fecha); // Intialize date with the string date
	    if(d!=null){  // simple null check
	    	timestamp = new java.sql.Timestamp(d.getTime());
	    }
	    
	    return timestamp;
	}
	
	/*
	 * Permite convertir un string dd/MM/yyyy to Date
	 * */
	public static Date convertObjectToDate(Object objeto){
	    Date fecha = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        String dateInString = String.valueOf(objeto);
        	fecha = formatter.parse(dateInString);
		} catch(Exception e) {
			fecha = null;
		}
	    return fecha;
	}
	
	/*
	 * Permite convertir un string dd/MM/yyyy to Timestamp y time hh:mm:ss
	 * caso 1: time hh:mm
	 * caso 2: time mm:ss
	 * caso 3: time hh:mm:ss
	 * */
	@SuppressWarnings("deprecation")
	public static Timestamp convertStringToTimestamp(String fecha, String time, Integer caso){
	    Timestamp timestamp = null;  //declare timestamp
	    Date d=new Date(fecha); // Intialize date with the string date
	    
	    if(d!=null){  // simple null check
	    	timestamp = new java.sql.Timestamp(d.getTime());
	    	
	    	addTimeToTimestamp(timestamp, time, caso);
	    }
	    
	    return timestamp;
	}
	
	@SuppressWarnings("deprecation")
	public static Timestamp addTimeToTimestamp(Timestamp timestamp, String time, Integer caso){
		
		switch(caso){
    	case 1:
    		timestamp.setHours(Integer.parseInt(time.substring(0,2)));
    		timestamp.setMinutes(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 2:
    		timestamp.setMinutes(Integer.parseInt(time.substring(0,2)));
    		timestamp.setSeconds(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 3:
    		timestamp.setHours(Integer.parseInt(time.substring(0,2)));
    		timestamp.setMinutes(Integer.parseInt(time.substring(3,5)));
    		timestamp.setSeconds(Integer.parseInt(time.substring(6,7)));
    		break;
    	}
		return timestamp;
	}
	
	/*
	 * Permite acceder a un atributo del objeto para setear un valor
	 */
	public static void setValueToFieldObject(Object object, String fieldName, Object value){
		try {
			Field field = object.getClass().getDeclaredField(fieldName);    
			field.setAccessible(true);
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean ValidarKeyMapParametros(String llave,Map<String, Object> map){
		boolean retorno = false;
		if (map.containsKey(llave)){
			retorno = true;           
	    }else{
	    	retorno =  false;
	    }
		return retorno;
	}
	
	public static String obtenerIdDepartamentoPorUbigeo(String idUbigeo){
		return idUbigeo.substring(0, 2);
	}

	public static String obtenerIdProvinciaPorUbigeo(String idUbigeo){
	 	return idUbigeo.substring(2, 4);
	}
	
	public static String obtenerIdDistritoPorUbigeo(String idUbigeo){
	 	return idUbigeo.substring(4, 6);
	}
	public static String formatearAUTF8(String texto){
        String cadena=null;
        try {
            cadena = new String(texto.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cadena;
    }
	public static String isNullOrBlank(Object objeto){
		String respuesta;
		if(objeto == null || String.valueOf(objeto).equals("null") || String.valueOf(objeto).trim().equals("")){
			respuesta = "";
		} else {
			respuesta = String.valueOf(objeto);
		}
		return respuesta;
	}
	
	
	public static String generarNombreArchivo(String nombre, String extension){
		
		Calendar now = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
		
		String nombreArchivo = nombre + sdf.format(now.getTime()) + "." + extension;
		
		return nombreArchivo;
	}

}
