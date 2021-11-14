package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.TiempoEntity;
import app.util.Database;

public class TiempoModel {

	
	private static final String SQL_FIND_CLASIFICACIONES_FOR_CARRERA = "SELECT idCarrera, emailAtleta, tiempo from Tiempos where idCarrera=? ORDER BY tiempo ASC";

	private static final String SQL_ADD_TIEMPO = "INSERT INTO Tiempos (emailAtleta, idCarrera, tiempo) VALUES(?, ?, ?)";

	private Database db = new Database();

	public List<TiempoEntity> findClasificacionForCarrera (int idCarrera) {
		List<TiempoEntity> clasificaciones = db.executeQueryPojo(TiempoEntity.class, SQL_FIND_CLASIFICACIONES_FOR_CARRERA, idCarrera);
		return clasificaciones;
	}
	
	public void addTiempo(TiempoEntity clasificacion) {
		db.executeUpdate(SQL_ADD_TIEMPO, clasificacion.getEmailAtleta(), clasificacion.getIdCarrera(), clasificacion.getTiempo());
	}
	

}
