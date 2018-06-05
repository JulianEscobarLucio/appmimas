package co.com.appmimas.appmimas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.com.appmimas.appmimas.model.Mascota;
import co.com.appmimas.appmimas.util.conexionDB;

@Repository
public class MascotaDaoImpl implements MascotaDaoInterface {
    Connection con;

    @Override
    public Integer insertar(Mascota mascota) throws Exception {
    	Integer respuesta = 0;
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "insert into mascota (Nombre, IdResponsable, Especie, Raza, Genero,imagen, Tamaño, "
                + "Estado, Caracteristicas, FechaN, Señales, Color, Colorojos, Personalidad, EstadoSalud)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, mascota.getNombre());
        preparedStatement.setString(2, mascota.getIdResponsable());
        preparedStatement.setString(3, mascota.getEspecie());
        preparedStatement.setString(4, mascota.getRaza());
        preparedStatement.setString(5, mascota.getGenero());
        preparedStatement.setString(6, mascota.getImagen());
        preparedStatement.setString(7, mascota.getTamano());
        preparedStatement.setString(8, mascota.getEstado());
        preparedStatement.setString(9, mascota.getCaracteristicas());
        preparedStatement.setString(10, mascota.getFechaN());
        preparedStatement.setString(11, mascota.getSenales());
        preparedStatement.setString(12, mascota.getColor());
        preparedStatement.setString(13, mascota.getColorojos());
        preparedStatement.setString(14, mascota.getPersonalidad());
        preparedStatement.setString(15, mascota.getEstadoSalud());
        preparedStatement .executeUpdate();
        ResultSet genKeysRs = preparedStatement.getGeneratedKeys();
        genKeysRs.next();
        respuesta  = genKeysRs.getInt(1);
        genKeysRs.close();
        preparedStatement.close();
        con.close();
        return respuesta;
    }

    @Override
    public Mascota consultar(Mascota mascota) throws Exception {
        ResultSet respuesta;
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "Select   Nombre, IdResponsable,Imagen, Especie, Raza, Genero, Tamaño, "
                + "Estado, Caracteristicas, FechaN, Señales, Color, Colorojos, Personalidad, EstadoSalud from mascota where Id = ?";       
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, mascota.getId());        
        respuesta = preparedStatement.executeQuery();
        Mascota mascotaRespuesta = new Mascota(); 
        while(respuesta.next()){
        	mascotaRespuesta.setId(mascota.getId());
            mascotaRespuesta.setNombre(respuesta.getString("Nombre"));
            mascotaRespuesta.setIdResponsable(respuesta.getString("IdResponsable"));
            mascotaRespuesta.setEspecie(respuesta.getString("Especie"));
            mascotaRespuesta.setRaza(respuesta.getString("Raza"));
            mascotaRespuesta.setGenero(respuesta.getString("Genero"));  
            mascotaRespuesta.setTamano(respuesta.getString("Tamaño"));
            mascotaRespuesta.setEstado(respuesta.getString("Estado"));
            mascotaRespuesta.setCaracteristicas(respuesta.getString("Caracteristicas"));            
            mascotaRespuesta.setFechaN(respuesta.getString("FechaN"));
            mascotaRespuesta.setSenales(respuesta.getString("Señales"));
            mascotaRespuesta.setColor(respuesta.getString("Color"));
            mascotaRespuesta.setColorojos(respuesta.getString("Colorojos"));
            mascotaRespuesta.setPersonalidad(respuesta.getString("Personalidad"));
            mascotaRespuesta.setEstadoSalud(respuesta.getString("EstadoSalud"));
            mascotaRespuesta.setImagen(respuesta.getString("Imagen"));
        }
        preparedStatement.close();
        con.close();        
        return mascotaRespuesta;
    }

    @Override
    public int actualizar(Mascota mascota) throws Exception {
        int respuesta = 0;
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "update mascota set  Nombre = ?, IdResponsable = ? , Especie = ?, Raza = ?, Genero = ?, Imagen= ?, Tamaño= ?, "
                + "Estado = ?, Caracteristicas = ?, FechaN = ?, Señales = ?, Color = ?, Colorojos = ?, Personalidad = ?, EstadoSalud = ? where Id = ?";             
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);      
        preparedStatement.setString(1, mascota.getNombre());
        preparedStatement.setString(2, mascota.getIdResponsable());
        preparedStatement.setString(3, mascota.getEspecie());
        preparedStatement.setString(4, mascota.getRaza());
        preparedStatement.setString(5, mascota.getGenero());
        preparedStatement.setString(6, mascota.getImagen());
        preparedStatement.setString(7, mascota.getTamano());
        preparedStatement.setString(8, mascota.getEstado());
        preparedStatement.setString(9, mascota.getCaracteristicas());
        preparedStatement.setString(10, mascota.getFechaN());
        preparedStatement.setString(11, mascota.getSenales());
        preparedStatement.setString(12, mascota.getColor());
        preparedStatement.setString(13, mascota.getColorojos());
        preparedStatement.setString(14, mascota.getPersonalidad());
        preparedStatement.setString(15, mascota.getEstadoSalud());
        preparedStatement.setString(16, mascota.getId());
        respuesta = preparedStatement.executeUpdate();  
        preparedStatement.close();
        con.close();        
        return respuesta;
    }

    @Override
    public int eliminar(Mascota mascota) throws Exception {
        int respuesta = 0;
        con = conexionDB.getConexion(); 
        PreparedStatement preparedStatement = null ;
        String sql = "delete from mascota  where Id = ?";        
        preparedStatement  = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);      
        preparedStatement.setString(1, mascota.getId());
        respuesta = preparedStatement.executeUpdate();  
        preparedStatement.close();
        con.close();        
        return respuesta;
    }

	@Override
	public List<Mascota> listar() throws Exception {
		List<Mascota> listMascota = new ArrayList<Mascota>();
		Mascota mascota = null;
		con = conexionDB.getConexion();
		PreparedStatement preparedStatement = con.prepareStatement("select  id, nombre, idresponsable, especie, raza, genero,"
				+ " imagen, tamaño, estado, caracteristicas, fechan, señales, color, colorojos, personalidad, "
				+ "estadosalud from mascota", Statement.RETURN_GENERATED_KEYS);  
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			mascota = new Mascota();
			mascota.setId(resultSet.getString("id"));
			mascota.setNombre(resultSet.getString("nombre"));
			mascota.setIdResponsable(resultSet.getString("idresponsable"));
			mascota.setEspecie(resultSet.getString("especie"));
			mascota.setRaza(resultSet.getString("raza"));
			mascota.setGenero(resultSet.getString("genero"));
			mascota.setImagen(resultSet.getString("imagen"));
			mascota.setTamano(resultSet.getString("tamaño"));
			mascota.setEstado(resultSet.getString("estado"));
			mascota.setCaracteristicas(resultSet.getString("caracteristicas"));
			mascota.setFechaN(resultSet.getString("fechan"));
			mascota.setSenales(resultSet.getString("señales"));
			mascota.setColor(resultSet.getString("color"));
			mascota.setColorojos(resultSet.getString("colorojos"));
			mascota.setPersonalidad(resultSet.getString("personalidad"));
			mascota.setEstadoSalud(resultSet.getString("estadosalud"));
			listMascota.add(mascota);
		}
		return listMascota;
	}


}
