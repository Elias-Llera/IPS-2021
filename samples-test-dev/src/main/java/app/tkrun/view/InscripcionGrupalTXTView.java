package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InscripcionGrupalTXTView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblArchivo;
	private JTextField textFieldArchivo;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnSalir;
	private JButton btnInsertar;
	private JLabel lblInscritosInfo;
	private JLabel lblNoInscritosInfo;
	private JLabel lblNumeroTotalIns;
	private JTextField textFieldNumeroIns;
	private JLabel lblNumeroTotalNoIns;
	private JTextField textFieldNumeroNoIns;
	private JButton btnRefrescar;
	private JScrollPane scrollPane;
	private JTable tableInscritosArchivo;
	private JScrollPane scrollPane_1;
	private JTable tableNoInscritosArchivo;
	private JLabel lblTotal;
	private JTextField textFieldTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InscripcionGrupalTXTView dialog = new InscripcionGrupalTXTView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InscripcionGrupalTXTView() {
		setTitle("Inscripcion grupal por archivo");
		setBounds(100, 100, 592, 623);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblArchivo());
		contentPanel.add(getTextFieldArchivo());
		contentPanel.add(getPanel());
		contentPanel.add(getPanel_1());
		contentPanel.add(getBtnSalir());
		contentPanel.add(getBtnInsertar());
		contentPanel.add(getLblInscritosInfo());
		contentPanel.add(getLblNoInscritosInfo());
		contentPanel.add(getLblNumeroTotalIns());
		contentPanel.add(getTextFieldNumeroIns());
		contentPanel.add(getLblNumeroTotalNoIns());
		contentPanel.add(getTextFieldNumeroNoIns());
		contentPanel.add(getBtnRefrescar());
		contentPanel.add(getLblTotal());
		contentPanel.add(getTextFieldTotal());
	}

	public JLabel getLblArchivo() {
		if (lblArchivo == null) {
			lblArchivo = new JLabel("Nombre del archivo");
			lblArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblArchivo.setBounds(10, 52, 137, 29);
		}
		return lblArchivo;
	}

	public JTextField getTextFieldArchivo() {
		if (textFieldArchivo == null) {
			textFieldArchivo = new JTextField();
			textFieldArchivo.setBounds(164, 58, 354, 20);
			textFieldArchivo.setColumns(10);
		}
		return textFieldArchivo;
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 139, 554, 136);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}

	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(10, 315, 556, 136);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return panel_1;
	}

	public JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setBounds(475, 543, 89, 23);
		}
		return btnSalir;
	}

	public JButton getBtnInsertar() {
		if (btnInsertar == null) {
			btnInsertar = new JButton("Insertar");
			btnInsertar.setBounds(349, 107, 89, 23);
		}
		return btnInsertar;
	}

	public JLabel getLblInscritosInfo() {
		if (lblInscritosInfo == null) {
			lblInscritosInfo = new JLabel("Inscritos:");
			lblInscritosInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblInscritosInfo.setBounds(250, 105, 100, 23);
		}
		return lblInscritosInfo;
	}

	public JLabel getLblNoInscritosInfo() {
		if (lblNoInscritosInfo == null) {
			lblNoInscritosInfo = new JLabel("No inscritos:");
			lblNoInscritosInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNoInscritosInfo.setBounds(250, 286, 100, 23);
		}
		return lblNoInscritosInfo;
	}

	public JLabel getLblNumeroTotalIns() {
		if (lblNumeroTotalIns == null) {
			lblNumeroTotalIns = new JLabel("N\u00FAmero de inscritos:");
			lblNumeroTotalIns.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNumeroTotalIns.setBounds(10, 473, 142, 29);
		}
		return lblNumeroTotalIns;
	}

	public JTextField getTextFieldNumeroIns() {
		if (textFieldNumeroIns == null) {
			textFieldNumeroIns = new JTextField();
			textFieldNumeroIns.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldNumeroIns.setText("0");
			textFieldNumeroIns.setEditable(false);
			textFieldNumeroIns.setBounds(187, 471, 89, 36);
			textFieldNumeroIns.setColumns(10);
		}
		return textFieldNumeroIns;
	}

	public JLabel getLblNumeroTotalNoIns() {
		if (lblNumeroTotalNoIns == null) {
			lblNumeroTotalNoIns = new JLabel("N\u00FAmero de no inscritos:");
			lblNumeroTotalNoIns.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNumeroTotalNoIns.setBounds(10, 538, 154, 29);
		}
		return lblNumeroTotalNoIns;
	}

	public JTextField getTextFieldNumeroNoIns() {
		if (textFieldNumeroNoIns == null) {
			textFieldNumeroNoIns = new JTextField();
			textFieldNumeroNoIns.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldNumeroNoIns.setText("0");
			textFieldNumeroNoIns.setEditable(false);
			textFieldNumeroNoIns.setColumns(10);
			textFieldNumeroNoIns.setBounds(187, 536, 89, 36);
		}
		return textFieldNumeroNoIns;
	}

	public JButton getBtnRefrescar() {
		if (btnRefrescar == null) {
			btnRefrescar = new JButton("Actualizar");
			btnRefrescar.setBounds(448, 107, 116, 23);
		}
		return btnRefrescar;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableInscritosArchivo());
		}
		return scrollPane;
	}

	public JTable getTableInscritosArchivo() {
		if (tableInscritosArchivo == null) {
			tableInscritosArchivo = new JTable();
		}
		return tableInscritosArchivo;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTableNoInscritosArchivo());
		}
		return scrollPane_1;
	}

	public JTable getTableNoInscritosArchivo() {
		if (tableNoInscritosArchivo == null) {
			tableNoInscritosArchivo = new JTable();
		}
		return tableNoInscritosArchivo;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblTotal.setBounds(286, 509, 46, 29);
		}
		return lblTotal;
	}

	public JTextField getTextFieldTotal() {
		if (textFieldTotal == null) {
			textFieldTotal = new JTextField();
			textFieldTotal.setText("0");
			textFieldTotal.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldTotal.setEditable(false);
			textFieldTotal.setColumns(10);
			textFieldTotal.setBounds(331, 507, 89, 36);
		}
		return textFieldTotal;
	}
}
