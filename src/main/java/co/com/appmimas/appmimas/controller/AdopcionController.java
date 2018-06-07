package co.com.appmimas.appmimas.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.appmimas.appmimas.dao.AdopcionDaoInterface;
import co.com.appmimas.appmimas.dao.MascotaDaoInterface;
import co.com.appmimas.appmimas.model.Adopcion;
import co.com.appmimas.appmimas.model.Mascota;

@CrossOrigin(origins = "*",maxAge = 4600)
@RestController
@RequestMapping(value = "/")
public class AdopcionController {
   
	@Autowired
	AdopcionDaoInterface adopcionDaoInterface;
	
	@Autowired
	private MascotaDaoInterface mascotaCrudInterface;
	
	@PostMapping("/adopciones")
	public Adopcion registrarSolicitud(@RequestBody Adopcion adopcion)  {
		Adopcion adopcionResponse = new Adopcion();
		adopcionResponse.setCodigoRespuesta("501"); 
		try {               
        	Mascota mascota = new Mascota();
        	mascota.setId(adopcion.getIdMascota());
        	mascota = mascotaCrudInterface.consultar(mascota);
        	if (mascota.getId() != null) {	        	        	
	            Integer respueata = adopcionDaoInterface.insertar(adopcion);
	            if(respueata > 0){ 
	            	adopcionResponse.setIdAdopcion(respueata.toString()); 
	            	adopcionResponse.setCodigoRespuesta("200"); 
	            }else{
	            	adopcionResponse.setCodigoRespuesta("201");
	            }
        	}
            return adopcionResponse;
        } catch (Exception e) {
        	 System.out.println(e.getMessage());
             return adopcionResponse;
        }
	}
	
	@GetMapping("/adopciones")
	public List<Adopcion> listarSolicitud() throws JSONException  {
		List<Adopcion> listaSolicitud = new ArrayList<>();
        try {  
            listaSolicitud  =  adopcionDaoInterface.listar();
            return listaSolicitud;
        } catch (Exception e) {
            return listaSolicitud;
        }
    }
	
	@GetMapping("/adopciones/{id}")
    public Adopcion consultarSolicitud(@PathVariable String id) throws JSONException  {
		Adopcion respuestaAdopcion = new Adopcion();
		try {  
        	Adopcion adopcion = new Adopcion();
        	adopcion.setIdAdopcion(id);
            respuestaAdopcion = (Adopcion) adopcionDaoInterface.consultar(adopcion);
            if(respuestaAdopcion.getIdMascota() != null){  
               respuestaAdopcion.setCodigoRespuesta("200");
            }else{
               respuestaAdopcion.setCodigoRespuesta("201");   
            }               
            return respuestaAdopcion;
        } catch (Exception e) {
        	respuestaAdopcion.setCodigoRespuesta("500");
            return respuestaAdopcion;
        }
    } 
    
     
	@PutMapping("/adopciones")
    public String actualizarSolicitud(@RequestBody Adopcion adopcion) throws JSONException  {
        String response = "200";
		try {    
            int respueata = (int) adopcionDaoInterface.actualizar(adopcion);
            if("2".equals(adopcion.getEstadoSolicitud())) {
            	Mascota mascota= new Mascota();
            	mascota.setId(adopcion.getIdMascota());
            	Mascota mascotaResult = mascotaCrudInterface.consultar(mascota);
            	mascotaResult.setEstado("2");
            	mascotaCrudInterface.actualizar(mascotaResult);
            }
            if(respueata !=1){  
            	response = "201";
            }          
            return response;
        } catch (Exception e) {
			e.printStackTrace();
            return "500";
        }
    }

}
