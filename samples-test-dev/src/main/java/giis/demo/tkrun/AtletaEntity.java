package giis.demo.tkrun;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AtletaEntity {
	private String email;
	private String name;
	private String surname;
	private String birthDate;
	private boolean isMale;

	// Relaciones
	private List<InscripcionEntity> inscripciones = new ArrayList<>();

	public AtletaEntity(String email, String name, String surname, String birthDate, boolean isMale) {
		this.setEmail(email);
		this.setName(name);
		this.setSurname(surname);
		this.setBirthDate(birthDate);
		this.setMale(isMale);
	}

	public AtletaEntity(String email, boolean isMale, String birthDate) {
		this.setEmail(email);
		this.setBirthDate(birthDate);
		this.setMale(isMale);
	}

	public void registrarse(InscripcionEntity inscripcion) {
		inscripciones.add(inscripcion);
	}

	// Getters y setters
	// NO TODOS SON NECESARIOS
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
