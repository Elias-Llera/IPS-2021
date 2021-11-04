package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.InscripcionEntity;
import app.util.ApplicationException;
import app.util.Database;
import app.util.Util;

public class CarreraModel {

	private static final String SQL_FIND_CARRERA = "SELECT idCarrera, nombre, tipo, descripcion, fecha, plazas, distancia from CARRERAS where idCarrera=?";
	private static final String SQL_FIND_CARRERAS_DESDE_HOY = "SELECT idCarrera, nombre, fecha, tipo, distancia, plazas from carreras where fecha>=? order by fecha";
	private static final String MSG_FECHA_INSCRIPCION_NO_NULA = "La fecha de inscripcion no puede ser nula";
	private static final String SQL_ADD_CARRERA = "INSERT INTO carreras (idCarrera, nombre, tipo, descripcion, fecha, plazas, distancia) VALUES(?, ?, ?, ?, ?,?,?)";
	private static final String SQL_FIND_CARRERA_IDENTICA = "SELECT  nombre, tipo, descripcion, fecha, plazas, distancia from CARRERAS where  nombre=? and tipo=? and descripcion=? and fecha=? and plazas=? and distancia=?";
	private Database db = new Database();

	/**
	 * Obtiene todos los datos de la carrera con el id indicado
	 */
	public CarreraEntity findCarrera(int id) {
		List<CarreraEntity> carreras = db.executeQueryPojo(CarreraEntity.class, SQL_FIND_CARRERA, id);
		Util.validateCondition(!carreras.isEmpty(), "Id de carrera no encontrado: " + id);
		return carreras.get(0);
	}

	/**
	 * Obtiene la lista de carreras activas en forma objetos para una fecha de
	 * inscripcion dada
	 */
	public List<CarreraEntity> getListaCarreras(String fechaInscripcion) {
		validateNotNull(fechaInscripcion, MSG_FECHA_INSCRIPCION_NO_NULA);

		return db.executeQueryPojo(CarreraEntity.class, SQL_FIND_CARRERAS_DESDE_HOY, fechaInscripcion);
	}

	public void addCarrera(CarreraEntity carrera) {
		db.executeUpdate(SQL_ADD_CARRERA, carrera.getIdCarrera(), carrera.getNombre(), carrera.getTipo(),
				carrera.getDescripcion(), carrera.getFecha(), carrera.getPlazas(), carrera.getDistancia());

	}

	public int findCarreraIdentica(CarreraEntity carrera) {
		List<CarreraEntity> carreras = db.executeQueryPojo(CarreraEntity.class, SQL_FIND_CARRERA_IDENTICA,
				carrera.getNombre(), carrera.getTipo(), carrera.getDescripcion(), carrera.getFecha(),
				carrera.getPlazas(), carrera.getDistancia());
		return carreras.size();
	}

//	private void validateFechasInscripcion(Date inicio, Date fin, Date fecha) {
//		validateNotNull(inicio, "La fecha de inicio de inscripcion no puede ser nula");
//		validateNotNull(fin, "La fecha de fin de inscripcion no puede ser nula");
//		validateNotNull(fecha, "La fecha de fin de carrera no puede ser nula");
//		validateCondition(inicio.compareTo(fin) <= 0, "La fecha de inicio no puede ser posterior a la de fin");
//		validateCondition(fin.compareTo(fecha) <= 0, "La fecha de fin no puede ser posterior a la de la carrera");
//	}

	/* De uso general para validacion de objetos */
	private void validateNotNull(Object obj, String message) {
		if (obj == null)
			throw new ApplicationException(message);
	}

//	private void validateCondition(boolean condition, String message) {
//		if (!condition)
//			throw new ApplicationException(message);
//	}

}
