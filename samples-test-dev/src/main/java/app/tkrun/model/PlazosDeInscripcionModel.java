package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.PlazosDeInscripcionEntity;
import app.util.Database;
import app.util.Util;

public class PlazosDeInscripcionModel {

	private static final String SQL_ADD_PLAZO_INSCRIPCION = "INSERT INTO PlazosDeInscripcion (idPlazosDeInscripcion, idCarrera, fechaInicio, fechaFin, precio) VALUES(?, ?, ?, ?, ?)";
	private static final String SQL_TOTAL_PLAZO_INSCRIPCION = "SELECT * from PlazosDeInscripcion where idCarrera=?";
	private static final String SQL_FIND_PRECIO_PLAZO_INSCRIPCION = "SELECT * from PlazosDeInscripcion where idCarrera=? and fechaInicio<=? and fechaFin>=?";
	private static final String SQL_FIND_PLAZO_INSCRIPCION_FIN = "SELECT * from PlazosDeInscripcion where idCarrera=? order by fechaFin DESC";

	private Database db = new Database();

	public void addInscripcion(PlazosDeInscripcionEntity plazo_inscripcion) {
		db.executeUpdate(SQL_ADD_PLAZO_INSCRIPCION, plazo_inscripcion.getIdPlazosDeInscripcion(),
				plazo_inscripcion.getIdCarrera(), plazo_inscripcion.getFechaInicio(), plazo_inscripcion.getFechaFin(),
				plazo_inscripcion.getPrecio());

	}

	public int findTodosLosPlazosParaUnaCarrera(int idCarrera) {
		List<PlazosDeInscripcionEntity> carreras = db.executeQueryPojo(PlazosDeInscripcionEntity.class,
				SQL_TOTAL_PLAZO_INSCRIPCION, idCarrera);
		return carreras.size();
	}

	public PlazosDeInscripcionEntity getListaPlazosInscripciones(int id, String fecha) {
		List<PlazosDeInscripcionEntity> carreras = db.executeQueryPojo(PlazosDeInscripcionEntity.class,
				SQL_FIND_PRECIO_PLAZO_INSCRIPCION, id,fecha,fecha);
		if(!carreras.isEmpty()) {
			return carreras.get(0);
		}
		return null;

	}
	
	public PlazosDeInscripcionEntity getListaPlazosInscripcionesPorFechaFin(int id) {
		List<PlazosDeInscripcionEntity> carreras = db.executeQueryPojo(PlazosDeInscripcionEntity.class,
				SQL_FIND_PLAZO_INSCRIPCION_FIN, id);
		if(!carreras.isEmpty()) {
			return carreras.get(0);
		}
		return null;

	}

}
