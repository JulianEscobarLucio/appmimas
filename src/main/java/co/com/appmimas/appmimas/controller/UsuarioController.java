package co.com.appmimas.appmimas.controller;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.appmimas.appmimas.dao.UsuarioDaoInterface;
import co.com.appmimas.appmimas.model.Usuario;

@CrossOrigin(origins = "*",maxAge = 4600)
@RestController
@RequestMapping(value = "/")
public class UsuarioController {
	
	@Autowired
	UsuarioDaoInterface usuarioDaoInterface;
	
	@SuppressWarnings("finally")
	@PostMapping("/usuario")
    public String regisrtrarUsuario(@RequestBody Usuario usuario) throws JSONException  {
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
	            }          
            } 
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	response = "500";
        }finally {
        	return response;
        }
    }
	
	@PostMapping("/consultarusuario")
    public Usuario consultarUsuario(@RequestBody Usuario usuario) throws JSONException  {
        Usuario respuestaUsuario = new Usuario();  
        try {    
            respuestaUsuario = (Usuario) usuarioDaoInterface.consultar(usuario);
            return respuestaUsuario;
        } catch (Exception e) {
            e.printStackTrace();
            return respuestaUsuario;
        }
    }
	
	@PutMapping("/usuario")
	public String actualizarUsuario(@RequestBody Usuario usuario) throws JSONException  {
		String response = "500";
		try {    
            int respueata = (int) usuarioDaoInterface.actualizar(usuario);
            if(respueata==1){  
            	response = "200";
            }else{
            	response = "201";   
            };          
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
    }
	
	
	@DeleteMapping("/usuario/{email}")
    public String eliminarUsuario(@PathVariable String email) throws JSONException  {
		String response = "500";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
		try {                        
            int respueata = (int) usuarioDaoInterface.eliminar(usuario);
            if(respueata==1){  
            	response = "200";
            }else{
            	response = "201";
            };          
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
    }

}
