package giis.demo.tkrun.entities;

import java.sql.Date;

public class CarreraEntity {
	
	private Long idCarrera;
    private String nombre; 
    private String tipo;
    private String descripcion;
    private Date inicioInscripcion;
    private Date finInscripcion;
    private double precioInscripcion;
    private Date fecha;
    private int plazas;
    
	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreCarrera() {
		return nombre;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombre = nombreCarrera;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getInicioInscripcion() {
		return inicioInscripcion;
	}

	public void setInicioInscripcion(Date inicioInscripcion) {
		this.inicioInscripcion = inicioInscripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioInscripcion() {
		return precioInscripcion;
	}

	public void setPrecioInscripcion(double precioInscripcion) {
		this.precioInscripcion = precioInscripcion;
	}

	public Date getFinInscripcion() {
		return finInscripcion;
	}

	public void setFinInscripcion(Date finInscripcion) {
		this.finInscripcion = finInscripcion;
	}

	public Date getFechaCarrera() {
		return fecha;
	}

	public void setFechaCarrera(Date fechaCarrera) {
		this.fecha = fechaCarrera;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
}
