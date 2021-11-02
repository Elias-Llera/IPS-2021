package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InscripcionNoAtletaView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnOk;
	private JButton btnCancelar;
	
	private String nombreCarrera;
	private String correo;
	private JLabel lblNombreCarrera;
	private JLabel lblEmail;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblFechaNacimiento;
	private JLabel lblSexo;
	private JTextField textFieldEmail;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldFechaNacimiento;
	private JTextField textFieldSexo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InscripcionNoAtletaView dialog = new InscripcionNoAtletaView("Test","Test");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InscripcionNoAtletaView(String nombreCarrera, String correo) {
		this.nombreCarrera=nombreCarrera;
		this.correo=correo;
		setTitle("Inscripci\u00F3n para ser atleta");
		setBounds(100, 100, 552, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnOk());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getLblNombreCarrera());
		contentPanel.add(getLblEmail());
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblApellido());
		contentPanel.add(getLblFechaNacimiento());
		contentPanel.add(getLblSexo());
		contentPanel.add(getTextFieldEmail());
		contentPanel.add(getTextFieldNombre());
		contentPanel.add(getTextFieldApellido());
		contentPanel.add(getTextFieldFechaNacimiento());
		contentPanel.add(getTextFieldSexo());
	}

	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Confirmar");

			btnOk.setBounds(329, 284, 89, 23);
		}
		return btnOk;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(428, 284, 89, 23);
		}
		return btnCancelar;
	}
	private JLabel getLblNombreCarrera() {
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel("New label");
			lblNombreCarrera.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNombreCarrera.setText(nombreCarrera);
			lblNombreCarrera.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombreCarrera.setBounds(203, 11, 120, 47);
		}
		return lblNombreCarrera;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEmail.setBounds(20, 83, 46, 23);
		}
		return lblEmail;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNombre.setBounds(20, 117, 69, 31);
		}
		return lblNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido");
			lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblApellido.setBounds(20, 161, 60, 31);
		}
		return lblApellido;
	}
	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha nacimiento");
			lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFechaNacimiento.setBounds(20, 203, 120, 23);
		}
		return lblFechaNacimiento;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSexo.setBounds(20, 237, 60, 31);
		}
		return lblSexo;
	}
	public JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setText(correo);
			textFieldEmail.setBounds(87, 86, 331, 20);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	public JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(86, 124, 332, 20);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	public JTextField getTextFieldApellido() {
		if (textFieldApellido == null) {
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(90, 168, 328, 20);
			textFieldApellido.setColumns(10);
		}
		return textFieldApellido;
	}
	public JTextField getTextFieldFechaNacimiento() {
		if (textFieldFechaNacimiento == null) {
			textFieldFechaNacimiento = new JTextField();
			textFieldFechaNacimiento.setBounds(150, 206, 268, 20);
			textFieldFechaNacimiento.setColumns(10);
		}
		return textFieldFechaNacimiento;
	}
	public JTextField getTextFieldSexo() {
		if (textFieldSexo == null) {
			textFieldSexo = new JTextField();
			textFieldSexo.setBounds(90, 244, 328, 20);
			textFieldSexo.setColumns(10);
		}
		return textFieldSexo;
	}
}
