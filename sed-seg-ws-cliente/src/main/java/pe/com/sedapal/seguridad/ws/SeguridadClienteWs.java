package pe.com.sedapal.seguridad.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;

@PropertySource("classpath:pe/com/sedapal/seguridad/ws/config/config.properties")
public class SeguridadClienteWs {

	@Value("#{config['urlws']}")
	private String urlWs = "http://192.168.150.200:8181/webserviceserguridad/seguridadws?wsdl";
	private WebServiceTemplate webServiceTemplate;

	public WebServiceTemplate getWebServiceTemplate() {
		return webServiceTemplate;
	}

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	public Retorno olvidarClaveWs(String usuario) {
		String xmlRequest = this.getPeticionOlvidarClaveWs(usuario);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}

	public Retorno autenticacionUsuarioWs(String usuario, int codSistema,String clave) {
		String xmlRequest = this.getPeticionAutenticacionUsuarioWs(usuario, codSistema,clave);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}
	
	public Retorno logoutWs(String token) {
		String xmlRequest = this.getPeticionLogoutWs(token);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;
		
		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}

	public Retorno autenticacionUsuarioCompletaWs(String usuario, String ip, String token, int codSistema) {
		String xmlRequest = this.getPeticionAutenticacionUsuarioCompletaWs(usuario, ip, token, codSistema);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				System.out.println("****************************************************************************");
				System.out.println("****************************************************************************");
				System.out.println("******************* AUTENTICACION USUARIO COMPLETA WS **********************");
				System.out.println(respuesta);
				retorno = resultToRetorno((Document) respuesta.getNode());
				System.out.println(respuesta.getNode().toString());
				System.out.println(retorno);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}

	public List<SistemaModuloOpcionBean> obtenerMenueWs(String usuario, int codSistema) {
		String xmlRequest = this.getMenuClaveWs(usuario, codSistema);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		List<SistemaModuloOpcionBean> retorno = new ArrayList<SistemaModuloOpcionBean>();

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetornoMenu((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		return retorno;
	}

	public Retorno actualizarClaveWs(String usuario, String claveActual, String nuevaClave, String nuevaClaveR) {
		String xmlRequest = this.getPeticionActualizarClaveWs(usuario, claveActual, nuevaClave, nuevaClaveR);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}
	
	
	public List<String> obtenerPermisosWs(String usuario) {
		String xmlRequest = this.getPeticionPermisosWs(usuario);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		List<String> retorno = new ArrayList<String>();

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetornoPerfiles((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		return retorno;
	}

	private String getPeticionAutenticacionUsuarioWs(String usuario, int codSistema,String clave) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:autenticacionUsuarioWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("<seg:clave>" + clave + "</seg:clave>");
		buffer.append("</seg:autenticacionUsuarioWs>");

		return buffer.toString();
	}
	
	public String getPeticionLogoutWs(String token) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:logoutWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:token>" + token + "</seg:token>");
		buffer.append("</seg:logoutWs>");
		
		return buffer.toString();
	}

	private String getPeticionAutenticacionUsuarioCompletaWs(String usuario, String ip, String token, int codSistema) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:autenticacionUsuarioCompletaWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:ip>" + ip + "</seg:ip>");
		buffer.append("<seg:token>" + token + "</seg:token>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("</seg:autenticacionUsuarioCompletaWs>");

		return buffer.toString();
	}

	private String getPeticionOlvidarClaveWs(String usuario) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:olvidarClaveWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("</seg:olvidarClaveWs>");

		return buffer.toString();
	}

	private String getPeticionActualizarClaveWs(String usuario, String claveActual, String nuevaClave,
			String nuevaClaveR) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:actualizarClaveWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:claveActual>" + claveActual + "</seg:claveActual>");
		buffer.append("<seg:nuevaClave>" + nuevaClave + "</seg:nuevaClave>");
		buffer.append("<seg:nuevaClaveR>" + nuevaClaveR + "</seg:nuevaClaveR>");
		buffer.append("</seg:actualizarClaveWs>");

		return buffer.toString();
	}

