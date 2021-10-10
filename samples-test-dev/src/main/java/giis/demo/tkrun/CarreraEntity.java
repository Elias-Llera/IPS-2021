package giis.demo.tkrun;

/**
 * Datos del modelo de dominio de cada una de las carreras
 * IMPORTANTE: Cuando se usan los componentes de Apache Commons DbUtils debe
 * mantenerse de forma estricta el convenio de capitalización de Java:
 *  - Capitalizar todas las palabras que forman un identificador 
 *    excepto la primera letra de nombres de métodos y variables.
 *  - No utilizar subrayados
 * Seguir tambien estos mismos criterios en los nombres de tablas y campos de la BD
 */
public class CarreraEntity {
	private Long idCarrera;
    private String nombre; 
    private String tipo;
    private String descripcion;
    private String inicioInscripcion;
    private String finInscripcion;
    private double precioInscripcion;
    private String fecha;
    private int plazas;
    private int distancia;
	public Long getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getInicioInscripcion() {
		return inicioInscripcion;
	}
	public void setInicioInscripcion(String inicioInscripcion) {
		this.inicioInscripcion = inicioInscripcion;
	}
	public String getFinInscripcion() {
		return finInscripcion;
	}
	public void setFinInscripcion(String finInscripcion) {
		this.finInscripcion = finInscripcion;
	}
	public double getPrecioInscripcion() {
		return precioInscripcion;
	}
	public void setPrecioInscripcion(double precioInscripcion) {
		this.precioInscripcion = precioInscripcion;
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
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
    


}
