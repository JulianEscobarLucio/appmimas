package co.com.appmimas.appmimas.dao;

import java.util.List;

public interface ApadrinamientoDaoInterface {
	
	Integer insertar(Object request) throws Exception;
    
    Object consultar(Object consultar) throws Exception;
    
    Object actualizar(Object consultar) throws Exception;
    
    Object eliminar(Object consultar) throws Exception;
    
    List<Object> listar() throws Exception;
}
