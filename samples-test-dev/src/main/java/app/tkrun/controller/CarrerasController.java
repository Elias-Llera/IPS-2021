package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.PlazosDeInscripcionEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.PlazosDeInscripcionModel;
import app.tkrun.view.CarrerasView;
import app.tkrun.view.ParticipantesView;
import app.util.SwingUtil;

/**
 * Controlador para la funcionalidad de visualizacion de carreras para la
 * inscripcion. Es el punto de entrada de esta pantalla que se invocará:
 * -instanciando el controlador con la vista y el modelo -ejecutando
 * initController que instalara los manejadores de eventos
 */
public class CarrerasController {
	private CarreraModel model;
	private CarrerasView view;
	AtletaModel atletaModel = new AtletaModel();
	private String lastSelectedKey = ""; // recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla
											// de carreras

	public CarrerasController(CarreraModel m, CarrerasView v) {
		this.model = m;
		this.view = v;
		// no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}

	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los
	 * objetos del UI. Cada manejador de eventos se instancia de la misma forma,
	 * para que invoque un metodo privado de este controlador, encerrado en un
	 * manejador de excepciones generico para mostrar ventanas emergentes cuando
	 * ocurra algun problema o excepcion controlada.
	 */
	public void initController() {
		// ActionListener define solo un metodo actionPerformed(), es un interfaz
		// funcional que se puede invocar de la siguiente forma:
		// view.getBtnTablaCarreras().addActionListener(e -> getListaCarreras());
		// ademas invoco el metodo que responde al listener en el exceptionWrapper para
		// que se encargue de las excepciones
		view.getBtnTablaCarreras().addActionListener(e -> SwingUtil.exceptionWrapper(() -> getListaCarreras()));

		view.getBtnParticipantes().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaParticipantes();
			}
		});
		// En el caso del mouse listener (para detectar seleccion de una fila) no es un
		// interfaz funcional puesto que tiene varios metodos
		// ver discusion:
		// https://stackoverflow.com/questions/21833537/java-8-lambda-expressions-what-about-multiple-methods-in-nested-class
		view.getTablaCarreras().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// no usa mouseClicked porque al establecer seleccion simple en la tabla de
				// carreras
				// el usuario podria arrastrar el raton por varias filas e interesa solo la
				// ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});

		view.getBtnAceptar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> openInscriptionView());
			}

			private void openInscriptionView() {
				JTable tabla = view.getTablaCarreras();
				String nombreCarrera = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
				int idCarrera = (Integer) tabla.getValueAt(tabla.getSelectedRow(), 0);
				new InscripcionController().init(nombreCarrera, idCarrera);
				;
			}
		});

		view.getBtnCrearCarrera().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> openCrearCarreraView());
			}

			private void openCrearCarreraView() {
				new CrearCarrerasController().init();

			}
		});

	}

	protected void mostrarVentanaParticipantes() {
		JTable tabla = view.getTablaCarreras();
		int idCarrera = (Integer) tabla.getValueAt(tabla.getSelectedRow(), 0);

		ParticipantesView vp = new ParticipantesView(idCarrera);

		vp.setLocationRelativeTo(null);
		vp.setVisible(true);
	}

	public void initView() {
		// Inicializa la fecha de hoy a un valor que permitira mostrar carreras en
		// diferentes fases
		// y actualiza los datos de la vista
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = LocalDate.now().format(formatter);
		view.setFechaHoy(date);
		this.getListaCarreras();

		// Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true);
	}

	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de
	 * objetos del modelo y usar metodo de SwingUtil para crear un tablemodel que se
	 * asigna finalmente a la tabla.
	 */
	public void getListaCarreras() {
		PlazosDeInscripcionModel sacarPrecio = new PlazosDeInscripcionModel();

		List<CarreraEntity> carreras = model.getListaCarreras(view.getFechaHoy());
		for (CarreraEntity carrera : carreras) {
			int plazasOcupadas = atletaModel.findAtletasParticipantesEnCarrera(carrera.getIdCarrera());
			PlazosDeInscripcionEntity precio = sacarPrecio.getListaPlazosInscripciones(carrera.getIdCarrera(),
					view.getFechaHoy());
			if (precio != null) {
				carrera.setPrecio(Double.toString(precio.getPrecio()));
			}

			if (plazasOcupadas > 0) {
				carrera.setPlazas(carrera.getPlazas() - plazasOcupadas);
			}

		}
		TableModel tmodel = SwingUtil.getTableModelFromPojos(carreras,
				new String[] { "nombre", "fecha", "tipo", "distancia", "plazas", "precio" });
		view.getTablaCarreras().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaCarreras());

		// Como se guarda la clave del ultimo elemento seleccionado, restaura la
		// seleccion de los detalles
		this.restoreDetail();
	}

	/**
	 * Restaura la informacion del detalle de la carrera para visualizar los valores
	 * correspondientes a la ultima clave almacenada.
	 */
	public void restoreDetail() {
		// Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la
		// tabla)
		this.lastSelectedKey = SwingUtil.selectAndGetSelectedKey(view.getTablaCarreras(), this.lastSelectedKey);
		// Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo
		// reinicia
	}

	/**
	 * Al seleccionar un item de la tabla muestra el detalle con el valor del
	 * porcentaje de descuento de la carrera seleccinada y los valores de esta
	 * entidad
	 */
	public void updateDetail() {
		// Obtiene la clave seleccinada y la guarda para recordar la seleccion en
		// futuras interacciones
//		this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTablaCarreras());
		view.getBtnAceptar().setEnabled(true);
		view.getBtnParticipantes().setEnabled(true);
	}

}
