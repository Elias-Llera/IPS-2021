package app.tkrun.entities;

public class TiempoParaTabla implements Comparable<TiempoParaTabla>{

	private String emailAtleta;
	private String tiempo;
	private int posicion;
	private String nombreCategoria;
	private int dorsal;
	
	public String getEmailAtleta() {
		return emailAtleta;
	}
	public void setEmailAtleta(String emailAtleta) {
		this.emailAtleta = emailAtleta;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public int getDorsal() {
		return dorsal;
	}
	@Override
	public int compareTo(TiempoParaTabla o) {
		if(tiempo.compareTo(o.tiempo) < 0) {
			return -1;
		}
		
		if(tiempo.compareTo(o.tiempo) > 0) {
			return 1;
		}
		
		return 0;
	}
	
}
