package app.tkrun.entities;

public class AtletaEntity {

	private String emailAtleta;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String sexo;
	private String causa;

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmailAtleta() {
		return emailAtleta;
	}

	public void setEmailAtleta(String email) {
		this.emailAtleta = email;
	}

}
