package co.com.appmimas.appmimas.controller;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appmimas.appmimas.dao.MascotaDaoInterface;
import co.com.appmimas.appmimas.model.Mascota;

@CrossOrigin(origins = {"http://mimassite.github.io."}) 
@RestController
@RequestMapping(value = "/")
public class MascotaController {
	
	@Autowired
	private MascotaDaoInterface mascotaCrudInterface;
	
	@GetMapping("/saludo")
	public String saludo() {
		return  "hola";
	}
	
	@PostMapping("/mascotas")
    public Mascota registrarMascota(@RequestBody Mascota mascota)  {
		Mascota mascotaResponse = new Mascota();
		mascotaResponse.setCodigoRespuesta("500");
        try {                      
        	mascotaResponse = mascotaCrudInterface.consultar(mascota);
        	if(mascotaResponse.getNombre() == null || mascotaResponse.getNombre().equals("") ) {
            	Integer respueata = mascotaCrudInterface.insertar(mascota);
                if(respueata > 0){  
                	mascotaResponse.setId(respueata.toString());
                	mascotaResponse.setCodigoRespuesta("201");
                }else{
                	mascotaResponse.setCodigoRespuesta("202");
                }        		
        	}else {
        		mascotaResponse.setCodigoRespuesta("203");
        	}

            return mascotaResponse;
        } catch (Exception e) {
        	System.out.print(e.getMessage());
            return mascotaResponse;
        }
    }
	
	@PutMapping("/mascotas")
	public String actualizarMascota(@RequestBody Mascota mascota) throws JSONException  {
		String response = "500";
        try {    
            int respueata = mascotaCrudInterface.actualizar(mascota);
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
	
	@GetMapping("/mascotas/{id}")
	 public Mascota consultarMascota(@PathVariable String id) throws JSONException  {
		 Mascota respuestaMascota = new Mascota(); 
		 try {    
        	  Mascota mascota = new Mascota();
        	  mascota.setId(id);
              respuestaMascota = mascotaCrudInterface.consultar(mascota);           
              return respuestaMascota;
          } catch (Exception e) {
              e.printStackTrace();
              return respuestaMascota;
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
	
	@DeleteMapping("/mascotas/{id}")
	public String eliminarMascota(@PathVariable String id) throws JSONException  {
        String response = "500"; 
		try {
       	     Mascota mascota = new Mascota();
       	     mascota.setId(id);
             int respueata = mascotaCrudInterface.eliminar(mascota);
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
