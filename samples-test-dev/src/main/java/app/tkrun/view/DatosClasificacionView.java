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

public class DatosClasificacionView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIdCarrera;
	private JTextField tfGenero;

	public JButton btnListo;

	/**
	 * Create the frame.
	 */
	public DatosClasificacionView() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblClasificacion = new JLabel("Clasificacion");
		lblClasificacion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasificacion.setBounds(141, 29, 234, 42);
		contentPane.add(lblClasificacion);

		JLabel lblIdCarrera = new JLabel("IdCarrera:");
		lblIdCarrera.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdCarrera.setBounds(26, 125, 163, 27);
		contentPane.add(lblIdCarrera);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGenero.setBounds(26, 163, 165, 27);
		contentPane.add(lblGenero);

		tfIdCarrera = new JTextField();
		tfIdCarrera.setBounds(199, 131, 276, 20);
		contentPane.add(tfIdCarrera);
		tfIdCarrera.setColumns(10);

		tfGenero = new JTextField();
		tfGenero.setBounds(199, 169, 276, 20);
		contentPane.add(tfGenero);
		tfGenero.setColumns(10);
		
		contentPane.add(getBtnListo());

		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(314, 220, 89, 23);
		contentPane.add(btnAtras);
	}

	

	public boolean comprobarCampos() {
		if (tfGenero.getText().isBlank() || tfIdCarrera.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Ninguno de los campos puede estar vacio", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(!(tfGenero.getText().toUpperCase().equals("MASCULINO")||tfGenero.getText().toUpperCase().equals("FEMENINO"))) {
			JOptionPane.showMessageDialog(this, "El genero no es valido", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}

	public JButton getBtnListo() {
		if (btnListo == null) {
			btnListo = new JButton("Listo");
			btnListo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarCampos();
					mostrarVentanaClasificaciones();
				}
			});
			btnListo.setBounds(413, 220, 89, 23);
		}
		return btnListo;
	}



	protected void mostrarVentanaClasificaciones() {
		ClasificacionesView vp = new ClasificacionesView(Integer.parseInt(tfIdCarrera.getText()), tfGenero.getText());

		vp.setLocationRelativeTo(null);
		vp.setVisible(true);
		
	}

}
