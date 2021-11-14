package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private JButton btnRefrescar;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public PlanificacionCarrerasView() {
		setTitle("Plazos de inscripcion");
		setBounds(100, 100, 477, 416);
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
			panel.add(getBtnRefrescar());
			panel.add(getPanel_1());
		}
		return panel;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Salir");
			btnCancelar.setBounds(352, 343, 89, 23);
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
			btnAdd.setBounds(253, 343, 89, 23);
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
	public JButton getBtnRefrescar() {
		if (btnRefrescar == null) {
			btnRefrescar = new JButton("Refrescar");
			btnRefrescar.setBounds(335, 165, 106, 23);
		}
		return btnRefrescar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(20, 203, 421, 118);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addComponent(getScrollPane_1(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addComponent(getScrollPane_1(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
			);
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
}
