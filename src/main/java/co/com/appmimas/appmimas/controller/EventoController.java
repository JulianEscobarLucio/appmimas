package co.com.appmimas.appmimas.controller;

import java.util.List;

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

import co.com.appmimas.appmimas.dao.EventoDaoInterface;
import co.com.appmimas.appmimas.model.Evento;

@CrossOrigin(origins = "*", maxAge = 4600)
@RestController
@RequestMapping(value = "/")
public class EventoController {

	@Autowired
	EventoDaoInterface eventoDaoInterface;

	@PostMapping("/eventos")
	public String registrarEvento(@RequestBody Evento evento) {
		String response = "200";
		try {
			int respueata = eventoDaoInterface.insertar(evento);
			if (respueata != 1) {
				response = "201";
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	@PutMapping("/eventos")
	public String actualizarEvento(@RequestBody Evento evento) {
		String response = "200";
		try {
			int respueata = eventoDaoInterface.actualizar(evento);
			if (respueata != 1) {
				response = "201s";
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	@GetMapping("/eventos/{id}")
	public Evento consultarEvento(@PathVariable String id) {
		Evento respuestaEvento = new Evento();
		try {
			respuestaEvento = eventoDaoInterface.consultar(id);
			if (respuestaEvento.getId() != null) {
				respuestaEvento.setCodigoRespuesta("200");
			} else {
				respuestaEvento.setCodigoRespuesta("201");
			}
			return respuestaEvento;
		} catch (Exception e) {
			respuestaEvento.setCodigoRespuesta("500");
			return respuestaEvento;
		}
	}

	@DeleteMapping("/eventos/{id}")
	public String eliminarEvento(@PathVariable String id) {
		Evento evento = new Evento();
		evento.setId(id);
		String response = "200";
		try {
			int respueata = eventoDaoInterface.eliminar(evento);
			if (respueata != 1) {
				response = "201";
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	@GetMapping("/eventos")
	public List<Evento> listaEvento() {
		try {
			return eventoDaoInterface.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
