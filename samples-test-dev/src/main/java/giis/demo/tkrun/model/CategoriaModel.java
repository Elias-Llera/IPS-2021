package giis.demo.tkrun.model;

import java.util.List;

import giis.demo.tkrun.entities.CategoriaEntity;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class CategoriaModel {

	private static final String SQL_FIND_CATEGORIA = "SELECT idCategoria, idCarrera, nombre, edadInicio, edadFinal, sexo from CATEGORIAS where idCategoria=?";
	private static final String SQL_FIND_CATEGORIA_PARA_ATLETA = "SELECT idCategoria, idCarrera, nombre, edadInicio, edadFinal, sexo from CATEGORIAS where idCarrera=? AND edadInicio <= ? AND edadFinal >= ? AND sexo = ?";

	private Database db = new Database();

	/**
	 * Obtiene todos los datos de la categoria con un id dado
	 */
	public CategoriaEntity findCategoria(int id) {
		List<CategoriaEntity> categorias = db.executeQueryPojo(CategoriaEntity.class, SQL_FIND_CATEGORIA, id);
		Util.validateCondition(!categorias.isEmpty(), "Categoria no encontrada");
		return categorias.get(0);
	}
	
	/**
	 * Obtiene todos los datos de la carrera para un atleta en concreto
	 */
	public CategoriaEntity findCategoria(int id_carrera, int edad, String sexo) {
		List<CategoriaEntity> categorias = db.executeQueryPojo(CategoriaEntity.class, SQL_FIND_CATEGORIA_PARA_ATLETA, id_carrera, edad, sexo);
		Util.validateCondition(!categorias.isEmpty(), "Categoria no encontrada");
		return categorias.get(0);
	}

}
