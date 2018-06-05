package co.com.appmimas.appmimas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        preparedStatement.executeUpdate();
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        respuesta  = genKeysRs.getInt(1);
        genKeysRs.close();
        preparedStatement.close();
        con.close();
		return respuesta;
	}

	@Override
	public Object consultar(Object consultar) throws Exception {
		return null;
	}

	@Override
	public Object actualizar(Object consultar) throws Exception {
		return null;
	}

	@Override
	public Object eliminar(Object consultar) throws Exception {
		return null;
	}

	@Override
	public List<Object> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
