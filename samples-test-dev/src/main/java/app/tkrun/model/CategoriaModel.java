package app.tkrun.model;

import java.util.List;

import app.tkrun.entities.CategoriaEntity;
import app.util.Database;

public class CategoriaModel {

	private static final String SQL_ADD_CATEGORIA = "INSERT INTO CATEGORIAS (idCategoria, idCarrera, nombre, edadInicio, edadFinal, sexo) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_CATEGORIA = "SELECT idCategoria, idCarrera, nombre, edadInicio, edadFinal, sexo from CATEGORIAS where idCategoria=?";
	private static final String SQL_FIND_CATEGORIA_CARRERA = "SELECT idCategoria, idCarrera, nombre, edadInicio, edadFinal, sexo from CATEGORIAS where idCarrera=?";
	private static final String SQL_FIND_CATEGORIA_PARA_ATLETA = "SELECT idCategoria, idCarrera, nombre, edadInicio, edadFinal, sexo from CATEGORIAS where idCarrera=? AND edadInicio <= ? AND edadFinal >= ? AND sexo = ?";
	private static final String SQL_FIND_NEXT_ID = "SELECT max(idCategoria) from CATEGORIAS";

	private Database db = new Database();

	/**
	 * Obtiene todos los datos de la categoria con un id dado
	 */
	public CategoriaEntity findCategoria(int id) {
		List<CategoriaEntity> categorias = db.executeQueryPojo(CategoriaEntity.class, SQL_FIND_CATEGORIA, id);
		return categorias.size() == 0 ? null : categorias.get(0);
	}
	
	/**
	 * Obtiene todos los datos de la categoria asociada a una carrera
	 */
	public CategoriaEntity findCategoriaCarrera(int id) {
		List<CategoriaEntity> categorias = db.executeQueryPojo(CategoriaEntity.class, SQL_FIND_CATEGORIA_CARRERA, id);
		return categorias.size() == 0 ? null : categorias.get(0);
	}
	
	/**
	 * Obtiene todas las categorias de una carrera
	 * @param id
	 * @return
	 */
	public List<CategoriaEntity> findCategoriasCarrera(int id) {
		List<CategoriaEntity> categorias = db.executeQueryPojo(CategoriaEntity.class, SQL_FIND_CATEGORIA_CARRERA, id);
		return categorias.size() == 0 ? null : categorias;
	}


	/**
	 * Obtiene todos los datos de la carrera para un atleta en concreto
	 */
	public CategoriaEntity findCategoria(int id_carrera, int edad, String sexo) {
		List<CategoriaEntity> categorias = db.executeQueryPojo(CategoriaEntity.class, SQL_FIND_CATEGORIA_PARA_ATLETA,
				id_carrera, edad, edad, sexo);
		return categorias.size() == 0 ? null : categorias.get(0);
	}

	public void addCategoria(CategoriaEntity categoria) {
		db.executeUpdate(SQL_ADD_CATEGORIA, categoria.getIdCategoria(), categoria.getIdCarrera(),
				categoria.getNombre(), categoria.getEdadInicio(), categoria.getEdadFinal(), categoria.getSexo());
	}

	public int getNextId() {
		Object o = db.executeQueryArray(SQL_FIND_NEXT_ID).get(0)[0];
		return o.equals(null) ? 1 : ((Integer) o) + 1;
	}

}
