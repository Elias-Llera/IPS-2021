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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDni;
	private JTextField tfCorreo;

	public JButton btnListo;

	/**
	 * Create the frame.
	 */
	public DatosView() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDatosCorredor = new JLabel("Datos del corredor");
		lblDatosCorredor.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDatosCorredor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosCorredor.setBounds(122, 30, 281, 42);
		contentPane.add(lblDatosCorredor);

		JLabel lblDni = new JLabel("Dni:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDni.setBounds(26, 125, 163, 27);
		contentPane.add(lblDni);

		JLabel lblCorreo = new JLabel("Correo electronico:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCorreo.setBounds(26, 163, 165, 27);
		contentPane.add(lblCorreo);

		tfDni = new JTextField();
		tfDni.setBounds(199, 131, 276, 20);
		contentPane.add(tfDni);
		tfDni.setColumns(10);

		tfCorreo = new JTextField();
		tfCorreo.setBounds(199, 169, 276, 20);
		contentPane.add(tfCorreo);
		tfCorreo.setColumns(10);
		
		contentPane.add(getBtnListo());

		JButton btnAtras = new JButton("Atras");
		
		btnAtras.setBounds(314, 233, 89, 23);
		contentPane.add(btnAtras);
	}

	public void mostrarMensaje() {
		JOptionPane.showMessageDialog(this, "Tarjeta de credito aceptada", "SUCCESS",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean comprobarCampos() {
		if (tfCorreo.getText().isBlank() || tfDni.getText().isBlank() ) {
			JOptionPane.showMessageDialog(this, "Ninguno de los campos puede estar vacio", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	public JButton getBtnListo() {
		if (btnListo == null) {
			btnListo = new JButton("Listo");
			btnListo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarCampos()) {
						mostrarVentanaInscripciones();
					}
				}
			});
			btnListo.setBounds(413, 233, 89, 23);
		}
		return btnListo;
	}

	protected void mostrarVentanaInscripciones() {
		ShowInscriptionsView vp = new ShowInscriptionsView(tfCorreo.getText());
		
		vp.setLocationRelativeTo(null);
		vp.setVisible(true);
		
	}

}
