package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class main {
/*
	public ArrayList<String> CrearSQL() {
		String crearDB = "CREATE DATABASE EstudioNotarial";
		
			
			Folios (codigo VARCHAR(60), caratula VARCHAR(60), paginas INT)
			Revisiones (número INT, codFolio VARCHAR(60), descripcion VARCHAR(60))
			
			
		String useDB = "USE EstudioNotarial";
			
		String crearTableFolios = "CREATE TABLE IF NOT EXISTS Folios (" +
									"codigo VARCHAR(60)," +
									"caratula VARCHAR(60)," +
									"paginas INT," +
									"PRIMARY KEY (codigo)" +
									");";
		
		String crearTableRevisiones= "CREATE TABLE IF NOT EXISTS Revisiones (" +
									"numero INT," +
									"codFolio VARCHAR(60)," +
									"descripcion VARCHAR(60)," +
									"PRIMARY KEY (numero)," +
									"FOREIGN KEY (codFolio) REFERENCES Folios(codigo)" +
									");";
		
		ArrayList<String> lista = new ArrayList<>();
		lista.add(crearDB);
		lista.add(useDB);
		lista.add(crearTableFolios);
		lista.add(crearTableRevisiones);
		
		return lista;
	}
*/
	public static void main(String [] args){
		Connection con = null;
		
		try {
			Properties prop = new Properties();
			String nomArch = "src/Config/datos.dat";
			prop.load(new FileInputStream(nomArch));

			String urlBD = prop.getProperty("urlBD");
	        String usr = prop.getProperty("usr");
	        String pwd = prop.getProperty("pwd");
	        String driver = prop.getProperty("driver");
	        
	        Class.forName(driver);
	        con=DriverManager.getConnection(urlBD,usr,pwd);
	        
	        
	        //Folios (codigo VARCHAR(60), caratula VARCHAR(60), paginas INT)
			//Revisiones (número INT, codFolio VARCHAR(60), descripcion VARCHAR(60))
	        
	        String crearDB = "CREATE DATABASE EstudioNotarial";
	        
	        String useDB = "USE EstudioNotarial";
			
			String crearTableFolios = "CREATE TABLE IF NOT EXISTS Folios (" +
										"codigo VARCHAR(60)," +
										"caratula VARCHAR(60)," +
										"paginas INT," +
										"PRIMARY KEY (codigo)" +
										");";
			
			String crearTableRevisiones= "CREATE TABLE IF NOT EXISTS Revisiones (" +
										"numero INT," +
										"codFolio VARCHAR(60)," +
										"descripcion VARCHAR(60)," +
										"PRIMARY KEY (numero)," +
										"FOREIGN KEY (codFolio) REFERENCES Folios(codigo)" +
										");";
			
			ArrayList<String> lista = new ArrayList<>();
			lista.add(crearDB);
			lista.add(useDB);
			lista.add(crearTableFolios);
			lista.add(crearTableRevisiones);
	        
			for(String valor : lista) {
				PreparedStatement pstmt = con.prepareStatement(valor);
				pstmt.executeUpdate();
			}
			
			String insertFolios = "INSERT INTO EstudioNotarial.Folios" +
							"(codigo,caratula,paginas)" + 
							"VALUES (?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(insertFolios);
			pstmt.setString(1,"FGH-0015");
			pstmt.setString(2,"La comuna contra la señora que tiene 38 gatos");
			pstmt.setInt(3, 5);
			pstmt.executeUpdate();

			pstmt.setString(1,"BBD-1278");
			pstmt.setString(2,"Adolescentes descontrolados hasta las 5 AM");
			pstmt.setInt(3, 2);
			pstmt.executeUpdate();
			
			pstmt.setString(1,"JJ-202");
			pstmt.setString(2,"Vecinos reclaman por heces de perro en el hall ");
			pstmt.setInt(3, 9);
			pstmt.executeUpdate();
			
			pstmt.setString(1,"CEFJ-63");
			pstmt.setString(2,"Vecinas rivales se arrojan macetas con frecuencia");
			pstmt.setInt(3, 463);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(con != null)
					con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
