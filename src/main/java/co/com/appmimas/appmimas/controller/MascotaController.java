package co.com.appmimas.appmimas.controller;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appmimas.appmimas.dao.MascotaDaoInterface;
import co.com.appmimas.appmimas.model.Mascota;


@RestController
@RequestMapping(value = "/")
public class MascotaController {
	
	@Autowired
	private MascotaDaoInterface mascotaCrudInterface;
	
	@GetMapping("/saludo")
	public String saludo() {
		return  "hola";
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/mascotas")
    public JSONArray registrarMascota(Mascota mascota)  {
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        try {                      
            int respueata = mascotaCrudInterface.insertar(mascota);
            if(respueata > 0){  
               jo.put("codRespuesta", "201");
               jo.put("idMascota", respueata);
               jo.put("respuesta", "Mascota registrada");
            }else{
               jo.put("codRespuesta", "202");
               jo.put("respuesta", "Mascota no registrada");   
            };            
        } catch (Exception e) {
        	System.out.print(e.getMessage());
        	jo.put("codRespuesta", "203");
        }finally {
        	ja.put(jo);
        	return ja;
        }

    }
	
	@GetMapping("/mascotas")
    public List<Mascota> listaMascota(){
		try {
			return mascotaCrudInterface.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

}
