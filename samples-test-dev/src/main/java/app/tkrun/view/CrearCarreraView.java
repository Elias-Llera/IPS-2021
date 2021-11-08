package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CrearCarreraView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombreCarrera;
	private JLabel lblDistancia;
	private JLabel lblTipo;
	private JLabel lblFechaCelebracion;
	private JLabel lblNumeroPlazas;
	private JTextField textFieldNombreCarrera;
	private JTextField textFieldDistancia;
	private JTextField textFieldTipoCarrera;
	private JTextField textFieldFechaCelebracion;
	private JTextField textFieldNumeroPlazas;
	private JButton btnPlazosInscripcion;
	private JButton btnCategorias;
	private JButton btnCancelar;
	private JButton btnCrearCarrera;
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;

	/**
	 * Create the dialog.
	 */
	public CrearCarreraView() {
		setTitle("Craer carrera");
		setBounds(100, 100, 564, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblNombreCarrera());
		contentPanel.add(getLblDistancia());
		contentPanel.add(getLblTipo());
		contentPanel.add(getLblFechaCelebracion());
		contentPanel.add(getLblNumeroPlazas());
		contentPanel.add(getTextFieldNombreCarrera());
		contentPanel.add(getTextFieldDistancia());
		contentPanel.add(getTextFieldTipoCarrera());
		contentPanel.add(getTextFieldFechaCelebracion());
		contentPanel.add(getTextFieldNumeroPlazas());
		contentPanel.add(getBtnPlazosInscripcion());
		contentPanel.add(getBtnCategorias());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getBtnCrearCarrera());
		contentPanel.add(getLblDescripcion());
		contentPanel.add(getTextFieldDescripcion());
	}

	public JLabel getLblNombreCarrera() {
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel("Nombre de la carrera");
			lblNombreCarrera.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNombreCarrera.setBounds(10, 36, 139, 23);
		}
		return lblNombreCarrera;
	}

	public JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia (km)");
			lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDistancia.setBounds(10, 70, 121, 23);
		}
		return lblDistancia;
	}

	public JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo de la carrera");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblTipo.setBounds(10, 104, 121, 23);
		}
		return lblTipo;
	}

	public JLabel getLblFechaCelebracion() {
		if (lblFechaCelebracion == null) {
			lblFechaCelebracion = new JLabel("Fecha de celebracion");
			lblFechaCelebracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblFechaCelebracion.setBounds(10, 138, 139, 23);
		}
		return lblFechaCelebracion;
	}

	public JLabel getLblNumeroPlazas() {
		if (lblNumeroPlazas == null) {
			lblNumeroPlazas = new JLabel("N\u00FAmero de plazas");
			lblNumeroPlazas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNumeroPlazas.setBounds(10, 172, 121, 23);
		}
		return lblNumeroPlazas;
	}

	public JTextField getTextFieldNombreCarrera() {
		if (textFieldNombreCarrera == null) {
			textFieldNombreCarrera = new JTextField();
			textFieldNombreCarrera.setBounds(159, 39, 243, 20);
			textFieldNombreCarrera.setColumns(10);
		}
		return textFieldNombreCarrera;
	}

	public JTextField getTextFieldDistancia() {
		if (textFieldDistancia == null) {
			textFieldDistancia = new JTextField();
			textFieldDistancia.setBounds(159, 73, 243, 20);
			textFieldDistancia.setColumns(10);
		}
		return textFieldDistancia;
	}

	public JTextField getTextFieldTipoCarrera() {
		if (textFieldTipoCarrera == null) {
			textFieldTipoCarrera = new JTextField();
			textFieldTipoCarrera.setBounds(159, 107, 243, 20);
			textFieldTipoCarrera.setColumns(10);
		}
		return textFieldTipoCarrera;
	}

	public JTextField getTextFieldFechaCelebracion() {
		if (textFieldFechaCelebracion == null) {
			textFieldFechaCelebracion = new JTextField();
			textFieldFechaCelebracion.setBounds(159, 141, 243, 20);
			textFieldFechaCelebracion.setColumns(10);
		}
		return textFieldFechaCelebracion;
	}

	public JTextField getTextFieldNumeroPlazas() {
		if (textFieldNumeroPlazas == null) {
			textFieldNumeroPlazas = new JTextField();
			textFieldNumeroPlazas.setBounds(159, 175, 243, 20);
			textFieldNumeroPlazas.setColumns(10);
		}
		return textFieldNumeroPlazas;
	}

	public JButton getBtnPlazosInscripcion() {
		if (btnPlazosInscripcion == null) {
			btnPlazosInscripcion = new JButton("Plazos inscripcion");
			btnPlazosInscripcion.setEnabled(false);
			btnPlazosInscripcion.setBounds(203, 263, 150, 23);
		}
		return btnPlazosInscripcion;
	}

	public JButton getBtnCategorias() {
		if (btnCategorias == null) {
			btnCategorias = new JButton("Configurar categorias");
			btnCategorias.setEnabled(false);
			btnCategorias.setBounds(363, 263, 175, 23);
		}
		return btnCategorias;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Salir");
			btnCancelar.setBounds(449, 297, 89, 23);
		}
		return btnCancelar;
	}
	public JButton getBtnCrearCarrera() {
		if (btnCrearCarrera == null) {
			btnCrearCarrera = new JButton("Crear");
			btnCrearCarrera.setBounds(102, 263, 89, 23);
		}
		return btnCrearCarrera;
	}
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripci\u00F3n");
			lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDescripcion.setBounds(10, 209, 105, 23);
		}
		return lblDescripcion;
	}
	public JTextField getTextFieldDescripcion() {
		if (textFieldDescripcion == null) {
			textFieldDescripcion = new JTextField();
			textFieldDescripcion.setBounds(159, 212, 243, 20);
			textFieldDescripcion.setColumns(10);
		}
		return textFieldDescripcion;
	}
}
