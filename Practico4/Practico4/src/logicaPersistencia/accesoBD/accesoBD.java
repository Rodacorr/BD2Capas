package logicaPersistencia.accesoBD;

import java.sql.*;
import java.util.*;

import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

import java.io.*;

public class accesoBD {
	private Connection con;
	
	public accesoBD() throws SQLException, IOException, ClassNotFoundException{
		Properties prop = new Properties();
		String nomArch = "src/config/datos.dat";
		
		prop.load(new FileInputStream(nomArch));
		
		String urlBD = prop.getProperty("url");
		String usr = prop.getProperty("user");
		String pwd = prop.getProperty("password");
		String driver = prop.getProperty("drivers");
		
		Class.forName(driver);
		con = DriverManager.getConnection(urlBD, usr, pwd);
		System.out.println("Conexion exitosa...");	
	}

	public boolean existeFolio(String codigo) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(Consultas.consultaFolio());
		pstmt.setString(1,codigo);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public void agregarFolio(VOFolio folio) throws SQLException {
		PreparedStatement ps = con.prepareStatement(Consultas.AgregarFolio());
        ps.setString(1, folio.getCodigo());
        ps.setString(2, folio.getCaratula());
        ps.setInt(3, folio.getPaginas());
        ps.executeUpdate();
	}
	/*
	public int obtenerUltimaRevision(String codFolio) {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.obtenerRevisiones());
			pstmt.setString(1, codFolio);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	*/
	public void agregarRevision(String codFolio, VORevision revision) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(Consultas.AgregarRevision());
        pstmt.setString(1, codFolio);
        pstmt.setInt(2, revision.getNumero());
        pstmt.setString(3, revision.getDescripcion());
        pstmt.executeUpdate();
    }
	
	public void borrarFolioRevisiones(String codFolio) throws SQLException {
		if(existeFolio(codFolio)){
			try {
				PreparedStatement pstmt1 = con.prepareStatement(Consultas.BorrarRevisiones());
				pstmt1.setString(1, codFolio);
				pstmt1.executeUpdate();
				pstmt1.close();
				
				PreparedStatement pstmt2 = con.prepareStatement(Consultas.BorrarFolio());
				pstmt2.setString(1, codFolio);
				pstmt2.executeUpdate();
				pstmt2.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String darDescripcion(String codFolio, int numRev) throws SQLException {	
		
		String desc = null;
		PreparedStatement pstmt = con.prepareStatement(Consultas.darDescripcion());
		pstmt.setString(1, codFolio);
		pstmt.setInt(2, numRev);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			desc = rs.getString("descripcion");
		}
		rs.close();
		pstmt.close();
		return desc;
	}
	/*
	public void listarFolios() {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.ListarFolios());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public List<VOFolio> listarFolios() throws SQLException{
		List<VOFolio> lista = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(Consultas.ListarFolios());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			VOFolio folio = new VOFolio(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("paginas"));
			lista.add(folio);
		}
		rs.close();
		pstmt.close();
		return lista;
	}
	
	public List<VORevision> listarRevisiones(String codFolio) throws SQLException {
		List<VORevision> lista = new ArrayList<>();
		PreparedStatement pstmt = con.prepareStatement(Consultas.ListarRevisiones());
		pstmt.setString(1, codFolio);
		ResultSet rs =pstmt.executeQuery();
		while(rs.next()) {
			VORevision revision = new VORevision(rs.getInt("numero"),rs.getString("codFolio"), rs.getString("descripcion"));
			lista.add(revision);
		}
		rs.close();
		pstmt.close();
		return lista;
	}
	
	public VOFolioMaxRev folioMasRevisado() throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(Consultas.FolioMasRevisado());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			VOFolioMaxRev revisionMax = new VOFolioMaxRev(rs.getString("codigo"), rs.getString("caratula"), rs.getInt("paginas"), rs.getInt("cRevs"));
			rs.close();
			pstmt.close();
			return revisionMax;
		}
		pstmt.close();
		return null;
	}
}
