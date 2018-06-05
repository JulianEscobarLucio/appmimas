package co.com.appmimas.appmimas.model;

public class Response {
	
	private String codigoRespuesta;
	private String mensajeRespuesta;
	
	public Response(String codigoRespuesta, String mensajeRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public Response() {
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	

}
