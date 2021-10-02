package giis.demo.tkrun;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CarreraModel {

	private enum TipoCarrera {URBANA, TIERRA};
	
	private Long idCarrera;
    private String nombreCarrera; 
    private TipoCarrera tipo;
    private String descripcion;
    private Date inicioInscripcion;
    private Date finInscripcion;
    private double precioInscripcion;
    private Date fechaCarrera;
    
    //Relaciones
    private List<InscripcionModel> inscripciones = new ArrayList<>();;
    
    public CarreraModel(Long idCarrera, Date inicioInscripcion, Date finInscripcion, double precioInscripcion) {
    	setIdCarrera(idCarrera);
    	setInicioInscripcion(inicioInscripcion);
    	setFinInscripcion(finInscripcion);
    	setPrecioInscripcion(precioInscripcion);
    }
    
    public void nuevoInscrito(InscripcionModel inscripcion) {
    	inscripciones.add(inscripcion);
    }

    
    //Getters y setters
    //NO TODOS SON NECESARIOS
	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public TipoCarrera getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarrera tipo) {
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
		return fechaCarrera;
	}

	public void setFechaCarrera(Date fechaCarrera) {
		this.fechaCarrera = fechaCarrera;
	}
}
