package logicaPersistencia.exepciones;

public class noExistenRevisionesException {
private String mensaje;
	
	public noExistenRevisionesException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}
