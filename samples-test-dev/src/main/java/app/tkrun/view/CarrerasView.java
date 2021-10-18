package app.tkrun.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * Vista de la pantalla que muestra las carreras activas y permite interactuar con ellas.
 * <br/>Se ha generado con WindowBulder y modificado para ser conforme a MVC teniendo en cuenta:
 * - Se elimina main (es invocada desde CarrerasMain) y se incluye Title en el frame
 * - No se incluye ningun handler de eventos pues estos van en el controlador
 * - Las tablas se encierran en JOptionPane para que se puedan visualizar las cabeceras
 * - Se asinga nombre a las tablas si se van a automatizar la ejecucion de pruebas
 * - Incluye al final los metodos adicionales necesarios para acceder al UI desde el controlador
 */
public class CarrerasView {

	private JFrame frame;
	private JTextField txtFechaHoy;
	private JButton btnTabCarreras;
	private JTable tabCarreras;
	private JButton btnAceptar;
	private JScrollPane tablePanel;
	private JButton btnParticipantes;
	
	/**
	 * Create the application.
	 */
	public CarrerasView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Carreras");
		frame.setName("Carreras");
		frame.setBounds(0, 0, 569, 298);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][][][][]"));
		final JLabel lblFechaHoy;
		
		lblFechaHoy = new JLabel("Fecha de hoy:");
		frame.getContentPane().add(lblFechaHoy, "flowx,cell 0 3");
		
		txtFechaHoy = new JTextField();
		txtFechaHoy.setName("txtFechaHoy");
		frame.getContentPane().add(txtFechaHoy, "cell 0 3,growx");
		txtFechaHoy.setColumns(10);
		
		btnTabCarreras = new JButton("Ver carreras en esta tabla");
		lblFechaHoy.setLabelFor(btnTabCarreras);
		frame.getContentPane().add(btnTabCarreras, "cell 0 3");
		
		JLabel lblLbltable = new JLabel("Proximas carreras:");
		frame.getContentPane().add(lblLbltable, "cell 0 4");
		
		//Incluyo la tabla en un JScrollPane y anyado este en vez de la tabla para poder ver los headers de la tabla
		tabCarreras = new JTable();
		tabCarreras.setName("tabCarreras");
		tabCarreras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabCarreras.setDefaultEditor(Object.class, null); //readonly
		tablePanel = new JScrollPane(tabCarreras);
		frame.getContentPane().add(tablePanel, "cell 0 5,grow");
		
		btnParticipantes = new JButton("Participantes");
		btnParticipantes.setEnabled(false);
		
		frame.getContentPane().add(btnParticipantes, "flowx,cell 0 7,alignx center");
		
		btnAceptar = new JButton("Inscribirse");
		btnAceptar.setEnabled(false);
		btnAceptar.setMnemonic('I');
		btnAceptar.setHorizontalAlignment(SwingConstants.RIGHT);
		frame.getContentPane().add(btnAceptar, "cell 0 7,alignx right");
	}

	

	//Getters y Setters anyadidos para acceso desde el controlador (repersentacion compacta)
	public JFrame getFrame() { return this.frame; }
	public String getFechaHoy()  { return this.txtFechaHoy.getText(); }
	public void setFechaHoy(String fechaIso)  { this.txtFechaHoy.setText(fechaIso); }
	public JButton getBtnTablaCarreras() { return this.btnTabCarreras; }
	public JTable getTablaCarreras() { return this.tabCarreras; }
	public JButton getBtnAceptar() { return this.btnAceptar; }
	public JButton getBtnParticipantes() { return this.btnParticipantes; }
	public JScrollPane getTablePanel() {return this.tablePanel; };

	
}
