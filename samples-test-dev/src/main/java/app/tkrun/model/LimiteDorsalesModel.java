package app.tkrun.model;

import app.tkrun.entities.LimiteDorsalesEntity;
import app.util.Database;

public class LimiteDorsalesModel {

	private static final String SQL_INSERT_LIMITE = "INSERT INTO limiteDorsales (idCarrera, numero, secuencial) VALUES(?, ?, ?)";


    private Database db = new Database();

    
    public void addLimiteDorsales(LimiteDorsalesEntity limite) {
        db.executeUpdate( SQL_INSERT_LIMITE , limite.getIdCarrera(), limite.getNumero(), limite.getSecuencial());
        System.out.println("limite insertado");
    }
}
