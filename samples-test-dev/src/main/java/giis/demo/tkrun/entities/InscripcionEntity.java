package giis.demo.tkrun.entities;

public class InscripcionEntity {

	private enum EstadosIncsripcion {
		INSCRITO, PREINSCRITO
	};
	
	private String idCarrera;

	private String emailAtleta;
	
	private EstadosIncsripcion estado;
	private int dorsal;

	public InscripcionEntity(String emailAtleta, String idCarrera, boolean pagado) {
		this.emailAtleta = emailAtleta;
		this.idCarrera = idCarrera;

		this.setDorsal(generarDorsal());

		if (pagado) {
			setEstado(EstadosIncsripcion.INSCRITO);
		} else {
			setEstado(EstadosIncsripcion.PREINSCRITO);
		}
	}

	public int generarDorsal() {
		return 0;
	}

	public String getEmailAtleta() {
		return emailAtleta;
	}

	public String getIdCarrera() {
		return idCarrera;
	}

	public EstadosIncsripcion getEstado() {
		return estado;
	}

	public void setEstado(EstadosIncsripcion estado) {
		this.estado = estado;
	}

	public int getDorsal() {
		return dorsal;
	}

	private void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

}
