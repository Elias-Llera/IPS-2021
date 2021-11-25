package app.tkrun.entities;

public class LimiteDorsalesEntity {

	private int idCarrera;
	private int numero;
	private String secuencial;
	
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	
	public int getIdCarrera() {
		return idCarrera;
	}
	public int getNumero() {
		return numero;
	}
	public String getSecuencial() {
		return secuencial;
	}
	
}
