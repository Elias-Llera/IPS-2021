package app.tkrun.entities;

public class DevolucionEntity {

	private String emailAtleta;
	private int idCarrera;
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
	public int getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	
}
