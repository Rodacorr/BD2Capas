package logicaPersistencia.valueObjects;

public class VORevision {
	private int numero;
	private String descripcion;
	private String codFolio;
	
	public VORevision(int num, String desc, String cFolio) {
		this.numero = num;
		this.descripcion = desc;
		this.codFolio = cFolio;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getCodigoFolio() {
		return codFolio;
	}
}
