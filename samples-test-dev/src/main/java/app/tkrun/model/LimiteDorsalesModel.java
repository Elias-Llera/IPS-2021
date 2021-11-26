package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.LimiteDorsalesEntity;
import app.util.Database;
import app.util.Util;

public class LimiteDorsalesModel {

	private static final String SQL_INSERT_LIMITE = "INSERT INTO limiteDorsales (idCarrera, numero, secuencial) VALUES(?, ?, ?)";
	private static final String FIND_LIMITE ="SELECT * from limiteDorsales where idCarrera=?";

    private Database db = new Database();

    
    public void addLimiteDorsales(LimiteDorsalesEntity limite) {
        db.executeUpdate( SQL_INSERT_LIMITE , limite.getIdCarrera(), limite.getNumero(), limite.getSecuencial());
        System.out.println("limite insertado");
    }
    
    public LimiteDorsalesEntity findLimite(int id) {
        List<LimiteDorsalesEntity> carreras = db.executeQueryPojo(LimiteDorsalesEntity.class, FIND_LIMITE, id);
        Util.validateCondition(!carreras.isEmpty(), "Id de carrera no encontrado: " + id);
        return carreras.get(0);
    }
}
