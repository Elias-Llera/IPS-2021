package app.tkrun.model;

import java.time.LocalDate;
import java.util.List;

import app.tkrun.entities.InscripcionEntity;
import app.util.Database;

public class InscripcionModel {

	private static final String SQL_FIND_INSCRIPCION = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal, fecha from INSCRIPCIONES where emailAtleta=? AND idCarrera = ?";

	private static final String SQL_FIND_INSCRIPCIONES_BY_ID_CARRERA = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal from INSCRIPCIONES where idCarrera = ?";

	private static final String SQL_FIND_INSCRIPCIONES_BY_EMAIL_ATLETA = "SELECT emailAtleta, idCarrera, estado, idCategoria, dorsal from INSCRIPCIONES where emailAtleta = ?";

	private static final String SQL_ADD_INSCRIPCION = "INSERT INTO INSCRIPCIONES (emailAtleta, idCarrera, estado, idCategoria, dorsal, fecha) VALUES(?, ?, ?, ?, ?, ?)";

	private static final String SQL_ACEPTAR_INSCRIPCION = "UPDATE Inscripciones SET estado = 'INSCRITO' WHERE emailAtleta = ? AND idCarrera = ?";
	
	private static final String SQL_RECHAZAR_INSCRIPCION = "UPDATE Inscripciones SET estado = 'INSCRITO' WHERE emailAtleta = ? AND idCarrera = ?";

	private static final String SQL_CALCULATE_DORSAL = "SELECT MAX dorsal FROM INSCRIPCIONES where idCarrera = ?";

	private Database db = new Database();

	public InscripcionEntity findInscripcion(String email, int id_carrera) {
		List<InscripcionEntity> inscripciones = db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCION,
				email, id_carrera);
		return (inscripciones == null || inscripciones.size() == 0) ? null : inscripciones.get(0);
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
				java.sql.Date.valueOf(LocalDate.now()));
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

}
