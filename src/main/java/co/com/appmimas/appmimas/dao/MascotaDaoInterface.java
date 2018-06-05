package co.com.appmimas.appmimas.dao;

import java.util.List;

import co.com.appmimas.appmimas.model.Mascota;


public interface MascotaDaoInterface {
    
    Integer insertar(Mascota mascota) throws Exception;
    
    Mascota consultar(Mascota mascota) throws Exception;
    
    int actualizar(Mascota mascota) throws Exception;
    
    int eliminar(Mascota mascota) throws Exception;
    
    List<Mascota> listar() throws Exception;
}
