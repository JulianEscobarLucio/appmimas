package co.com.appmimas.appmimas.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appmimas.appmimas.dao.UsuarioDaoInterface;
import co.com.appmimas.appmimas.model.Usuario;

@RestController
@RequestMapping(value = "/")
public class UsuarioController {
	
	@Autowired
	UsuarioDaoInterface usuarioDaoInterface;
	
	@SuppressWarnings("finally")
	@PostMapping("/usuario")
    public String regisrtrarUsuario(@RequestBody  Usuario usuario) throws JSONException  {
		String response = "500";
        try { 
            Usuario respuestaUsuario = new Usuario();         
            respuestaUsuario = (Usuario) usuarioDaoInterface.consultar(usuario);
            if(respuestaUsuario.getNombre1() != null){  
            	response = "201";
            }else {
	            int respueata = usuarioDaoInterface.insertar(usuario);
	            if(respueata==1){  
	            	response = "200";
	            }else{
	            	response = "201"; 
	            }          
            } 
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }finally {
        	return response;
        }
    }

}
