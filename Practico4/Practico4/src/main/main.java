package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.*;


public class main {
	public static void main (String[] args) throws ClassNotFoundException, FileNotFoundException, IOException{
		Connection con = null;
		String nomArch = "src/config/datos.dat";
		try { 
			Properties prop = new Properties();
			prop.load(new FileInputStream(nomArch));
			String driver = prop.getProperty("DRIVER");
			String usr = prop.getProperty("USR");
			String pwd = prop.getProperty("PWD");
			String url = prop.getProperty("URL");
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,usr,pwd);
			
			String database = "CREATE DATABASE IF NOT EXISTS Estudio";
			
			String useDatabase = "USE Estudio";
			
			String Folios = "CREATE TABLE IF NOT EXISTS Folios ("
					+ "Codigo VARCHAR(60),"
					+ "Caratula VARCHAR(60),"
					+ "Paginas INTEGER,"
					+ "PRIMARY KEY(Codigo))";
			
			String Revisiones = "CREATE TABLE IF NOT EXISTS Revisiones("
					+ "Numero INT,"
					+ "CodFolio VARCHAR(60),"
					+ "Descripcion VARCHAR(60),"
					+ "PRIMARY KEY(Numero),"
					+ "FOREIGN KEY (CodFolio) REFERENCES Folios (Codigo))";
			
			ArrayList <String> comandos = new ArrayList<>();
			comandos.add(database);
			comandos.add(useDatabase);
			comandos.add(Folios);
			comandos.add(Revisiones);
			
			for(String a : comandos) {
				PreparedStatement pstmt = con.prepareStatement(a);
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			String Folio = "INSERT INTO Estudio.Folios " +
							"(Codigo,Caratula,Paginas)" +
							"VALUES (?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(Folio);
			pstmt.setString(1, "FGH-0015");
			pstmt.setString(2, "La comuna contra la señora que tiene 38 gatos");
			pstmt.setString(3, "5");
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(Folio);
			pstmt.setString(1, "BBD-1278");
			pstmt.setString(2, "Adolescentes descontrolados hasta las 5 AM");
			pstmt.setString(3, "2");
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(Folio);
			pstmt.setString(1, "JJ-202 ");
			pstmt.setString(2, "Vecinos reclaman por heces de perro en el hall");
			pstmt.setString(3, "9");
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(Folio);
			pstmt.setString(1, "CEFJ-63");
			pstmt.setString(2, "Vecinas rivales se arrojan macetas con frecuencia");
			pstmt.setString(3, "463");
			pstmt.executeUpdate();
			pstmt.close();
			
			con.close();

			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
