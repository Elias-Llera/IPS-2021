package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.AtletaEntity;
import app.util.Database;

public class AtletaModel {

	private static final String SQL_FIND_ATLETA = "SELECT emailAtleta, nombre, apellido, fechaNacimiento, sexo from ATLETAS where emailAtleta=?";
	
	private static final String SQL_FIND_ATLETAS_CARRERA = "SELECT a.emailAtleta, a.nombre, a.apellido, a.fechaNacimiento, a.sexo"
			+ " from ATLETAS a, INSCRIPCIONES i"
			+ " where a.emailAtleta=i.emailAtleta and i.idCarrera = ?";
	
	private Database db = new Database();

	public AtletaEntity findAtleta(String email) {
		List<AtletaEntity> atletas = db.executeQueryPojo(AtletaEntity.class, SQL_FIND_ATLETA, email);
		return atletas.get(0);
	}
	
	public List<AtletaEntity> findAtletasCarrera(int idCarrera) {
		List<AtletaEntity> atletas = db.executeQueryPojo(AtletaEntity.class, SQL_FIND_ATLETAS_CARRERA, idCarrera);
		return atletas;
	}

}
