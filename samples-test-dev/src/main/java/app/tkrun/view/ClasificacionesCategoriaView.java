package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.ClasificacionParaTabla;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.TiempoEntity;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.model.TiempoModel;
import app.util.SwingUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClasificacionesCategoriaView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCategorias;
	private JScrollPane scrollPaneCategorias;
	private JLabel lblClasificacion;
	private JScrollPane scrollPane;
	private JTable tableClasificacion;
	private JTable tableCategorias;
	CategoriaModel modelCategoria = new CategoriaModel();
	TiempoModel tiemposModel = new TiempoModel();
	InscripcionModel inscripcionesModel = new InscripcionModel(); 
	private int id;
	private List<CategoriaEntity> listaCategorias;
	private List<TiempoEntity> clasificacion = new ArrayList<TiempoEntity>();
	private List<CategoriaEntity> categoriasSelec = new ArrayList<CategoriaEntity>();
	private JButton btnVerClasificacion;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			ClasificacionesCategoriaView dialog = new ClasificacionesCategoriaView(102);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ClasificacionesCategoriaView(int id) {
		this.id = id;
		setBounds(100, 100, 654, 579);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblCategorias());
		contentPanel.add(getScrollPaneCategorias());
		contentPanel.add(getLblClasificacion());
		contentPanel.add(getScrollPane());
		contentPanel.add(getBtnVerClasificacion());
		rellenaCategorias();
		this.listaCategorias = modelCategoria.findCategoriasCarrera(id);
		generarClasificacionGeneral();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void generarClasificacionGeneral() {
		for(CategoriaEntity categoria: listaCategorias) {
			List<InscripcionEntity> inscripciones = inscripcionesModel.findByCarreraCategoria(this.id, categoria.getIdCategoria());
			
			for(InscripcionEntity inscripcion: inscripciones) {
				List<TiempoEntity> tiempos = tiemposModel.findClasificacionForCarreraCategoria(id, inscripcion.getEmailAtleta());
				
				for(TiempoEntity tiempo: tiempos) {
					clasificacion.add(tiempo);
				}
			}
		}
		
		TableModel tmodel = SwingUtil.getTableModelFromPojos(clasificacion,
				new String[] { "emailAtleta", "tiempo"});

		getTableClasificacion().setModel(tmodel);

		SwingUtil.autoAdjustColumns(getTableCategorias());
	}

	private void rellenaCategorias() {
		List<CategoriaEntity> categorias = modelCategoria.findCategoriasCarrera(id);
		
		TableModel tmodel = SwingUtil.getTableModelFromPojos(categorias,
				new String[] { "nombre"});

		getTableCategorias().setModel(tmodel);

		SwingUtil.autoAdjustColumns(getTableCategorias());

		
	}

	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("Categorias:");
			lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblCategorias.setBounds(10, 11, 114, 25);
		}
		return lblCategorias;
	}
	private JScrollPane getScrollPaneCategorias() {
		if (scrollPaneCategorias == null) {
			scrollPaneCategorias = new JScrollPane();
			scrollPaneCategorias.setBounds(20, 47, 315, 150);
			scrollPaneCategorias.setViewportView(getTableCategorias());
		}
		return scrollPaneCategorias;
	}
	private JLabel getLblClasificacion() {
		if (lblClasificacion == null) {
			lblClasificacion = new JLabel("Clasificacion");
			lblClasificacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblClasificacion.setBounds(10, 208, 114, 25);
		}
		return lblClasificacion;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 244, 594, 242);
			scrollPane.setViewportView(getTableClasificacion());
		}
		return scrollPane;
	}
	private JTable getTableClasificacion() {
		if (tableClasificacion == null) {
			tableClasificacion = new JTable();
			tableClasificacion.setEnabled(false);
			tableCategorias.setDefaultEditor(Object.class, null);
			tableClasificacion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		return tableClasificacion;
	}
	private JTable getTableCategorias() {
		if (tableCategorias == null) {
			tableCategorias = new JTable();
			tableCategorias.setDefaultEditor(Object.class, null);
			tableCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		return tableCategorias;
	}
	private JButton getBtnVerClasificacion() {
		if (btnVerClasificacion == null) {
			btnVerClasificacion = new JButton("Ver clasificacion");
			btnVerClasificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarTablaClasi();
				}
			});
			btnVerClasificacion.setBounds(437, 106, 114, 23);
		}
		return btnVerClasificacion;
	}
	
	protected void actualizarTablaClasi() {
		List<TiempoEntity> clasificacionFiltrada = new ArrayList<TiempoEntity>();
		int[] numeros = getTableCategorias().getSelectedRows();
		categoriasSelec = new ArrayList<CategoriaEntity>();
		for(Integer numero: numeros) {
			categoriasSelec.add(listaCategorias.get(numero));
		}
		
		for(CategoriaEntity categoria: categoriasSelec) {
			List<InscripcionEntity> inscripciones = inscripcionesModel.findByCarreraCategoria(this.id, categoria.getIdCategoria());
			
			for(InscripcionEntity inscripcion: inscripciones) {
				List<TiempoEntity> tiempos = tiemposModel.findClasificacionForCarreraCategoria(id, inscripcion.getEmailAtleta());
				
				for(TiempoEntity tiempo: tiempos) {
					
						clasificacionFiltrada.add(tiempo);	
					
					
				}
			}
		}
		
		tableClasificacion = new JTable();
		tableClasificacion.setEnabled(false);
		tableClasificacion.setDefaultEditor(Object.class, null);
		tableClasificacion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		getScrollPane().setViewportView(tableClasificacion);
		
		java.util.Collections.sort(clasificacionFiltrada);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(clasificacionFiltrada,
				new String[] { "emailAtleta", "tiempo"});

		getTableClasificacion().setModel(tmodel);

		SwingUtil.autoAdjustColumns(getTableCategorias());
		
		
	}
}
