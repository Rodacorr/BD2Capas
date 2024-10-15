package logicaPersistencia.exepciones;

public class folioNoExisteException extends Exception{
	private String mensaje;
	
	public folioNoExisteException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}

