package app.tkrun.model;

import java.util.List;


import app.tkrun.entities.ClasificacionEntity;
import app.util.Database;
import app.util.Util;

public class ClasificacionModel {

	
	private static final String SQL_FIND_CLASIFICACION = "SELECT idCarrera, idCategoria, nombreAtleta, genero, posicion, tiempo from CLASIFICACIONES where idCarrera=? and genero=?";

	

	private Database db = new Database();

	public List<ClasificacionEntity> findClasificacion (int idCarrera, String genero) {
		List<ClasificacionEntity> clasificaciones = db.executeQueryPojo(ClasificacionEntity.class, SQL_FIND_CLASIFICACION, idCarrera, genero.toLowerCase());
		Util.validateCondition(!clasificaciones.isEmpty(), "Clasifcacion para la carrera con id " + idCarrera + "no encontrada");
		return clasificaciones;
	}
	

}
