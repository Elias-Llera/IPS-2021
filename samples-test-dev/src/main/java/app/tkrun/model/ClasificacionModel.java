package app.tkrun.model;

import java.util.List;


import app.tkrun.entities.ClasificacionEntity;
import app.util.Database;
import app.util.Util;

public class ClasificacionModel {

	
	private static final String SQL_FIND_CLASIFICACION = "SELECT idCarrera, idCategoria,nombreCategoria, nombreAtleta, genero, posicion, tiempo from CLASIFICACIONES where idCarrera=? and genero=? ORDER BY posicion ASC";

	

	private Database db = new Database();

	public List<ClasificacionEntity> findClasificacion (int idCarrera, String genero) {
		List<ClasificacionEntity> clasificaciones = db.executeQueryPojo(ClasificacionEntity.class, SQL_FIND_CLASIFICACION, idCarrera, genero.toLowerCase());
		
		return clasificaciones;
	}
	

}
