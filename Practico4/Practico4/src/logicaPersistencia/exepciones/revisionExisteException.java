package logicaPersistencia.exepciones;

public class revisionExisteException{
	private String mensaje;
	
	public revisionExisteException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}
