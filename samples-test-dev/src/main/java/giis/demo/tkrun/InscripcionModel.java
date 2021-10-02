package giis.demo.tkrun;

public class InscripcionModel {

	private enum EstadosIncsripcion {INSCRITO, PREINSCRITO};
	
	private String emailAtleta;
	private Long idCarrera;
	private EstadosIncsripcion estado;
	private int dorsal;
	
	public InscripcionModel(String emailAtleta, Long idCarrera, boolean pagado) {
		this.emailAtleta = emailAtleta;
		this.idCarrera = idCarrera;
		
		this.setDorsal(generarDorsal());
		
		if(pagado) {
			setEstado(EstadosIncsripcion.INSCRITO);
		}else {
			setEstado(EstadosIncsripcion.PREINSCRITO);
		}
	}
	
	public int generarDorsal() {
		return 0;
	}

	public String getEmailAtleta() {
		return emailAtleta;
	}

	public Long getIdCarrera() {
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
