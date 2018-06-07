package co.com.appmimas.appmimas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.appmimas.appmimas.dao.ApadrinamientoDaoInterface;
import co.com.appmimas.appmimas.dao.MascotaDaoInterface;
import co.com.appmimas.appmimas.model.Apadrinamiento;
import co.com.appmimas.appmimas.model.Mascota;

@CrossOrigin(origins = "*", maxAge = 4600)
@RestController
@RequestMapping(value = "/")
public class ApadrinamientoController {

	@Autowired
	ApadrinamientoDaoInterface apadrinamientoDaoInterface;

	@Autowired
	private MascotaDaoInterface mascotaCrudInterface;

	@PostMapping("/apadrinamientos")
	public Apadrinamiento registrarSolicitud(@RequestBody Apadrinamiento apadrinamiento) {
		Apadrinamiento apadrinamientoResponse = new Apadrinamiento();
		try {
			Mascota mascota = new Mascota();
			mascota.setId(apadrinamiento.getIdMascota());
			mascota = mascotaCrudInterface.consultar(mascota);
			if (mascota.getId() != null) {
				Integer respueata = apadrinamientoDaoInterface.insertar(apadrinamiento);
				if (respueata > 0) {
					apadrinamientoResponse.setIdApadrinamiento(respueata.toString());
					apadrinamientoResponse.setCodigoRespuesta("200");
				} else {
					apadrinamientoResponse.setCodigoRespuesta("201");
				}
			}
			return apadrinamientoResponse;
		} catch (Exception e) {
			e.printStackTrace();
			apadrinamientoResponse.setCodigoRespuesta("501");
			return apadrinamientoResponse;
		}

	}

	@GetMapping("/apadrinamientos/{id}")
	public Apadrinamiento consultarSolicitud(@PathVariable String id) {
		Apadrinamiento respuestaApadrinamiento = new Apadrinamiento();
		try {
			Apadrinamiento apadrinamiento = new Apadrinamiento();
			apadrinamiento.setIdApadrinamiento(id);
			respuestaApadrinamiento = (Apadrinamiento) apadrinamientoDaoInterface.consultar(apadrinamiento);
			if (respuestaApadrinamiento.getIdMascota() != null) {
				respuestaApadrinamiento.setCodigoRespuesta("200");
			} else {
				respuestaApadrinamiento.setCodigoRespuesta("201");
			}
			return respuestaApadrinamiento;
		} catch (Exception e) {
			e.printStackTrace();
			respuestaApadrinamiento.setCodigoRespuesta("500");
			return respuestaApadrinamiento;
		}
	}

	@GetMapping("/apadrinamientos")
	public List<Apadrinamiento> listarSolicitud() {
		List<Apadrinamiento> listaSolicitud = new ArrayList<>();
		try {
			listaSolicitud = apadrinamientoDaoInterface.listar();
			return listaSolicitud;
		} catch (Exception e) {
			e.printStackTrace();
			return listaSolicitud;
		}
	}

	@PutMapping("/apadrinamientos")
	public String actualizarSolicitud(@RequestBody Apadrinamiento apadrinamiento) {
		String response = "200";
		try {
			int respueata = (int) apadrinamientoDaoInterface.actualizar(apadrinamiento);
			if (respueata != 1) {
				response = "201";
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

}
