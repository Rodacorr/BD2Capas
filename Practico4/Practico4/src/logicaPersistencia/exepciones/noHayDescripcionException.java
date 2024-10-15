package logicaPersistencia.exepciones;

public class noHayDescripcionException {
private String mensaje;
	
	public noHayDescripcionException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}
