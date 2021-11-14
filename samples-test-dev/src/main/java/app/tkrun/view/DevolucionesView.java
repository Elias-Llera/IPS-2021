package app.tkrun.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class DevolucionesView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabDevoluciones;
	private JScrollPane tablePanel;
	private JButton generateButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DevolucionesView dialog = new DevolucionesView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DevolucionesView() {
		setTitle("Confirmacion de pagos");
		setBounds(100, 100, 600, 415);
		getContentPane().setLayout(null);
		{
			generateButton = new JButton("Confirmacion de pagos");
			generateButton.setBounds(214, 333, 162, 32);
			getContentPane().add(generateButton);
			generateButton.setActionCommand("OK");
			getRootPane().setDefaultButton(generateButton);
		}		
		
		tabDevoluciones = new JTable();
		tabDevoluciones.setName("tabDevoluciones");
		tabDevoluciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabDevoluciones.setDefaultEditor(Object.class, null); //readonly
		tablePanel = new JScrollPane(tabDevoluciones);
		tablePanel.setBounds(10, 44, 564, 278);
		
		getContentPane().add(tablePanel);
		
		JLabel lblDevoluciones = new JLabel("Devoluciones:");
		lblDevoluciones.setBounds(10, 11, 119, 23);
		getContentPane().add(lblDevoluciones);
	}
	
	public JTable getTablaDevoluciones() {
		return tabDevoluciones;
	}
	
	public JButton getBtnGenerar() {
		return generateButton;
	}
	
}
