package app.tkrun.entities;

public class DevolucionEntity {

	private String emailAtleta;
	private String nombreCarrera;
	private double cantidad;
	
	public String getEmailAtleta() {
		return emailAtleta;
	}
	public void setEmailAtleta(String emailAtleta) {
		this.emailAtleta = emailAtleta;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
}