	private String getMenuClaveWs(String usuario, int codSistema) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerMenuWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("</seg:obtenerMenuWs>");
		return buffer.toString();
	}
	
	private String getPeticionPermisosWs(String usuario) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerPermisosWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");		
		buffer.append("</seg:obtenerPermisosWs>");
		return buffer.toString();
	}
	
	
	private Retorno resultToRetorno(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;
		Retorno retorno = null;

		retorno = new Retorno();
		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);
			if (this.getProperty(current, "codigo") != null) {
				retorno.setCodigo(this.getProperty(current, "codigo"));
			}

			if (this.getProperty(current, "mensaje") != null) {
				retorno.setMensaje(this.getProperty(current, "mensaje"));
			}

			if (this.getProperty(current, "tipo") != null) {
				retorno.setTipo(this.getProperty(current, "tipo"));
			}
			if (this.getProperty(current, "usuario") != null) {
				retorno.setUsuario(this.getProperty(current, "usuario"));
			}
			if (this.getProperty(current, "clave") != null) {
				retorno.setClave(this.getProperty(current, "clave"));
			}

			if (this.getProperty(current, "flagCambiarClave") != null) {
				retorno.setFlagCambiarClave(this.getProperty(current, "flagCambiarClave"));
			}
			if (this.getProperty(current, "ultimoAcceso") != null) {
				retorno.setUltimoAcceso(this.getProperty(current, "ultimoAcceso"));
			}
			/*
			 * if (this.getProperty(current, "permisos") != null) {
			 * retorno.setPermisos(this.getProperty(current, "permisos")); } if
			 * (this.getProperty(current, "menu") != null) {
			 * retorno.setObject(this.getProperty(current, "menu")); }
			 */
//			inicio adecuacion seguridad2
			if (this.getProperty(current, "autenticacion") != null) {
				retorno.setAutenticacion(this.getProperty(current, "autenticacion"));
			}
			if (this.getProperty(current, "numPerfilesAct") != null) {
				retorno.setNumPerfilesAct(Integer.valueOf(this.getProperty(current, "numPerfilesAct")));
			}
			if (this.getProperty(current, "token") != null) {
				retorno.setToken(this.getProperty(current, "token"));
			}
			
			Node nodoPerfiles = null;
			NodeList nodosAlter = current.getChildNodes();
			List<PerfilSistemaBean> perfiles = new ArrayList<PerfilSistemaBean>();
			for(int k=0; k < nodosAlter.getLength(); k++) {
				if(nodosAlter.item(k).getNodeName().equals("perfilesAct")) {
					nodoPerfiles = nodosAlter.item(k);
					PerfilSistemaBean perfil = new PerfilSistemaBean();
					System.out.println("tama�oNodo: " + nodoPerfiles.getChildNodes().getLength());
					if (this.getProperty(nodoPerfiles, "codPerfil") != null) {
						perfil.setCodPerfil(Integer.valueOf(this.getProperty(nodoPerfiles, "codPerfil")));
					}
					if (this.getProperty(nodoPerfiles, "codSistema") != null) {
						perfil.setCodSistema(Integer.valueOf(this.getProperty(nodoPerfiles, "codSistema")));
					}
					if (this.getProperty(nodoPerfiles, "descripcion") != null) {
						perfil.setDescripcion(this.getProperty(nodoPerfiles, "descripcion"));
					}
					if (this.getProperty(nodoPerfiles, "estado") != null) {
						perfil.setEstado(this.getProperty(nodoPerfiles, "estado"));
					}
					if (this.getProperty(nodoPerfiles, "estadoNombre") != null) {
						perfil.setEstadoNombre(this.getProperty(nodoPerfiles, "estadoNombre"));
					}
					if (this.getProperty(nodoPerfiles, "sistemaNombre") != null) {
						perfil.setSistemaNombre(this.getProperty(nodoPerfiles, "sistemaNombre"));
					}
					perfiles.add(perfil);
				}
			}
			if(perfiles.size() != 0) {
				retorno.setPerfilesAct(perfiles);
			}
