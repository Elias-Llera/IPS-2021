package app.tkrun.entities;

public class TiempoEntity {

	private int idCarrera;
	private String emailAtleta;
	private String tiempo;
	private double km;
	
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

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}
	
	
}
