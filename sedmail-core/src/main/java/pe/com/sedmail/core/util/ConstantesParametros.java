package pe.com.sedmail.core.util;

public class ConstantesParametros { 
		
	/*Tamaño de archivos*/
	public static final String TAMANIO_MAX_ARCHIVO =  "10MB";
//	String.valueOf(Long.parseLong(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "archivo.tamanioMax")) / (1024*1024))
	                                                 
    public static final String DATO_FORMATO_FECHA_VALIDAR = "DATO FORMATO FECHA A VALIDAR";
    //PARA FECHAS
    public static final String FORMATO_24_HORAS	 	= "FORMATO 24 HORAS";
    public static final String FORMATO_12_HORAS	 	= "FORMATO 12 HORAS";
    public static final String FORMATO_DD_MM_YYYY	= "FORMATO DD/MM/YYYY";
    
    public static final String FORMATO_24_HORAS_DATE = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMATO_12_HORAS_DATE = "dd/MM/yyyy hh:mm aa";
    public static final String FORMATO_DD_MM_YYYY_DATE = "dd/MM/yyyy";
    
    public static final String TIPO_DATO_STRING		= "STRING";
    public static final String TIPO_DATO_NUMBER 	= "NUMBER";
    public static final String DATO_S_LONG	    	= "LONGITUD STRING"; //valido solo para TIPO_DATO_S
                                                    
    public static final String VAL_COLUMN		    = "VALOR";
    public static final String NULL_COLUMN		    = "NULLABLE";
    public static final String FILA_INICIO		    = "FILA_INICIO";
    public static final String FILA_FIN			    = "FILA_FIN";
    public static final String LISTA_DETALLE	    = "LISTA_DETALLE";
    public static final String OK_COLUMN 		    = "OK_COLUMN";
    public static final String FILAS_EXCEL	 	    = "FILAS_EXCEL";
    public static final String ERRORES_EXCEL	 	= "ERRORES_EXCEL";    
    
    public static final String MSJ_ERR_NULL_COLUMN  = "No puede ser nulo";
    public static final String MSJ_ERR_NUMERIC		= "Debe ser numérico";
    public static final String MSJ_ERR_INTEG 		= "Debe ser entero";
    public static final String MSJ_ERR_INTEG_POS 	= "Debe ser numérico entero positivo";
    public static final String MSJ_ERR_INTEG_NEG 	= "Debe ser numérico entero negativo";
    public static final String MSJ_ERR_FLOAT		= "Debe ser decimal";
    public static final String MSJ_ERR_FLOAT_POS 	= "Debe ser numérico decimal positivo";
    public static final String MSJ_ERR_FLOAT_NEG 	= "Debe ser numérico decimal negativo";
    public static final String MSJ_ERR_ENT_LONG_VAR	= "La longitud máxima de parte entera es de VALUE dígito(s)";
    public static final String MSJ_ERR_ENT_LONG_FIJ	= "La longitud es de VALUE dígito(s)";
    public static final String MSJ_ERR_DEC_LONG_VAR	= "La longitud máxima de parte decimal es de VALUE dígito(s)";
    public static final String MSJ_ERR_DEC_LONG_FIJ	= "La longitud es de VALUE dígito(s)";
    public static final String MSJ_ERR_STR_LONG_VAR = "La longitud máxima es de VALUE caracteres(s)";
    public static final String MSJ_ERR_STR_LONG_FIJ = "La longitud es de VALUE caracteres(s)";
    public static final String MSJ_ERR_VALIDACION_TIPO_SERVICIO	= "Tipo de Servicio No Existe";
    public static final String MSJ_ERR_VALIDACION_FORMATO_FECHA	= "Debe ser una Fecha";
    public static final String MSJ_ERR_VALIDACION_24FORMATO_FECHA	= "Debe Tener un formato a 24 Horas";
    public static final String MSJ_ERR_VALIDACION_12FORMATO_FECHA	= "Debe Tener un formato a 12 Horas";
    public static final String MSJ_ERR_VALIDACION_DDMMYYYYFORMATO_FECHA	= "Debe Tener un formato a DD/MM/YYYY";
    public static final String MSJ_ERR_NO_EXISTE	= "VALUE No Existe";
    public static final String MSJ_ERR_VALIDACION_TIPO_ORIGEN	= "Numero Invalido";
    public static final String MSJ_ERR_VALIDACION_FECHA_RECOJO	= "Menor o igual a la fecha Actual";
    public static final String MSJ_ERR_NO_VALIDO	= "No Valido";
    public static final String MSJ_ERR_EXISTE	= "VALUE Ya Existe";
        
    /*ESTADO DE REGISTRO*/
    public static final String ANULAR_REGISTRO = "ANULAR_REGISTRO";
    public static final String ACTUALIZAR_REGISTRO = "ACTUALIZAR_REGISTRO";
    public static final String AGREGAR_REGISTRO = "AGREGAR_REGISTRO";
	 
	 /* Constantes De Registro */
	 public static final String ST_REGI_INACTIVO = "0";
	 public static final String ST_REGI_ACTIVO = "1";
	 
	 public static final int TIPO_ENVIO_SMTP = 1;
	 public static final int TIPO_ENVIO_OFF_365 = 2;
	 public static final int TIPO_ENVIO_SMTP_365 = 3;//UTILITARIO OPCIONAL
	 
	 /*Identificadores para SMTP - NOT OFF*/
	 public static Integer ID_PARAMETRO_HOST = 1;
	 public static Integer ID_PARAMETRO_PUERTO = 2;
	 public static Integer ID_PARAMETRO_AUTH = 3;
	 public static Integer ID_PARAMETRO_STARTTLS = 4;
	 public static Integer ID_PARAMETRO_TRUST = 5;
	 
	 public static String DES_PARAMETRO_HOST = "HOST";
	 public static String DES_PARAMETRO_PUERTO = "PUERTO";
	 public static String DES_PARAMETRO_AUTH = "AUTH";
	 public static String DES_PARAMETRO_STARTTLS = "STARTTLS";
	 public static String DES_PARAMETRO_TRUST = "TRUST";
	 public static String DES_PARAMETRO_TIMEOUT = "TIMEOUT";
	 public static String DES_PARAMETRO_SIZEFILE = "SIZEFILE";
	 
	 
	 public static Integer ID_PARAMETRO_DEFAULT_USER_365 = 1;
	 public static Integer ID_PARAMETRO_DEFAULT_PASS_365 = 2;
}
