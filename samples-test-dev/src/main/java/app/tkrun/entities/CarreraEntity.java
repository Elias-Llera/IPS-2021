package app.tkrun.entities;

public class CarreraEntity {

	private int idCarrera;
	private String nombre;
	private String tipo;
	private String descripcion;
	private String fecha;
	private int plazas;
	private double distancia;
	
	private double precio;
	
	

	public double getPrecioInscripcion() {
		return precio;
	}

	public void setPrecioInscripcion(double precio) {
		this.precio = precio;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreCarrera) {
		this.nombre = nombreCarrera;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaCarrera() {
		return fecha;
	}

	public void setFechaCarrera(String fechaCarrera) {
		this.fecha = fechaCarrera;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
}
