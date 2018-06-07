package co.com.appmimas.appmimas.dao;

import java.util.List;

import co.com.appmimas.appmimas.model.Adopcion;

public interface AdopcionDaoInterface {
	Integer insertar(Object request) throws Exception;
    
    Object consultar(Object consultar) throws Exception;
    
    Object actualizar(Object consultar) throws Exception;
    
    Object eliminar(Object consultar) throws Exception;
    
    List<Adopcion> listar() throws Exception;
}
