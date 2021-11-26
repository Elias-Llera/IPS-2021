package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.LimiteDorsalesEntity;
import app.util.Database;

public class LimiteDorsalesModel {

	private static final String SQL_INSERT_LIMITE = "INSERT INTO limiteDorsales (idCarrera, numero, secuencial) VALUES(?, ?, ?)";
	private static final String SQL_FIND_LIMITE = "SELECT * from limiteDorsales where idCarrera=?";

	private Database db = new Database();

	public void addLimiteDorsales(LimiteDorsalesEntity limite) {
		db.executeUpdate(SQL_INSERT_LIMITE, limite.getIdCarrera(), limite.getNumero(), limite.getSecuencial());
		System.out.println("limite insertado");
	}

	public LimiteDorsalesEntity findInscripcion(int id) {
		List<LimiteDorsalesEntity> dorsales = db.executeQueryPojo(LimiteDorsalesEntity.class, SQL_FIND_LIMITE, id);
		return dorsales.get(0);
	}
}
