package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.PuntoDeControlEntity;
import app.util.Database;

public class PuntoDeControlModel {

	private static final String SQL_ADD_PUNTO_DE_CONTROL = "INSERT INTO PuntosDeControl (idCarrera, nombre) values (?, ?)";
	private static final String SQL_FIND_PUNTO_BY_ID_CARRERA = "SELECT * FROM PuntosDeControl WHERE idCarrera = ?";
	
	private Database db = new Database();
	
	public List<PuntoDeControlEntity> findPuntosDeControlByIdCarrera(int idCarrera) {
        return db.executeQueryPojo(PuntoDeControlEntity.class, SQL_FIND_PUNTO_BY_ID_CARRERA, idCarrera);
        
    }

    public void addPuntoDeControl(PuntoDeControlEntity punto) {
        db.executeUpdate(SQL_ADD_PUNTO_DE_CONTROL, punto.getIdCarrera(), punto.getNombre());
    }
	
}
