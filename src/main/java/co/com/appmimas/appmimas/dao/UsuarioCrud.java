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
public class UsuarioCrud implements UsuarioDaoInterface {
    Connection con;

    @Override
    public int insertar(Object request) throws Exception {
        int respuesta = 0;
        Usuario usuario = (Usuario) request;         
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "insert into usuario (nombre1, nombre2, apellido1, apellido2, telefonofijo, telefonomovil, email, pregunta, respuesta, contraseña, rol,estado)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?) ";
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, usuario.getNombre1());
        preparedStatement.setString(2, usuario.getNombre2());
        preparedStatement.setString(3, usuario.getApellido1());
        preparedStatement.setString(4, usuario.getApellido2());
        preparedStatement.setString(5, usuario.getTelefonoFijo());
        preparedStatement.setString(6, usuario.getTelefonomovil());
        preparedStatement.setString(7, usuario.getEmail());
        preparedStatement.setString(8, usuario.getPregunta());
        preparedStatement.setString(9, usuario.getRespuesta());
        preparedStatement.setString(10, usuario.getContrasena());
        preparedStatement.setString(11, "4");
        preparedStatement.setString(12, "1");
        respuesta = preparedStatement .executeUpdate();
        preparedStatement.close();
        con.close();        
        return respuesta;
    }

    @Override
    public Object consultar(Object consultar) throws Exception {
        ResultSet respuesta;
        Usuario usuario = (Usuario) consultar;         
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "Select nombre1, nombre2, apellido1, apellido2, telefonofijo, telefonomovil, pregunta, respuesta, contraseña, rol, estado from usuario where email = ?";
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, usuario.getEmail());        
        respuesta = preparedStatement.executeQuery();
        Usuario usuarioRespuesta = new Usuario(); 
        while(respuesta.next()){
            usuarioRespuesta.setNombre1(respuesta.getString("nombre1"));
            usuarioRespuesta.setNombre2(respuesta.getString("nombre2"));
            usuarioRespuesta.setApellido1(respuesta.getString("apellido1"));
            usuarioRespuesta.setApellido2(respuesta.getString("apellido2"));
            usuarioRespuesta.setTelefonoFijo(respuesta.getString("telefonofijo"));
            usuarioRespuesta.setTelefonomovil(respuesta.getString("telefonomovil"));
            usuarioRespuesta.setPregunta(respuesta.getString("pregunta"));
            usuarioRespuesta.setRespuesta(respuesta.getString("respuesta"));
            usuarioRespuesta.setContrasena(respuesta.getString("contraseña")); 
            usuarioRespuesta.setRol(respuesta.getString("rol"));
            usuarioRespuesta.setEstado(respuesta.getString("estado"));
        }
        preparedStatement.close();
        con.close();        
        return usuarioRespuesta;
    }

    @Override
    public Object actualizar(Object actualizar) throws Exception {
        int respuesta = 0;
        Usuario usuario = (Usuario) actualizar;         
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "Update usuario set  nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2  = ?,  telefonofijo = ?"
                +", telefonomovil = ?, pregunta = ?, respuesta = ?, contraseña = ?, rol = ?, estado = ?  where email = ?"; 
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);      
        preparedStatement.setString(1, usuario.getNombre1());
        preparedStatement.setString(2, usuario.getNombre2());
        preparedStatement.setString(3, usuario.getApellido1());
        preparedStatement.setString(4, usuario.getApellido2()); 
        preparedStatement.setString(5, usuario.getTelefonoFijo());
        preparedStatement.setString(6, usuario.getTelefonomovil());
        preparedStatement.setString(7, usuario.getPregunta());
        preparedStatement.setString(8, usuario.getRespuesta());
        preparedStatement.setString(9, usuario.getContrasena());  
        preparedStatement.setString(10, usuario.getRol());
        preparedStatement.setString(11, usuario.getEstado());
        preparedStatement.setString(12, usuario.getEmail());       
        respuesta = preparedStatement.executeUpdate();  
        preparedStatement.close();
        con.close();        
        return respuesta;
    }

    @Override
    public Object eliminar(Object eliminar) throws Exception {
        int respuesta = 0;
        Usuario usuario = (Usuario) eliminar;         
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "delete from usuario  where email = ?";        
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);      
        preparedStatement.setString(1, usuario.getEmail());
        respuesta = preparedStatement.executeUpdate();  
        preparedStatement.close();
        con.close();        
        return respuesta;
    }

	@Override
	public List<Object> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
