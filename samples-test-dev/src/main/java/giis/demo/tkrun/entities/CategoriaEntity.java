package giis.demo.tkrun.entities;

public class CategoriaEntity {

	private int id;
	private int idCarrera;
	private String nombre;
	private int edadInicial;
	private int edadFinal;
	private String sexo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdadInicial() {
		return edadInicial;
	}

	public void setEdadInicial(int edadInicial) {
		this.edadInicial = edadInicial;
	}

	public int getEdadFinal() {
		return edadFinal;
	}

	public void setEdadFinal(int edadFinal) {
		this.edadFinal = edadFinal;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
