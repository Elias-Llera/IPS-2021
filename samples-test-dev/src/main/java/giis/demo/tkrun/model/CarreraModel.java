package giis.demo.tkrun.model;

import java.util.List;

import giis.demo.tkrun.entities.CarreraEntity;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class CarreraModel {

	private static final String SQL_FIND_CARRERA = "SELECT id, nombre, descripcion, tipo, inicio_inscripcion, fin_inscripcion, precio_inscripcion, fecha, plazas from CARRERAS where id=?";

	private Database db = new Database();

	/**
	 * Obtiene todos los datos de la carrera con el id indicado
	 */
	public CarreraEntity findCarrera(int id) {
		List<CarreraEntity> carreras = db.executeQueryPojo(CarreraEntity.class, SQL_FIND_CARRERA, id);
		Util.validateCondition(!carreras.isEmpty(), "Id de carrera no encontrado: " + id);
		return carreras.get(0);
	}

}
