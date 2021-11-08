package app.tkrun.entities;

public class ClasificacionEntity {

	private int idCarrera;
	private int idCategoria;
	private String nombreAtleta;
	private String nombreCategoria;
	private String genero;
	private int posicion;
	private String tiempo;
	
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	public void setNombreAtleta(String nombreAtleta) {
		this.nombreAtleta = nombreAtleta;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public String getGenero() {
		return genero;
	}
	public int getPosicion() {
		return posicion;
	}
	public String getTiempo() {
		return tiempo;
	}
	public String getNombreAtleta() {
		return nombreAtleta;
	}
	
	
}
