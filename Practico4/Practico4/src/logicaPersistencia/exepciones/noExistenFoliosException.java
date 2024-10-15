package logicaPersistencia.exepciones;

public class noExistenFoliosException {
private String mensaje;
	
	public noExistenFoliosException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}
