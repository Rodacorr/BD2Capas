package logicaPersistencia.valueObjects;

public class VOFolio {
	private String codigo;
	private String caratula;
	private int paginas;
	
	public VOFolio(String codigo, String caratula, int paginas) {
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getCaratula() {
		return caratula;
	}
	
	public int getPaginas() {
		return paginas;
	}
	
}
