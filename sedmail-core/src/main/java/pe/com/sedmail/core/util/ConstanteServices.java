package pe.com.sedmail.core.util;



public class ConstanteServices {

	/** Indicadores de autenticacion */
	public static final String SUCCESS = "1";
	public static final String FAILURE = "0";
	
    /** Mensaje que se envia para las peticiones AJAX cuando la sesión ha expirado */
    public static final String MENSAJE_SESION_EXPIRADA = "sesion expirada";
    public static final String MENSAJE_ERROR = "Ocurrió un error inesperado con ID {0}, informar al área correspondiente.";
    public static final String LOGIN_CODIGO_ERROR = "ERROR";
    
    public static final String SERVICE_AUTENTICACION_ERROR = "Autenticación no válida para el usuario: {0}";
    public static final String SERVICE_AUTENTICACION_CAMBIAR_CLAVE = "Autenticación no permitida por clave vencida para el usuario: {0}";
    
    /** Indicador del estado del registro "ACTIVO" */
    public static final String IND_ESTADO_REGISTRO_ACTIVO = "A";
    public static final String IND_ESTADO_REGISTRO_INACTIVO = "I";
    
    public static final String CODIGO_INDICADOR_ACCION_NUEVO = "1";
    
    public static final Integer ID_CAMB_CLAVE_USER = 1;
    
    public static final String ERROR_FECHA_VENCIMIENTO = "ERRORFECHA";
    
    public static final Integer VALOR_DIAS_VENCIMIENTO = 5;
    
    public static final String CONTRASENIA_POR_VENCER = "CONTRASENIA_POR_VENCER";
    
    public static final String CONTRASENIA_VENCERA = "ATENCIÓN... SU CONTRASEÑA VENCERÁ;...";

    public static final String LOGIN_MENSAJE_ERROR = "Error: Lo sentimos, su nombre de usuario y/o clave son incorrectos. Por favor inténtelo de nuevo.";
    
    public static final String OK = "OK";
 
    
    public static final String ERROR = "ERROR";
    public static final String INFO = "INFO";
    public static final String REGRESAR = "REGRESAR";
    
    
    public static final Integer ID_TIPO_PAGINA = 177;
    
    public static final String ARCHIVO_CONFIGURACION="configuracion";
    public static final String ARCHIVO_PDF="PDF";
    
    public static final String IMAGEN_DANGER = "danger";
    public static final String IMAGEN_SUCCESS = "success";
    
	/*Constantes de estados */
	public static final String ESTADO_OPCION_NUEVA = "NUEVA";
	public static final String ESTADO_OPCION_EDITAR = "EDITAR";
	public static final String ESTADO_OPCION_VER = "VER";
	public static final String ESTADO_ANULADO = "ANULADO";	
	
    public static final String MENSAJE_GRABO_CORRECTAMENTE= "Se grab&oacute; correctamente ";
    public static final String MENSAJE_ACTUALIZO_CORRECTAMENTE= "Se actualiz&oacute; correctamente ";
    
    /* Constantes de Carga Archivo */
    public static final String MENSAJE_ARCHIVO_NO_ENCONTRADO_SUBIR = "Verificar archivo a subir";
    public static final String MENSAJE_ARCHIVO_NO_ENCONTRADO_DESCARGAR = "Archivo no encontrado";
    public static final String MENSAJE_ARCHIVO_EXCESO_TAMANIO = "Excedió el tamaño de archivo permitido";
    public static final String MENSAJE_CELDA_FINAL_NO_ENCONTRADA = "Debe asignar celda final";
    public static final String MENSAJE_CABECERA_EXCEL_NO_ENCONTRADA = "Formato de cabecera no encontrado";
    
    public static final String MENSAJE_EMPTY_VALUE = "Los parámetros de envío no pueden ser vacíos";
    public static final String MENSAJE_TIPO_ENVIO_VACIO = "El tipo de envío no puede ser vacío";
    public static final String MENSAJE_TIPO_ENVIO_INVALIDO = "El tipo de envío es incorrecto";
    public static final String MENSAJE_ENVIADO = "Se envió el correo correctamente"; 
    public static final String MENSAJE_WRONG_DET_GENERAL = "No se encontraron parámetros para el tipo de envío"; 
    
    public static final String MENSAJE_WRONG_BODY = "El cuerpo del correo no es válido";
    public static final String MENSAJE_EMPTY_SENDER = "El correo del remitente no puede ser vacío";
    public static final String MENSAJE_WRONG_SENDER = "El correo del remitente es incorrecto";
    public static final String MENSAJE_EMPTY_PASS = "La clave del remitente no puede ser vacío";
    public static final String MENSAJE_EMPTY_LIST_TO = "La lista de destinatarios no puede ser vacía";
    public static final String MENSAJE_WRONG_TO = "EL correo de destinatario es incorrecto";
    public static final String MENSAJE_EMPTY_USR_OFF365 = "El usuario de envío por Office 365 no puede ser vacío";
    public static final String MENSAJE_EMPTY_PASS_OFF365 = "La contraseña de envío por Office 365 no puede ser vacía";
    
    public static final String MENSAJE_WRONG_DETAIL = "Dato de envío de correo en Base incorrecto";
    public static final String MENSAJE_OUT_LANG_OFF365 = "El archivo adjunto excedio el tamaño permitido";
    
    /* Para condicional de ws*/    
    public static final int TIPOWS_ENVIAR_EXCEPCION_RUTA = 1;
    public static final int TIPOWS_OBTENER_ENTREGA = 2;
    
    
    
 
}
