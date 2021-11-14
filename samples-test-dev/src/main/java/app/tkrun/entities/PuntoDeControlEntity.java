package app.tkrun.entities;

public class PuntoDeControlEntity {

	private int idCarrera;
	private double km;
	
	public int getIdCarrera() {
		return idCarrera;
	}
	
	public double getKm() {
		return km;
	}
	
	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	
	public void setKm(double km) {
		this.km = km;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCarrera;
		long temp;
		temp = Double.doubleToLongBits(km);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(km) != Double.doubleToLongBits(other.km))
			return false;
		return true;
	}

}
