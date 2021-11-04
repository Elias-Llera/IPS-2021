package app.tkrun.entities;

public class PlazosDeInscripcionEntity {

	private int idPlazosDeInscripcion;
	private int idCarrera;
	private String fechaInicio;
	private String fechaFin;
	private double precio;

	public int getIdPlazosDeInscripcion() {
		return idPlazosDeInscripcion;
	}

	public void setIdPlazosDeInscripcion(int idPlazosDeInscripcion) {
		this.idPlazosDeInscripcion = idPlazosDeInscripcion;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
