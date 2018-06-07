package co.com.appmimas.appmimas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.appmimas.appmimas.model.Apadrinamiento;
import co.com.appmimas.appmimas.util.conexionDB;

@Repository
public class ApadrinamientoCrud implements ApadrinamientoDaoInterface {

	private Connection con = null;
	private PreparedStatement preparedStatement;

	@Override
	public Integer insertar(Object request) throws Exception {
		Integer respuesta = 0;
		Apadrinamiento apadrinamiento = (Apadrinamiento) request;
		apadrinamiento.setFecha(new Date());
		con = conexionDB.getConexion();
		preparedStatement = null;
		String sql = "Insert Into apadrinamiento(usuario,mascota,fecha,nombreadjunto,adjunto,estado)values(?,?,?,?,?,?)";
		preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, apadrinamiento.getUsuario());
		preparedStatement.setString(2, apadrinamiento.getIdMascota());
		preparedStatement.setDate(3, new java.sql.Date(0L));
		preparedStatement.setString(4, apadrinamiento.getNombreAdjunto());
		preparedStatement.setString(5, apadrinamiento.getAdjunto());
		preparedStatement.setString(6, "1");
        respuesta = preparedStatement.executeUpdate();  
        preparedStatement.close();
        con.close();
		return respuesta;
	}

	@Override
	public Apadrinamiento consultar(Object consultar) throws Exception {
        ResultSet respuesta;
        Apadrinamiento apadrinamiento = (Apadrinamiento) consultar;         
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "Select   usuario, mascota,fecha, nombreadjunto, adjunto, estado from apadrinamiento where idsolicitud = ?";       
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, apadrinamiento.getIdApadrinamiento());        
        respuesta = preparedStatement.executeQuery();
        Apadrinamiento apadrinamientoRespuesta = new Apadrinamiento(); 
        while(respuesta.next()){
        	apadrinamientoRespuesta.setUsuario(respuesta.getString("usuario"));
        	apadrinamientoRespuesta.setIdMascota(respuesta.getString("mascota"));
        	apadrinamientoRespuesta.setFecha(respuesta.getDate("fecha"));
        	apadrinamientoRespuesta.setNombreAdjunto(respuesta.getString("nombreadjunto"));
        	apadrinamientoRespuesta.setAdjunto(respuesta.getString("adjunto"));
        	apadrinamientoRespuesta.setEstadoSolicitud(respuesta.getString("estado"));
        }
        preparedStatement.close();
        con.close();        
        return apadrinamientoRespuesta;
	}
	
	@Override
	public Object actualizar(Object actualizar) throws Exception {
		int respuesta = 0;
		Apadrinamiento apadrinamiento = (Apadrinamiento) actualizar;        
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "update apadrinamiento set  mascota = ?, fecha = ? , nombreadjunto = ?, adjunto = ?, estado = ? where idsolicitud = ?";             
		preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, apadrinamiento.getIdMascota());
		preparedStatement.setDate(2, new java.sql.Date(0L));
		preparedStatement.setString(3, apadrinamiento.getNombreAdjunto());
		preparedStatement.setString(4, apadrinamiento.getAdjunto());
		preparedStatement.setString(5, apadrinamiento.getEstadoSolicitud());
		preparedStatement.setString(6, apadrinamiento.getIdApadrinamiento());
        respuesta = preparedStatement.executeUpdate();  
        preparedStatement.close();
        con.close();        
        return respuesta;
	}

	@Override
	public Object eliminar(Object consultar) throws Exception {
		return null;
	}

	@Override
	public List<Apadrinamiento> listar() throws Exception {
        ResultSet respuesta;
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "Select idsolicitud,  usuario, mascota,fecha, nombreadjunto, adjunto, estado from apadrinamiento";       
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        respuesta = preparedStatement.executeQuery();
        List<Apadrinamiento> listApadrinamiento = new ArrayList<Apadrinamiento>();
        Apadrinamiento miapadrinamiento = null;
        while(respuesta.next()){
        	miapadrinamiento = new Apadrinamiento();
        	miapadrinamiento.setIdApadrinamiento(respuesta.getString("idsolicitud"));
        	miapadrinamiento.setUsuario(respuesta.getString("usuario"));
        	miapadrinamiento.setIdMascota(respuesta.getString("mascota"));
        	miapadrinamiento.setFecha(respuesta.getDate("fecha"));
        	miapadrinamiento.setNombreAdjunto(respuesta.getString("nombreadjunto"));
        	miapadrinamiento.setAdjunto(respuesta.getString("adjunto"));
        	miapadrinamiento.setEstadoSolicitud(respuesta.getString("estado"));
        	listApadrinamiento.add(miapadrinamiento);
        }
        preparedStatement.close();
        con.close();        
        return listApadrinamiento;
	}

}