package app.tkrun.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

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
	private ButtonGroup buttonGroup;

	private String nombreCarrera;
	private JButton btnInscripcionGrupo;

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

		setTitle("Inscripcion");
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
		
		JLabel lblMetodoDePago = new JLabel("Metodo de pago:");
		lblMetodoDePago.setBounds(127, 138, 236, 14);
		contentPane.add(lblMetodoDePago);
		
		JRadioButton rdbtnTarjeta = new JRadioButton("Tarjeta");
		rdbtnTarjeta.setBounds(127, 159, 73, 23);
		contentPane.add(rdbtnTarjeta);
		
		JRadioButton rdbtnTransferencia = new JRadioButton("Transferencia");
		rdbtnTransferencia.setBounds(202, 159, 109, 23);
		contentPane.add(rdbtnTransferencia);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnTarjeta);
		buttonGroup.add(rdbtnTransferencia);
		contentPane.add(getBtnInscripcionGrupo());
		
	}
	
	 public String getSelectedButtonText() {
	        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();
	            if (button.isSelected()) {
	                return button.getText();
	            }
	        }
			return null;
	 }
	 
	public JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Ok");
			btnOk.setBounds(244, 213, 90, 47);
		}
		return btnOk;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(344, 213, 90, 47);
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
			lblEmail.setBounds(127, 79, 46, 14);
		}
		return lblEmail;
	}

	public JTextField getTextEmail() {
		if (textEmail == null) {
			textEmail = new JTextField();
			textEmail.setBounds(162, 76, 174, 20);
			textEmail.setColumns(10);
		}
		return textEmail;
	}
	public JButton getBtnInscripcionGrupo() {
		if (btnInscripcionGrupo == null) {
			btnInscripcionGrupo = new JButton("Incripcion Grupal");
			btnInscripcionGrupo.setBounds(87, 213, 147, 47);
		}
		return btnInscripcionGrupo;
	}
}
