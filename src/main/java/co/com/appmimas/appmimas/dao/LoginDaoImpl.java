package co.com.appmimas.appmimas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.appmimas.appmimas.model.Usuario;
import co.com.appmimas.appmimas.util.conexionDB;

@Repository
public class LoginDaoImpl implements LoginDaoInterface {
    Connection con;

	@Override
	public boolean login(Object consultar) throws Exception {
		Usuario usuario = (Usuario) consultar;
        boolean respuesta = false;
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "select nombre1, nombre2, apellido1, apellido2, telefonofijo, telefonomovil, email, pregunta, respuesta, contraseña"
                + " from usuario where email = ? and contraseña = ? ";
        preparedStatement  = con.prepareStatement(sql);
        preparedStatement.setString(1, usuario.getEmail());
        preparedStatement.setString(2, usuario.getContrasena());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
              respuesta = true;
              break;
        }        
        preparedStatement.close();
        con.close();        
        return respuesta;
	}
    

}
