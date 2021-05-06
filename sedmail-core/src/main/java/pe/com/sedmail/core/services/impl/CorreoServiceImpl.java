package pe.com.sedmail.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedmail.core.dao.DetalleGeneralDao;
import pe.com.sedmail.core.entity.DetalleGeneral;
import pe.com.sedmail.core.entity.ParametrosCorreo;
import pe.com.sedmail.core.services.CorreoService;
import pe.com.sedmail.core.services.base.BaseServiceImpl;
import pe.com.sedmail.core.util.ConstanteServices;
import pe.com.sedmail.core.util.ConstantesParametros;
import pe.com.sedmail.core.util.NotificacionUtil;
import pe.com.sedmail.core.util.ValidatorUtil;

@Service
public class CorreoServiceImpl extends BaseServiceImpl<Object, Long> implements CorreoService{
		
	@Autowired
	DetalleGeneralDao detalleGeneralDao;
	
	@Override
	public Map<String, String> enviarCorreo(ParametrosCorreo parametrosEnvio) throws GmdException {
		Map<String, String> mapResultado = new HashMap<String, String>();
		try{
			List<DetalleGeneral> datosGeneralXTipo = listarDetalleGeneral(parametrosEnvio.getIdTipoEnvio() == ConstantesParametros.TIPO_ENVIO_SMTP_365 ? ConstantesParametros.TIPO_ENVIO_OFF_365 : parametrosEnvio.getIdTipoEnvio());
			mapResultado = ValidatorUtil.validarDetalleGeneral(datosGeneralXTipo);
			if(mapResultado.get("estadoRespuesta").equals(ConstanteServices.OK)){
				parametrosEnvio.setCuerpoHTML(NotificacionUtil.generarCuerpoCorreo(parametrosEnvio.getCuerpoHTML()));
				switch(parametrosEnvio.getIdTipoEnvio()){
//					configuracion servidor SMTP
					case ConstantesParametros.TIPO_ENVIO_SMTP:
						NotificacionUtil.enviarCorreoElectronicoSMTP(parametrosEnvio, datosGeneralXTipo);
						break;
					case ConstantesParametros.TIPO_ENVIO_OFF_365:
//					configuracion exchange office365
						NotificacionUtil.enviarCorreoElectronicoOff365(parametrosEnvio, datosGeneralXTipo);
						break;
//					caso de conexion al smtp del office libre -- DON'T WORK BECAUSE WRONG CONFIGURATION SEDAPAL
//					configuracion servidor SMTP
					case ConstantesParametros.TIPO_ENVIO_SMTP_365:
						NotificacionUtil.enviarCorreoElectronicoSMTP(parametrosEnvio, datosGeneralXTipo);
						break;
				}
				mapResultado.put("estadoRespuesta", ConstanteServices.OK);
			}else{
				return mapResultado;
			}
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return mapResultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DetalleGeneral buscarDetalleGeneral(Integer idDetGeneral, String estado) throws GmdException {
		DetalleGeneral detalleGeneral = new DetalleGeneral();
		try{
			Map<String, Object> mapBusqueda = new HashMap<String, Object>();
			mapBusqueda.put("PAR_N_ID_DET_GENERAL", idDetGeneral);
			mapBusqueda.put("PAR_V_ST_REGI", estado);
			detalleGeneralDao.buscarDetalleGeneral(mapBusqueda);
			List<DetalleGeneral> ltaDetalleGeneral = (List<DetalleGeneral>) mapBusqueda.get("PAR_OUT_CURSOR");
			detalleGeneral = (ltaDetalleGeneral != null && ltaDetalleGeneral.size() > 0) ? ltaDetalleGeneral.get(0) : null;
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return detalleGeneral;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneral> listarDetalleGeneral(Integer idGeneral) throws GmdException {
		List<DetalleGeneral> listaDetalleGeneral = new ArrayList<DetalleGeneral>();
		try{
			Map<String, Object> mapBusqueda = new HashMap<String, Object>();
			mapBusqueda.put("PAR_N_ID_GENERAL", idGeneral);
			detalleGeneralDao.listarDetalleGeneral(mapBusqueda);
			listaDetalleGeneral = (List<DetalleGeneral>) mapBusqueda.get("PAR_OUT_CURSOR");
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return listaDetalleGeneral;
	}
	
}
