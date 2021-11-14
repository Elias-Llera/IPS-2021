package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import app.tkrun.entities.ClasificacionParaTabla;
import app.util.SwingUtil;

public class ClasificacionesView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable tableClasificaciones;
	private JScrollPane tablePanelInscripciones;
	private List<ClasificacionParaTabla> clasificacion;
	private JPanel panelSouth;
	private JButton btnGenerar;
	
	/**
	 * Create the frame.
	 */
	public ClasificacionesView(List<ClasificacionParaTabla> clasificacion) {
		this.clasificacion = clasificacion;
		setTitle("Clasificacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tableClasificaciones = new JTable();
		tableClasificaciones.setName("tabClasificaciones");
		tableClasificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClasificaciones.setDefaultEditor(Object.class, null); //readonly
		contentPane.setLayout(new BorderLayout(0, 0));
		tablePanelInscripciones = new JScrollPane(tableClasificaciones);
		contentPane.add(tablePanelInscripciones);
		
		panelSouth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSouth.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		panelSouth.add(getBtnGenerar(), BorderLayout.NORTH);
		
		getListaCarreras();
		
	}
	
	public JButton getBtnGenerar() {
		if(btnGenerar == null) {
			btnGenerar = new JButton("Generar clasificacion");
		}
		return btnGenerar;
	}
	
	public void getListaCarreras() {
		TableModel tmodel = SwingUtil.getTableModelFromPojos(clasificacion, new String[] { "idCarrera", "idCategoria","nombreCategoria", "nombreAtleta", "genero",
				"posicion", "tiempo"});
		tableClasificaciones.setModel(tmodel);
		SwingUtil.autoAdjustColumns(tableClasificaciones);
	}
	
}
