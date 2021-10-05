package giis.demo.tkrun.model;

import java.util.List;

import giis.demo.tkrun.entities.InscripcionEntity;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class InscripcionModel {
	
	private static final String SQL_FIND_INSCRIPCION = "SELECT email, id_carrera, estado, idCategoria, dorsal from INSCRIPCIONES where email=? AND id_carrera = ?";
	
	private static final String SQL_FIND_INSCRIPCIONES_BY_ID_CARRERA = "SELECT email, id_carrera, estado, idCategoria, dorsal from INSCRIPCIONES where id_carrera = ?";

	private static final String SQL_ADD_INSCRIPCION = "INSERT INTO INSCRIPCIONES (email, id_carrera, estado, idCategoria, dorsal) VALUES(?, ?, ?, ?, ?)";
	
	private static final String SQL_CALCULATE_DORSAL = "SELECT MAX dorsal FROM INSCRIPCIONES where id_carrera = ?";

	private Database db = new Database();

	public InscripcionEntity findInscripcion(String email, int id_carrera) {
		List<InscripcionEntity> inscripciones = db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCION, email, id_carrera);
		Util.validateCondition(!inscripciones.isEmpty(), "Inscripcion no encontrada");
		return inscripciones.get(0);
	}
	
	public List<InscripcionEntity> findInscripciones(int idCarrera){
		return db.executeQueryPojo(InscripcionEntity.class, SQL_FIND_INSCRIPCIONES_BY_ID_CARRERA, idCarrera);
	}
	
	public void addInscripcion(InscripcionEntity inscripcion) {
		db.executeUpdate(SQL_ADD_INSCRIPCION, inscripcion.getEmailAtleta(), inscripcion.getIdCarrera(), inscripcion.getEstado(), inscripcion.getIdCategoria(), inscripcion.getDorsal());;
	}
	
	public int calculateDorsal(int id_carrera) {
		Object o = db.executeQueryArray(SQL_CALCULATE_DORSAL, id_carrera).get(0)[0];
		return o.equals(null) ? 1 : ((Integer)o)+1;
	}

}
