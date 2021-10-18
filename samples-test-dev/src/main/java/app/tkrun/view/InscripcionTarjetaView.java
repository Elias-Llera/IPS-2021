package app.tkrun.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InscripcionTarjetaView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTarjeta;
	private JTextField tfFechaCaducidad;
	private JTextField tfCvc;

	public JButton btnListo;

	/**
	 * Create the frame.
	 */
	public InscripcionTarjetaView() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPagoTarjeta = new JLabel("Pago con tarjeta");
		lblPagoTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPagoTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPagoTarjeta.setBounds(141, 29, 234, 42);
		contentPane.add(lblPagoTarjeta);

		JLabel lblNumeroTarjeta = new JLabel("Numero de tarjeta:");
		lblNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumeroTarjeta.setBounds(26, 125, 163, 27);
		contentPane.add(lblNumeroTarjeta);

		JLabel lblFechaCaducidad = new JLabel("Fecha de caducidad:");
		lblFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFechaCaducidad.setBounds(26, 163, 165, 27);
		contentPane.add(lblFechaCaducidad);

		JLabel lblCvc = new JLabel("CVC:");
		lblCvc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCvc.setBounds(26, 204, 163, 27);
		contentPane.add(lblCvc);

		tfTarjeta = new JTextField();
		tfTarjeta.setBounds(199, 131, 276, 20);
		contentPane.add(tfTarjeta);
		tfTarjeta.setColumns(10);

		tfFechaCaducidad = new JTextField();
		tfFechaCaducidad.setBounds(199, 169, 276, 20);
		contentPane.add(tfFechaCaducidad);
		tfFechaCaducidad.setColumns(10);

		tfCvc = new JTextField();
		tfCvc.setBounds(199, 210, 276, 20);
		contentPane.add(tfCvc);
		tfCvc.setColumns(10);
		
		contentPane.add(getBtnListo());

		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(313, 281, 89, 23);
		contentPane.add(btnAtras);
	}

	public void mostrarMensaje() {
		JOptionPane.showMessageDialog(this, "Tarjeta de credito aceptada", "SUCCESS",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean comprobarCampos() {
		if (tfFechaCaducidad.getText().isBlank() || tfTarjeta.getText().isBlank() || tfCvc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ninguno de los campos puede estar vacio", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	public JButton getBtnListo() {
		if (btnListo == null) {
			btnListo = new JButton("Listo");
			btnListo.setBounds(424, 281, 89, 23);
		}
		return btnListo;
	}

}
