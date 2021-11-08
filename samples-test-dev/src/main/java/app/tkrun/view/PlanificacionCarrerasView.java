package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlanificacionCarrerasView extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btnCancelar;
	private JLabel lblInfo;
	private JLabel lblPlazoInicio;
	private JLabel lblPlazoFin;
	private JLabel lblPrecio;
	private JButton btnAdd;
	private JTextField textFieldInscripcionInicio;
	private JTextField textFieldInscripcionFin;
	private JTextField textFieldInscripcionPrecio;

	/**
	 * Create the dialog.
	 */
	public PlanificacionCarrerasView() {
		setTitle("Plazos de inscripcion");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnCancelar());
			panel.add(getLblInfo());
			panel.add(getLblPlazoInicio());
			panel.add(getLblPlazoFin());
			panel.add(getLblPrecio());
			panel.add(getBtnAdd());
			panel.add(getTextFieldInscripcionInicio());
			panel.add(getTextFieldInscripcionFin());
			panel.add(getTextFieldInscripcionPrecio());
		}
		return panel;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Salir");
			btnCancelar.setBounds(335, 227, 89, 23);
		}
		return btnCancelar;
	}

	public JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("Como m\u00E1ximo solo se pueden a\u00F1adir 4 plazos de inscripcion");
			lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblInfo.setBounds(10, 11, 414, 56);
		}
		return lblInfo;
	}

	public JLabel getLblPlazoInicio() {
		if (lblPlazoInicio == null) {
			lblPlazoInicio = new JLabel("Inicio");
			lblPlazoInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPlazoInicio.setBounds(20, 78, 54, 23);
		}
		return lblPlazoInicio;
	}

	public JLabel getLblPlazoFin() {
		if (lblPlazoFin == null) {
			lblPlazoFin = new JLabel("Fin");
			lblPlazoFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPlazoFin.setBounds(20, 115, 54, 23);
		}
		return lblPlazoFin;
	}

	public JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio");
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrecio.setBounds(20, 152, 54, 23);
		}
		return lblPrecio;
	}

	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("A\u00F1adir");
			btnAdd.setBounds(230, 227, 89, 23);
		}
		return btnAdd;
	}

	public JTextField getTextFieldInscripcionInicio() {
		if (textFieldInscripcionInicio == null) {
			textFieldInscripcionInicio = new JTextField();
			textFieldInscripcionInicio.setBounds(84, 78, 235, 20);
			textFieldInscripcionInicio.setColumns(10);
		}
		return textFieldInscripcionInicio;
	}

	public JTextField getTextFieldInscripcionFin() {
		if (textFieldInscripcionFin == null) {
			textFieldInscripcionFin = new JTextField();
			textFieldInscripcionFin.setBounds(84, 118, 235, 20);
			textFieldInscripcionFin.setColumns(10);
		}
		return textFieldInscripcionFin;
	}

	public JTextField getTextFieldInscripcionPrecio() {
		if (textFieldInscripcionPrecio == null) {
			textFieldInscripcionPrecio = new JTextField();
			textFieldInscripcionPrecio.setBounds(84, 155, 235, 20);
			textFieldInscripcionPrecio.setColumns(10);
		}
		return textFieldInscripcionPrecio;
	}
}
