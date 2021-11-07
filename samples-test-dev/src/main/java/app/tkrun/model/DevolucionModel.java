package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.DevolucionEntity;
import app.util.Database;

public class DevolucionModel {

	private static final String SQL_ADD_DEVOLUCION = "INSERT INTO Devoluciones (emailAtleta, idCarrera, cantidad) VALUES(?, ?, ?)";
	private static final String SQL_FIND_ALL_DEVOLUCION = "SELECT * FROM DEVOLUCIONES";
	
	private Database db = new Database();
	
	public void addDevolucion(DevolucionEntity devolucion) {
		db.executeUpdate(SQL_ADD_DEVOLUCION, devolucion.getEmailAtleta(), devolucion.getIdCarrera(), devolucion.getCantidad());
	}

	public List<DevolucionEntity> findAll() {
		return db.executeQueryPojo(DevolucionEntity.class, SQL_FIND_ALL_DEVOLUCION);
	}

}
