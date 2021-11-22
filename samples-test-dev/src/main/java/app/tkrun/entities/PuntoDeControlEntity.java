package app.tkrun.entities;

public class PuntoDeControlEntity {

	private int idCarrera;
	private String nombre;
	
	public int getIdCarrera() {
		return idCarrera;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCarrera;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntoDeControlEntity other = (PuntoDeControlEntity) obj;
		if (idCarrera != other.idCarrera)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}



}
