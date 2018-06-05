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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.com.appmimas.appmimas.dao.ApadrinamientoDaoInterface;
import co.com.appmimas.appmimas.dao.MascotaDaoInterface;
import co.com.appmimas.appmimas.model.Apadrinamiento;
import co.com.appmimas.appmimas.model.Mascota;

@CrossOrigin(origins = {"http://localhost:8090"}) 
@RestController
@RequestMapping(value = "/")
public class ApadrinamientoController {
	
	@Autowired
	ApadrinamientoDaoInterface apadrinamientoDaoInterface;
	
	@Autowired
	private MascotaDaoInterface mascotaCrudInterface;
	
	@PostMapping("/apadrinamientos")
    public Apadrinamiento registrarSolicitud(@RequestBody Apadrinamiento apadrinamiento) throws JSONException  {
		Apadrinamiento apadrinamientoResponse = new Apadrinamiento();
        try {               
        	Mascota mascota = new Mascota();
        	mascota.setId(apadrinamiento.getIdMascota());
        	mascota = mascotaCrudInterface.consultar(mascota);
        	if (mascota.getId() != null) {	        	        	
	            Integer respueata = apadrinamientoDaoInterface.insertar(apadrinamiento);
	            if(respueata > 0){ 
	            	apadrinamientoResponse.setIdApadrinamiento(respueata.toString()); 
	            	apadrinamientoResponse.setCodigoRespuesta("200"); 
	            }else{
	            	apadrinamientoResponse.setCodigoRespuesta("201");
	            }
        	}
        	return apadrinamientoResponse;
        } catch (Exception e) {
        	 System.out.println(e.getMessage());
        	 apadrinamientoResponse.setCodigoRespuesta("501"); 
             return apadrinamientoResponse;
        }     

   }

}
