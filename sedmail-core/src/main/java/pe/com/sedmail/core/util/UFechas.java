package pe.com.sedmail.core.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import pe.com.gmd.util.exception.MensajeExceptionUtil;


@SuppressWarnings("serial")
public class UFechas implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(UFechas.class);
	
	public static boolean EntreFechasTiempo(Date fechaValidar, Date fechaInicio, Date fechaFin ){
		boolean retorno = false;
		try{
			if ((fechaInicio.getTime() < fechaValidar.getTime()) 
					&& (fechaValidar.getTime() < fechaFin.getTime()))
			{
				retorno=true;
			}
		}
		catch(Exception excepcion){
            String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);         
            LOGGER.error(error[1], excepcion);
        }
		return retorno;
	}
	
	public static boolean ComparaFechasTiempo(Date fechaMenor, Date fechaMayor){
		boolean retorno = false;
		try{
			if (fechaMenor.getTime() < fechaMayor.getTime()) 
			{
				retorno=true;
			}
		}
		catch(Exception excepcion){
            String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);         
            LOGGER.error(error[1], excepcion);
        }
		return retorno;
	}
	
	public static boolean ComparaFechasTiempoMenorIgual(Date fechaMenor, Date fechaMayor){
		boolean retorno = false;
		try{
			if (fechaMenor.getTime() <= fechaMayor.getTime()) 
			{
				retorno=true;
			}
		}
		catch(Exception excepcion){
            String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);         
            LOGGER.error(error[1], excepcion);
        }
		return retorno;
	}
	public static boolean ComparaFechasTiempoMayorIgual(Date fechaMayor, Date fechaMenor){
		boolean retorno = false;
		try{
			if (fechaMenor.getTime() <= fechaMayor.getTime()) 
			{
				retorno=true;
			}
		}
		catch(Exception excepcion){
            String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);         
            LOGGER.error(error[1], excepcion);
        }
		return retorno;
	}
	
	public static boolean ComparaTiempo(Date fechaMenor, Date fechaMayor) {
		boolean retorno = false;
		  try{
			  DateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss.SSS");
			  if(formatoTiempo.format(fechaMenor).compareTo(formatoTiempo.format(fechaMayor)) < 0)
			  {
				  retorno = true;
			  }
		  }
		  catch(Exception excepcion){
	            String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);         
	            LOGGER.error(error[1], excepcion);
	        }
		  return retorno;
	} 
	
	public static boolean EntreRangoTiempo(Date fechaValidar, Date fechaInicio, Date fechaFin) {
		  boolean retorno = false;
		  try{
			  DateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss.SSS");
			  if((formatoTiempo.format(fechaInicio).compareTo(formatoTiempo.format(fechaValidar)) < 0)
					  && (formatoTiempo.format(fechaValidar).compareTo(formatoTiempo.format(fechaFin)) < 0))
			  {
				  retorno = true;
			  }
		  }
		  catch(Exception excepcion){
	            String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);         
	            LOGGER.error(error[1], excepcion);
	        }
		  return retorno;
	} 
}
