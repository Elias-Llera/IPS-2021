package app.tkrun.entities;

public class TiempoEntity {

	private int idCarrera;
	private String emailAtleta;
	private String tiempo;
	private String nombre;
	
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public String getTiempo() {
		return tiempo;
	}

	public String getEmailAtleta() {
		return emailAtleta;
	}

	public void setEmailAtleta(String emailAtleta) {
		this.emailAtleta = emailAtleta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
