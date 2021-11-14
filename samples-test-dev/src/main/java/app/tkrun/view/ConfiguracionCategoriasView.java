package app.tkrun.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class ConfiguracionCategoriasView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JButton btnConfirmar;
	private JPanel panelCategorias;
	private JButton btnAdd;
	private JLabel lblCategorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfiguracionCategoriasView dialog = new ConfiguracionCategoriasView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfiguracionCategoriasView() {
		setResizable(false);
		setModal(true);
		setTitle("Creacion de categorias");
		setBounds(100, 100, 578, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnConfirmar = getBtnConfirmar();
		
		panelCategorias = getPanelCategorias();
		
		JScrollPane scrollPane = new JScrollPane(panelCategorias);
		panelCategorias.setLayout(new GridLayout(0, 1, 0, 0));
		scrollPane.setBounds(10, 35, 542, 224);
		contentPanel.add(scrollPane);
		
		btnAdd = getBtnAdd();
		contentPanel.add(getLblCategorias());

	}

	public JButton getBtnConfirmar() {
		if(btnConfirmar== null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(432, 315, 120, 41);
			contentPanel.add(btnConfirmar);
		}
		return btnConfirmar;
	}
	
	public JPanel getPanelCategorias() {
		if(panelCategorias == null) {
			panelCategorias = new JPanel();
			panelCategorias.setBounds(0, 0, 10, 10);
		}
		return panelCategorias;
	}
	
	public JButton getBtnAdd() {
		if(btnAdd== null) {
			btnAdd = new JButton("+");
			btnAdd.setBounds(43, 273, 80, 35);
			contentPanel.add(btnAdd);
		}
		return btnAdd;
	}
	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("Categorias:");
			lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblCategorias.setBounds(10, 11, 80, 19);
		}
		return lblCategorias;
	}
}
