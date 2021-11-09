package app.tkrun.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.ClasificacionEntity;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.ClasificacionModel;
import app.util.SwingUtil;

import java.awt.GridLayout;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ClasificacionesView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String genero;
	private int idCarrera;
	private ClasificacionModel im= new ClasificacionModel();
	private CarreraModel cm= new CarreraModel();
	private JTable tableClasificaciones;
	private JScrollPane tablePanelInscripciones;
	private List<ClasificacionEntity> clasificaciones;
	
	/**
	 * Create the frame.
	 */
	public ClasificacionesView(int idCarrera, String genero) {
		setTitle("Clasificacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 300);
		this.genero = genero;
		this.idCarrera = idCarrera;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		tableClasificaciones = new JTable();
		tableClasificaciones.setName("tabClasificaciones");
		tableClasificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClasificaciones.setDefaultEditor(Object.class, null); //readonly
		tablePanelInscripciones = new JScrollPane(tableClasificaciones);
		contentPane.add(tablePanelInscripciones, "cell 0 0,grow");
		
		getListaCarreras();
		
	}
	
	public void getListaCarreras() {
		CarreraEntity ce = cm.findCarrera(idCarrera);
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		
		Date fecha = new Date();
		try {
			fecha = formato.parse(ce.getFecha());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
		
		Date actual = Date.from(instant);
		
		if(!actual.after(fecha)) {
			
			JOptionPane.showMessageDialog(this, "La carrera aun no se ha celebrado", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			return;
		}
		clasificaciones = im.findClasificacion(idCarrera, genero);
		
		if(clasificaciones.isEmpty()) {
			
			JOptionPane.showMessageDialog(this, "La clasificacion aun no se ha procesado", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
		List<ClasificacionEntity> noAcabadas = new ArrayList<ClasificacionEntity>();
		List<ClasificacionEntity> acabadas = new ArrayList<ClasificacionEntity>();
		List<ClasificacionEntity> definitiva = new ArrayList<ClasificacionEntity>();
		
		
		for(ClasificacionEntity clasificacion: clasificaciones) {
			if(clasificacion.getTiempo().equals("---")) {
				noAcabadas.add(clasificacion);
				
			}else {
				acabadas.add(clasificacion);
			}
		}
		
		for(ClasificacionEntity clasificacion: acabadas) {
			definitiva.add(clasificacion);
		}
		
		for(ClasificacionEntity clasificacion: noAcabadas) {
			definitiva.add(clasificacion);
		}
		
		TableModel tmodel = SwingUtil.getTableModelFromPojos(definitiva, new String[] { "idCarrera", "idCategoria","nombreCategoria", "nombreAtleta", "genero",
				"posicion", "tiempo"});
		tableClasificaciones.setModel(tmodel);
		SwingUtil.autoAdjustColumns(tableClasificaciones);

	
	}

}
