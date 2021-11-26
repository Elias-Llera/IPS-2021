package app.tkrun.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import app.tkrun.entities.InscripcionEntity;
import app.util.Database;

public class InscripcionModel {

	private static final String SQL_FIND_INSCRIPCIONES_PARTICIPANTE = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal, ultimaActualizacion from INSCRIPCIONES where emailAtleta=? ORDER BY ultimaActualizacion DESC";

	private static final String SQL_FIND_INSCRIPCION = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal, fecha from INSCRIPCIONES where emailAtleta=? AND idCarrera = ?";

	private static final String SQL_FIND_INSCRIPCIONES_BY_ID_CARRERA = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal from INSCRIPCIONES where idCarrera = ?";

	private static final String SQL_FIND_INSCRIPCIONES_BY_EMAIL_ATLETA = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal from INSCRIPCIONES where emailAtleta = ?";

	private static final String SQL_FIND_EXPIRED_PREINSCRIPTIONS = "SELECT * from INSCRIPCIONES where estado = 'PREINSCRITP' AND fecha < ?";

	private static final String SQL_ADD_INSCRIPCION = "INSERT INTO INSCRIPCIONES (emailAtleta, idCarrera, estado, idCategoria, dorsal, fecha, idPlazosDeInscripcion) VALUES(?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_ADD_INSCRIPCION_GRUPAL = "INSERT INTO INSCRIPCIONES (emailAtleta, idCarrera, estado, idCategoria, dorsal, fecha, ultimaActualizacion, idPlazosDeInscripcion) VALUES(?, ?, ?, ?, ?, ?, ?,?)";

	private static final String SQL_ACEPTAR_INSCRIPCION = "UPDATE Inscripciones SET estado = 'INSCRITO' WHERE emailAtleta = ? AND idCarrera = ?";

	private static final String SQL_RECHAZAR_INSCRIPCION = "UPDATE Inscripciones SET estado = 'INSCRITO' WHERE emailAtleta = ? AND idCarrera = ?";

	private static final String SQL_CALCULATE_DORSAL = "SELECT MAX dorsal FROM INSCRIPCIONES where idCarrera = ?";

	private static final String SQL_FIND_BY_CARRERA_AND_DORSAL = "SELECT * FROM Inscripciones WHERE idCarrera = ? AND dorsal = ?";

	private static final String SQL_UPDATE_INSCRIPCION_CLUB = "UPDATE Inscripciones SET estado = 'INSCRITO', ultimaActualizacion = ?, fecha = ?  WHERE emailAtleta = ? AND idCarrera = ?";
	
	private static final String SQL_UPDATE_INSCRIPCION_DORSAL = "UPDATE Inscripciones SET dorsal = ?  WHERE emailAtleta = ? AND idCarrera = ?";

	private Database db = new Database();

	public void addInscripcionGrupal(InscripcionEntity inscripcion) {
		db.executeUpdate(SQL_ADD_INSCRIPCION_GRUPAL, inscripcion.getEmailAtleta(), inscripcion.getIdCarrera(),
				inscripcion.getEstado(), inscripcion.getIdCategoria(), inscripcion.getDorsal(), LocalDate.now(),
				LocalDate.now(), inscripcion.getIdPlazoInscripcion());
	}

	public void actualizarInscripcion(String email, int id) {
		db.executeUpdate(SQL_UPDATE_INSCRIPCION_CLUB, LocalDate.now(), LocalDate.now(), email, id);
	}
	
	public void actualizarDorsal(String email, int id, int dorsal) {
		db.executeUpdate(SQL_UPDATE_INSCRIPCION_DORSAL, dorsal, email, id);
	}

	public InscripcionEntity findInscripcion(String email, int id_carrera) {
		List<InscripcionEntity> inscripciones = db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCION,
				email, id_carrera);
		return (inscripciones == null || inscripciones.size() == 0) ? null : inscripciones.get(0);
	}

	public List<InscripcionEntity> findInscripcionesParticipante(String email) {
		List<InscripcionEntity> inscripciones = db.executeQueryPojo(InscripcionEntity.class,
				SQL_FIND_INSCRIPCIONES_PARTICIPANTE, email);
		return inscripciones;
	}

	public List<InscripcionEntity> findInscripcionesByEmailAtleta(String emailAtleta) {
		return db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCIONES_BY_EMAIL_ATLETA, emailAtleta);
	}

	public List<InscripcionEntity> findInscripcionesByIdCarrera(int idCarrera) {

		return db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCIONES_BY_ID_CARRERA, idCarrera);
	}

	public void addInscripcion(InscripcionEntity inscripcion) {
		db.executeUpdate(SQL_ADD_INSCRIPCION, inscripcion.getEmailAtleta(), inscripcion.getIdCarrera(),
				inscripcion.getEstado(), inscripcion.getIdCategoria(), inscripcion.getDorsal(),
				java.sql.Date.valueOf(LocalDate.now()), inscripcion.getIdPlazoInscripcion());
	}

	public int calculateDorsal(int id_carrera) {
		Object o = db.executeQueryArray(SQL_CALCULATE_DORSAL, id_carrera).get(0)[0];
		return o.equals(null) ? 1 : ((Integer) o) + 1;
	}

	public void rejectInscription(String email, int idCarrera) {
		db.executeUpdate(SQL_RECHAZAR_INSCRIPCION, email, idCarrera);
	}

	public void acceptInscription(String email, int idCarrera) {
		db.executeUpdate(SQL_ACEPTAR_INSCRIPCION, email, idCarrera);
	}

	public List<InscripcionEntity> findExpiredPreinscriptions() {
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date maximoParaPago = new Date(System.currentTimeMillis() - (3 * DAY_IN_MS));
		return db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_EXPIRED_PREINSCRIPTIONS,
				maximoParaPago.toString());
	}

	public InscripcionEntity findByCarreraAndDorsal(int idCarrera, int dorsal) {
		List<InscripcionEntity> inscripciones = db.executeQueryPojo(InscripcionEntity.class,
				SQL_FIND_BY_CARRERA_AND_DORSAL, idCarrera, dorsal);
		return inscripciones.isEmpty() ? null : inscripciones.get(0);
	}

}
