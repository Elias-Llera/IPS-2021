package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.TiempoEntity;
import app.util.Database;

public class TiempoModel {

	
	private static final String SQL_FIND_CLASIFICACIONES_FOR_CARRERA = "SELECT * from Tiempos where idCarrera=? AND NOMBRE = 'FINAL' ORDER BY tiempo ASC";

	private static final String SQL_ADD_TIEMPO = "INSERT INTO Tiempos (emailAtleta, idCarrera, tiempo, nombre) VALUES(?, ?, ?, ?)";

	private Database db = new Database();

	public List<TiempoEntity> findClasificacionForCarrera (CarreraEntity carrera) {
		List<TiempoEntity> clasificaciones = db.executeQueryPojo(TiempoEntity.class, SQL_FIND_CLASIFICACIONES_FOR_CARRERA, carrera.getIdCarrera());
		return clasificaciones;
	}
	
	public void addTiempo(TiempoEntity clasificacion) {
		db.executeUpdate(SQL_ADD_TIEMPO, clasificacion.getEmailAtleta(), clasificacion.getIdCarrera(), clasificacion.getTiempo(), clasificacion.getNombre());
	}
	

}
