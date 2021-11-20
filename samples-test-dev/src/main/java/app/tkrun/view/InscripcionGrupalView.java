package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;

public class InscripcionGrupalView extends JDialog {

	private JPanel contentPane;
	private JLabel lblNombreClub;
	private JTextField textFieldNombreClub;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JPanel panel;
	private JButton btnRefrescar;
	private JButton btnInsertar;
	private JScrollPane scrollPane;
	private JTable tableIGrupal;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscripcionGrupalView frame = new InscripcionGrupalView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InscripcionGrupalView() {
		setTitle("Inscripcion grupal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 427);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNombreClub());
		contentPane.add(getTextFieldNombreClub());
		contentPane.add(getLblEmail());
		contentPane.add(getTextFieldEmail());
		contentPane.add(getPanel());
		contentPane.add(getBtnRefrescar());
		contentPane.add(getBtnInsertar());
		contentPane.add(getBtnSalir());
	}

	public JLabel getLblNombreClub() {
		if (lblNombreClub == null) {
			lblNombreClub = new JLabel("Nombre del club");
			lblNombreClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNombreClub.setBounds(22, 40, 109, 27);
		}
		return lblNombreClub;
	}

	public JTextField getTextFieldNombreClub() {
		if (textFieldNombreClub == null) {
			textFieldNombreClub = new JTextField();
			textFieldNombreClub.setBounds(141, 45, 320, 20);
			textFieldNombreClub.setColumns(10);
		}
		return textFieldNombreClub;
	}

	public JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEmail.setBounds(22, 97, 109, 27);
		}
		return lblEmail;
	}

	public JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(141, 102, 320, 20);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}

	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(22, 205, 524, 137);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}

	public JButton getBtnRefrescar() {
		if (btnRefrescar == null) {
			btnRefrescar = new JButton("Actualizar");
			btnRefrescar.setBounds(386, 171, 122, 23);
		}
		return btnRefrescar;
	}

	public JButton getBtnInsertar() {
		if (btnInsertar == null) {
			btnInsertar = new JButton("Insertar");
			btnInsertar.setBounds(287, 171, 89, 23);
		}
		return btnInsertar;
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableIGrupal());
		}
		return scrollPane;
	}

	public JTable getTableIGrupal() {
		if (tableIGrupal == null) {
			tableIGrupal = new JTable();
		}
		return tableIGrupal;
	}

	public JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setBounds(457, 353, 89, 23);
		}
		return btnSalir;
	}
}
