package giis.demo.tkrun.entities;

public class InscripcionEntity {

	private String emailAtleta;
	private Long idCarrera;
	private String estado;
	private int dorsal;
	private int idCategoria;

	public String getEmailAtleta() {
		return emailAtleta;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