//			fin adecuacion seguridad2
		}
		return retorno;
	}

	private List<SistemaModuloOpcionBean> resultToRetornoMenu(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;
		SistemaModuloOpcionBean sistemaModuloOpcionBean = null;
		List<SistemaModuloOpcionBean> lista = new ArrayList<SistemaModuloOpcionBean>();

		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);
			sistemaModuloOpcionBean = new SistemaModuloOpcionBean();

			if (this.getProperty(current, "codFormulario") != null) {
				sistemaModuloOpcionBean.setCodFormulario(new Integer(this.getProperty(current, "codFormulario")));
			}
			if (this.getProperty(current, "codFormularioPadre") != null) {
				sistemaModuloOpcionBean
						.setCodFormularioPadre(new Integer(this.getProperty(current, "codFormularioPadre")));
			}
			if (this.getProperty(current, "codModulo") != null) {
				sistemaModuloOpcionBean.setCodModulo(new Integer(this.getProperty(current, "codModulo")));
			}
			if (this.getProperty(current, "codPerfil") != null) {
				sistemaModuloOpcionBean.setCodPerfil(new Integer(this.getProperty(current, "codPerfil")));
			}

			if (this.getProperty(current, "codSistema") != null) {
				sistemaModuloOpcionBean.setCodSistema(new Integer(this.getProperty(current, "codSistema")));
			}
			if (this.getProperty(current, "descripcion") != null) {
				sistemaModuloOpcionBean.setDescripcion(this.getProperty(current, "descripcion"));
			}
			if (this.getProperty(current, "estado") != null) {
				sistemaModuloOpcionBean.setEstado(new Integer(this.getProperty(current, "estado")));
			}
			if (this.getProperty(current, "estadoNombre") != null) {
				sistemaModuloOpcionBean.setEstadoNombre(this.getProperty(current, "estadoNombre"));
			}
			if (this.getProperty(current, "moduloNombre") != null) {
				sistemaModuloOpcionBean.setModuloNombre(this.getProperty(current, "moduloNombre"));
			}
			if (this.getProperty(current, "nivelFormulario") != null) {
				sistemaModuloOpcionBean.setNivelFormulario(new Integer(this.getProperty(current, "nivelFormulario")));
			}
			if (this.getProperty(current, "ordenFormulario") != null) {
				sistemaModuloOpcionBean.setOrdenFormulario(new Integer(this.getProperty(current, "ordenFormulario")));
			}
			if (this.getProperty(current, "perfilNombre") != null) {
				sistemaModuloOpcionBean.setPerfilNombre(this.getProperty(current, "perfilNombre"));
			}
			if (this.getProperty(current, "sistemaNombre") != null) {
				sistemaModuloOpcionBean.setSistemaNombre(this.getProperty(current, "sistemaNombre"));
			}

			if (this.getProperty(current, "urlFormulario") != null) {
				sistemaModuloOpcionBean.setUrlFormulario(this.getProperty(current, "urlFormulario"));
			}

			lista.add(sistemaModuloOpcionBean);

		}

		return lista;
	}
	
	
	private List<String> resultToRetornoPerfiles(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;		
		List<String> lista = new ArrayList<String>();
		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);			
			if (current.getFirstChild().getNodeValue() != null) {
				lista.add(current.getFirstChild().getNodeValue());
			}
		}

		return lista;
	}
	
	
	/**
	 * 
	 * @param node
	 *            Nodo padre en el cual se busca la propiedad
	 * @param tag
	 *            Nombre del elemento buscado
	 * @return Devuelve el valor de un elemento <padre><tag1>xxx<tag1><tagN>xxx
	 *         <tagN></padre>
	 */
	private String getProperty(Node nodo, String tag) {
		NodeList props = nodo.getChildNodes();
		Node prop = null;
		String value = null;

		for (int j = 0, num = props.getLength(); j < num; j++) {
			prop = props.item(j);
			if (prop.getFirstChild()!= null && tag.equals(prop.getNodeName())) {
				value = prop.getFirstChild().getNodeValue();
				break;
			}
		}
		return value;
	}
		
	public String getUrlWs() {
		return urlWs;
	}

	public void setUrlWs(String urlWs) {
		this.urlWs = urlWs;
	}
	
