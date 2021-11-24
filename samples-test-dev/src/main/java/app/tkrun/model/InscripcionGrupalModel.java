package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.InscripcionEntity;
import app.util.Database;

public class InscripcionGrupalModel {

	private static final String SQL_ADD_INSCRIPCION = "INSERT INTO INSCRIPCIONESGRUPAL (idCarrera,emailAtleta,club) VALUES(?, ?, ?)";
	private static final String SQL_FIND_INSCRIPCION = "SELECT * from INSCRIPCIONESGRUPAL where emailAtleta=? AND idCarrera = ? AND club=?";
	private Database db = new Database();

	public void addInscripcion(int id, String email, String club) {
		db.executeUpdate(SQL_ADD_INSCRIPCION, id, email, club);
	}

	public int findInscripcion(String email, int id, String club) {
		List<InscripcionEntity> inscripciones = db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCION,
				email, id, club);
		return inscripciones.size();
	}
}
