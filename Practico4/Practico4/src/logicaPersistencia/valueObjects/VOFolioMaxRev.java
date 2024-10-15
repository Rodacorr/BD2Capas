package logicaPersistencia.valueObjects;

public class VOFolioMaxRev extends VOFolio {	//Extends de la clase VOFolio
	private int cantRevisiones;
	
	public VOFolioMaxRev(String codigo, String caratula, int paginas ,int cRevs) {
		super(codigo, caratula, paginas);	//Llamo a construcotr VOFolio
		this.cantRevisiones = cRevs;
	}
	
	public int getCantRevisiones() {
		return cantRevisiones;
	}
}
