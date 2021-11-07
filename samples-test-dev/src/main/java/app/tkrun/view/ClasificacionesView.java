package app.tkrun.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.ClasificacionEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.model.ClasificacionModel;
import app.tkrun.model.InscripcionModel;
import app.util.SwingUtil;

import java.awt.GridLayout;
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
	private JTable tableClasificaciones;
	private JScrollPane tablePanelInscripciones;
	private List<ClasificacionEntity> clasificaciones;
	
	/**
	 * Create the frame.
	 */
	public ClasificacionesView(int idCarrera, String genero) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 300);
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
		clasificaciones = im.findClasificacion(idCarrera, genero);
		for(ClasificacionEntity clasificacion: clasificaciones) {
			if(clasificacion.getTiempo().equals("---")) {
				clasificaciones.remove(clasificacion);
				clasificaciones.add(clasificacion);
			}
		}
		TableModel tmodel = SwingUtil.getTableModelFromPojos(clasificaciones, new String[] { "idCarrera", "idCategoria", "nombreAtleta", "genero",
				"posicion", "tiempo"});
		tableClasificaciones.setModel(tmodel);
		SwingUtil.autoAdjustColumns(tableClasificaciones);

	
	}

}