//	inicio adecuacion seguridad2
	public Retorno autenticacionUsuarioActWs(String usuario, int codSistema, String clave) {
		String xmlRequest = this.getPeticionAutenticacionUsuarioActWs(usuario, codSistema,clave);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}
	
	private String getPeticionAutenticacionUsuarioActWs(String usuario, int codSistema,String clave) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:autenticacionUsuarioActWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("<seg:clave>" + clave + "</seg:clave>");
		buffer.append("</seg:autenticacionUsuarioActWs>");

		return buffer.toString();
	}
	
	public Retorno autenticacionUsuarioCompletaActWs(String usuario, String ip, String token, int codSistema, int codPerfil) {
		String xmlRequest = this.getPeticionAutenticacionUsuarioCompletaActWs(usuario, ip, token, codSistema, codPerfil);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				System.out.println("****************************************************************************");
				System.out.println("****************************************************************************");
				System.out.println("******************* AUTENTICACION USUARIO COMPLETA WS **********************");
				System.out.println(respuesta);
				retorno = resultToRetorno((Document) respuesta.getNode());
				System.out.println(respuesta.getNode().toString());
				System.out.println(retorno);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}
	
	private String getPeticionAutenticacionUsuarioCompletaActWs(String usuario, String ip, String token, int codSistema, int codPerfil) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:autenticacionUsuarioCompletaActWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:ip>" + ip + "</seg:ip>");
		buffer.append("<seg:token>" + token + "</seg:token>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("<seg:codPerfil>" + codPerfil + "</seg:codPerfil>");
		buffer.append("</seg:autenticacionUsuarioCompletaActWs>");

		return buffer.toString();
	}
	
	public List<SistemaModuloOpcionBean> obtenerMenuActWs(String usuario, int codSistema, int codPerfil) {
		String xmlRequest = this.getMenuClaveActWs(usuario, codSistema, codPerfil);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		List<SistemaModuloOpcionBean> retorno = new ArrayList<SistemaModuloOpcionBean>();

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetornoMenu((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		return retorno;
	}
	
	private String getMenuClaveActWs(String usuario, int codSistema, int codPerfil) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerMenuActWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("<seg:codUsuario>" + usuario + "</seg:codUsuario>");
		buffer.append("<seg:codPerfil>" + codPerfil + "</seg:codPerfil>");
		buffer.append("</seg:obtenerMenuActWs>");
		return buffer.toString();
	}
	
	public List<String> obtenerPermisosActWs(String codUsuario, Integer codSistema, Integer codPerfil) {
		String xmlRequest = this.getPeticionPermisosActWs(codUsuario, codSistema, codPerfil);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		List<String> retorno = new ArrayList<String>();

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetornoPerfiles((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		return retorno;
	}
	
	private String getPeticionPermisosActWs(String codUsuario, Integer codSistema, Integer codPerfil) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerPermisosActWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:codUsuario>" + codUsuario + "</seg:codUsuario>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("<seg:codPerfil>" + codPerfil + "</seg:codPerfil>");
		buffer.append("</seg:obtenerPermisosActWs>");
		return buffer.toString();
	}
	
	public Retorno validarTokenWs(String token) {
		String xmlRequest = this.getPeticionValidarTokenWs(token);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;
		
		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion = " + e.getMessage());
		}
		return retorno;
	}
	
	private String getPeticionValidarTokenWs(String token) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:validarTokenWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:token>" + token + "</seg:token>");
		buffer.append("</seg:validarTokenWs>");
		return buffer.toString();
	}
	
	public TrabajadorBean obtenerTrabajadorWs(String codUsuario) {
		String xmlRequest = this.getPeticionObtenerTrabajadorWs(codUsuario);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		TrabajadorBean trabajador = null;
		
		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				trabajador = resultToTrabajador((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			trabajador = new TrabajadorBean("0", "error al realizar la operacion = " + e.getMessage());
		}
		return trabajador;
	}
	
	private String getPeticionObtenerTrabajadorWs(String codUsuario) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerTrabajadorWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:codUsuario>" + codUsuario + "</seg:codUsuario>");
		buffer.append("</seg:obtenerTrabajadorWs>");
		return buffer.toString();
	}
	
	private TrabajadorBean resultToTrabajador(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;
		TrabajadorBean trabajador = null;

		trabajador = new TrabajadorBean();
		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);
			if (this.getProperty(current, "descArea") != null) {
				trabajador.setDescArea(this.getProperty(current, "descArea"));
			}
			if (this.getProperty(current, "ind_email") != null) {
				trabajador.setInd_email(this.getProperty(current, "ind_email"));
			}
			if (this.getProperty(current, "nCodArea") != null) {
				trabajador.setnCodArea(Integer.valueOf(this.getProperty(current, "nCodArea")));
			}
			if (this.getProperty(current, "nCodTipo") != null) {
				trabajador.setnCodTipo(Integer.valueOf(this.getProperty(current, "nCodTipo")));
			}
			if (this.getProperty(current, "nCodTrabajador") != null) {
				trabajador.setnCodTrabajador(Integer.valueOf(this.getProperty(current, "nCodTrabajador")));
			}
			if (this.getProperty(current, "nFicha") != null) {
				trabajador.setnFicha(Integer.valueOf(this.getProperty(current, "nFicha")));
			}
			if (this.getProperty(current, "nSubArea") != null) {
				trabajador.setnSubArea(Integer.valueOf(this.getProperty(current, "nSubArea")));
			}
			if (this.getProperty(current, "vApeMaterno") != null) {
				trabajador.setvApeMaterno(this.getProperty(current, "vApeMaterno"));
			}
			if (this.getProperty(current, "vApePaterno") != null) {
				trabajador.setvApePaterno(this.getProperty(current, "vApePaterno"));
			}
			if (this.getProperty(current, "vAreaPersonal") != null) {
				trabajador.setvAreaPersonal(this.getProperty(current, "vAreaPersonal"));
			}
			if (this.getProperty(current, "vCargo") != null) {
				trabajador.setvCargo(this.getProperty(current, "vCargo"));
			}
			if (this.getProperty(current, "vCodEstado") != null) {
				trabajador.setvCodEstado(this.getProperty(current, "vCodEstado"));
			}
			if (this.getProperty(current, "vCodTipo") != null) {
				trabajador.setvCodTipo(this.getProperty(current, "vCodTipo"));
			}
			if (this.getProperty(current, "vDirelectronica") != null) {
				trabajador.setvDirelectronica(this.getProperty(current, "vDirelectronica"));
			}
			if (this.getProperty(current, "vDni") != null) {
				trabajador.setvDni(this.getProperty(current, "vDni"));
			}
			if (this.getProperty(current, "vNombres") != null) {
				trabajador.setvNombres(this.getProperty(current, "vNombres"));
			}
			if (this.getProperty(current, "vUbicacion") != null) {
				trabajador.setvUbicacion(this.getProperty(current, "vUbicacion"));
			}
		}
		return trabajador;
	}
//	fin adecuacion seguridad2
	
}
