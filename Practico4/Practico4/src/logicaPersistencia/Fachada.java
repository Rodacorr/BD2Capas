package logicaPersistencia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;
import logicaPersistencia.exepciones.*;

public class Fachada extends UnicastRemoteObject{

	private logicaPersistencia.accesoBD.accesoBD accesoBD;
	
	public Fachada(logicaPersistencia.accesoBD.accesoBD accesoBD) throws RemoteException {
		this.accesoBD = accesoBD;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void agregarFolio(VOFolio folio) throws RemoteException, folioYaExisteException {
		try {
			List<VOFolio> folios = accesoBD.listarFolios();
			for(VOFolio f : folios) {
				if(f.getCodigo().equals(folio.getCodigo())) {
					throw new folioYaExisteException("El folio ya existe.");
				}
			}
		}catch(SQLException e) {
			throw new RemoteException("Error al agregar el folio ",e);
		}
	}
	
	public void agregarRevision(String codFolio, VORevision revision)throws RemoteException, folioNoExisteException{
		try {
			VOFolio folio = null;
			List<VOFolio> folios = accesoBD.listarFolios();
			for(VOFolio f : folios) {
				if(f.getCodigo().equals(codFolio)) {
					folio = f;
					break;
				}
			}
			if(folio == null) {
				throw new folioNoExisteException("El folio no existe.");
			}
			accesoBD.agregarRevision(codFolio, revision);
		}catch(SQLException e) {
			throw new RemoteException("Error al agregar revision ",e);
		}
	}
	
	public void borrarFolioRevisiones(String codFolio) throws RemoteException{
		try {
			accesoBD.borrarFolioRevisiones(codFolio);
		}catch(SQLException e) {
			throw new RemoteException("Error al borrar el folio con sus revisiones ",e);
		}
	}
	
	public String darDescrpicion(String codFolio, int numRevision) throws noExistenRevisionesException, RemoteException {
		try {
			String desc = accesoBD.darDescripcion(codFolio, numRevision);
			if(desc == null) {
				throw new noExistenRevisionesException("La revision no existe.");
			}
			return desc;
		}catch(SQLException e) {
			throw new RemoteException("Error al obtener la descripcion de la revision",e);
		}
	}
	
	public List<VOFolio> listarFolios() throws RemoteException{
		try {
			return accesoBD.listarFolios();
		}catch(SQLException e) {
			throw new RemoteException("Error al listar folios ",e);
		}
	}
	
	public List<VORevision> listarRevisiones(String codFolio) throws RemoteException{
		try {
			return accesoBD.listarRevisiones(codFolio);
		}catch(SQLException e) {
			throw new RemoteException("Error al listar las revisiones", e);
		}
	}
	
	public VOFolioMaxRev folioMasRevisado() throws RemoteException {
		try {
			return accesoBD.folioMasRevisado();
		}catch (SQLException e) {
			throw new RemoteException("Error al obtener el folio más revisado", e);
		}
	}
}
