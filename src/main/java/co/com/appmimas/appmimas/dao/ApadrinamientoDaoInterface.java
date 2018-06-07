package co.com.appmimas.appmimas.dao;

import java.util.List;

import co.com.appmimas.appmimas.model.Apadrinamiento;

public interface ApadrinamientoDaoInterface {
	
	Integer insertar(Object request) throws Exception;
    
	Apadrinamiento consultar(Object consultar) throws Exception;
    
    Object actualizar(Object consultar) throws Exception;
    
    Object eliminar(Object consultar) throws Exception;
    
    List<Apadrinamiento> listar() throws Exception;
}
