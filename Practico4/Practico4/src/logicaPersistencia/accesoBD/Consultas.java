package logicaPersistencia.accesoBD;

public class Consultas {
	
	public static String AgregarFolio() {
		return "INSERT INTO Estudio.Folios " +
				"(Codigo,Caratula,Paginas)" +
				"VALUES (?,?,?)";
	}
	
	public static String AgregarRevision() {
		return "INSERT INTO Estudio.Revisiones " +
				"(Numero,codFolio,descripcion)" +
				"VALUES (?,?,?)";
	}
	
	public static String BorrarFolio() {
		return "DELETE FROM Estudios.Folios WHERE codFolio = ?";
	}
	
	public static String BorrarRevisiones() {
		return "DELETE FROM Estudios.Revisiones WHERE codFolio = ?";
	}
	
	public static String ListarFolios() {
		return "SELECT * FROM Estudio.Folios ORDER BY codigo";
	}
	
	public static String ListarRevisiones() {
		return "SELECT * FROM Estudios.Revisiones " + 
				"WHERE CodFolio = ? ORDERBY numero";
	}
	
	public static String FolioMasRevisado() {
		return "SELECT codFolio, COUNT(*) as Total FROM Estudio.Revisiones " + 
				"GROUP BY codFolio ORDER BY Total DESC LIMIT 1";
	}
	
	public static String consultaFolio() {
		return "SELECT COUNT(*) FROM Estudios.Folio WHERE Codigo = ?";
	}
	
	public static String obtenerRevisiones() {
		return "SELECT MAX(numero) FROM Estudios.Revisiones WHERE codigoFolio = ?";
	}
	
	public static String darDescripcion() {
		return "SELECT descripcion FROM Estudios.Revisiones WHERE codFolio = ? AND Numero = ?";
	}
}
