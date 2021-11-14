package app.tkrun.entities;

public class ClasificacionParaTabla {
	private int idCarrera;
	private int idCategoria;
	private String nombreCategoria;
	private String nombreAtleta;
	private String genero;
	private String tiempo;
	private int posicion;

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreAtleta() {
		return nombreAtleta;
	}

	public void setNombreAtleta(String nombreAtleta) {
		this.nombreAtleta = nombreAtleta;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCatetoria) {
		this.idCategoria = idCatetoria;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getTiempo() {
		return this.tiempo;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getPosicion() {
		return this.posicion;
	}
}
