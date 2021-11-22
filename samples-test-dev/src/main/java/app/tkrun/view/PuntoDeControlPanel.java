package app.tkrun.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PuntoDeControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JButton btnDelete;

	/**
	 * Create the panel.
	 */
	public PuntoDeControlPanel() {
		
		JLabel lblNombre = new JLabel("Nombre:");
		add(lblNombre);
		
		textFieldNombre = new JTextField();
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		add(getBtnDelete());

	}

	public JButton getBtnDelete() {
		if(btnDelete == null) {
			btnDelete = new JButton("x");
		}
		return btnDelete;
	}
	
	public String getNombrePunto() {
		return textFieldNombre.getText();
	}
	
}
