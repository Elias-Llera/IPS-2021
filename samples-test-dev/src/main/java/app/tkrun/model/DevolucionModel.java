package app.tkrun.model;

import app.tkrun.entities.DevolucionEntity;
import app.util.Database;

public class DevolucionModel {

	private static final String SQL_ADD_DEVOLUCION = "INSERT INTO Devoluciones (emailAtleta, idCarrera, cantidad) VALUES(?, ?, ?)";
	
	private Database db = new Database();
	
	public void addDevolucion(DevolucionEntity devolucion) {
		db.executeUpdate(SQL_ADD_DEVOLUCION, devolucion.getEmailAtleta(), devolucion.getIdCarrera(), devolucion.getCantidad());
	}

}
