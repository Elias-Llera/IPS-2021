package giis.demo.tkrun;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AtletaModel {
	
	private String email;
	private String name;
	private String surname;
	private Date birthDate;
	private boolean isMale;
	
	//Relaciones
	private List<InscripcionModel> inscripciones = new ArrayList<>();
	
	public AtletaModel(String email, boolean isMale, Date birthDate) {
		this.setEmail(email);
		this.setBirthDate(birthDate);
		this.setMale(isMale);
	}
	
	public void registrarse(InscripcionModel inscripcion){
		inscripciones.add(inscripcion);
	}

	
	//Getters y setters
	//NO TODOS SON NECESARIOS
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
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
