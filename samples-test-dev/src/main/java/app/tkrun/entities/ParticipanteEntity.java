package app.tkrun.entities;

public class ParticipanteEntity {

	private String emailAtleta;
	private int idCarrera;
	private String estado;
	private int dorsal;
	
	private String nombreAtleta;
	private String apellidoAtleta;
	private String nombreCategoria;
	
	public void setEmailAtleta(String emailAtleta) {
		this.emailAtleta = emailAtleta;
	}
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public void setNombreAtleta(String nombreAtleta) {
		this.nombreAtleta = nombreAtleta;
	}
	public void setApellidoAtleta(String apellidoAtleta) {
		this.apellidoAtleta = apellidoAtleta;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getEmailAtleta() {
		return emailAtleta;
	}
	public int getIdCarrera() {
		return idCarrera;
	}
	public String getEstado() {
		return estado;
	}
	public int getDorsal() {
		return dorsal;
	}
	public String getNombreAtleta() {
		return nombreAtleta;
	}
	public String getApellidoAtleta() {
		return apellidoAtleta;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
}
