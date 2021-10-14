package giis.demo.tkrun.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InscripcionView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnOk;
	private JButton btnCancelar;
	private JLabel lblNombreCarrera;
	private JLabel lblEmail;
	private JTextField textEmail;

	private String nombreCarrera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscripcionView frame = new InscripcionView("Test");
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
	public InscripcionView(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;

		setTitle("Inscripción");
		setResizable(false);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		contentPane.add(getBtnOk());
		contentPane.add(getBtnCancelar());
		contentPane.add(getLblNombreCarrera());
		contentPane.add(getLblEmail());
		contentPane.add(getTextEmail());
	}

	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Ok");
			btnOk.setBounds(272, 218, 73, 32);
		}
		return btnOk;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(349, 218, 75, 33);
		}
		return btnCancelar;
	}

	public JLabel getLblNombreCarrera() {
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel("nombre carrera");
			lblNombreCarrera.setText(nombreCarrera);
			lblNombreCarrera.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNombreCarrera.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombreCarrera.setBounds(127, 11, 174, 39);
		}
		return lblNombreCarrera;
	}

	public JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("email:");
			lblEmail.setLabelFor(getTextEmail());
			lblEmail.setBounds(146, 131, 186, 14);
		}
		return lblEmail;
	}

	public JTextField getTextEmail() {
		if (textEmail == null) {
			textEmail = new JTextField();
			textEmail.setBounds(202, 128, 130, 20);
			textEmail.setColumns(10);
		}
		return textEmail;
	}

}
