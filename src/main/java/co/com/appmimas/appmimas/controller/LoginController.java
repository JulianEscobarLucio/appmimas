package co.com.appmimas.appmimas.controller;


import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.appmimas.appmimas.dao.LoginDaoInterface;
import co.com.appmimas.appmimas.dao.UsuarioDaoInterface;
import co.com.appmimas.appmimas.model.Usuario;

@CrossOrigin(origins = {"http://localhost:8090"}) 
@RestController
@RequestMapping(value = "/")
public class LoginController {
	
	@Autowired
	LoginDaoInterface loginDaoInterface;
	
	@Autowired
	UsuarioDaoInterface usuarioDaoInterface;
	
	@SuppressWarnings("finally")
	@PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) throws JSONException {
		Usuario miUsuario = new Usuario();
        try {
            boolean respuesta = (boolean) loginDaoInterface.login(usuario);
            if (respuesta) {
                miUsuario = (Usuario) usuarioDaoInterface.consultar(usuario);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	return miUsuario;
        }
    }

}
