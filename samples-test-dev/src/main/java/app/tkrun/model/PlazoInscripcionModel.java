package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.PlazosDeInscripcionEntity;
import app.util.Database;

public class PlazoInscripcionModel {

	private static final String SQL_FIND_PLAZO_BY_INSCRIPCION = "SELECT * FROM PlazosInscripciones WHERE idCarrera = ? AND fechaInicio < ? AND fechaFin > ?";

	private Database db = new Database();

	public PlazosDeInscripcionEntity findByInscripcion(InscripcionEntity inscripcion) {
		List<PlazosDeInscripcionEntity> res =  db.executeQueryPojo(PlazosDeInscripcionEntity.class, SQL_FIND_PLAZO_BY_INSCRIPCION, inscripcion.getIdCarrera(), inscripcion.getFecha(), inscripcion.getFecha());
		return (res == null || res.size() == 0) ? null : res.get(0);
	}

}
