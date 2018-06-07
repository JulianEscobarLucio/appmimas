package co.com.appmimas.appmimas.dao;

import java.util.List;

import co.com.appmimas.appmimas.model.Evento;

public interface EventoDaoInterface {
	Integer insertar(Object request) throws Exception;
    
	Evento consultar(String id) throws Exception;
    
    Integer actualizar(Object consultar) throws Exception;
    
    List<Evento> listar() throws Exception;

	int eliminar(Evento evento) throws Exception;
}
