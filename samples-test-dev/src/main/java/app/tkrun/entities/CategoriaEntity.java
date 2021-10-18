package app.tkrun.entities;

public class CategoriaEntity {

	private int idCategoria;
	private int idCarrera;
	private String nombre;
	private int edadInicio;
	private int edadFinal;
	private String sexo;
	
	
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEdadInicio(int edadInicio) {
		this.edadInicio = edadInicio;
	}
	public void setEdadFinal(int edadFinal) {
		this.edadFinal = edadFinal;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdadInicio() {
		return edadInicio;
	}
	public int getEdadFinal() {
		return edadFinal;
	}
	public String getSexo() {
		return sexo;
	}

	
}
