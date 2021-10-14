package giis.demo.tkrun.model;

import java.util.List;

import giis.demo.tkrun.entities.AtletaEntity;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class AtletaModel {

	private static final String SQL_FIND_ATLETA = "SELECT emailAtleta, nombre, apellido, fechaNacimiento from ATLETAS where email=?";
	
	private Database db = new Database();

	public AtletaEntity findAtleta(String email) {
		List<AtletaEntity> atletas = db.executeQueryPojo(AtletaEntity.class, SQL_FIND_ATLETA, email);
		Util.validateCondition(!atletas.isEmpty(), "Email no encontrado: " + email);
		return atletas.get(0);
	}

}
