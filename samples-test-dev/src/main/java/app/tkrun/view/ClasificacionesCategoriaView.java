package app.tkrun.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.TiempoEntity;
import app.tkrun.entities.TiempoParaTabla;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.model.TiempoModel;
import app.util.SwingUtil;

public class ClasificacionesCategoriaView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCategorias;
	private JScrollPane scrollPaneCategorias;
	private JLabel lblClasificacion;
	private JScrollPane scrollPane;
	private JTable tableClasificacion;
	private JTable tableCategorias;
	CategoriaModel modelCategoria = new CategoriaModel();
	CarreraModel modelCarrera = new CarreraModel();
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
		CarreraEntity actual = modelCarrera.findCarrera(id);
		setTitle("Clasificaciones para carrera: " + actual.getNombre());
		this.id = id;
		this.setModal(true);
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
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		if(!generarClasificacionGeneral()) {
			dispose();
		}
		
	}
	private boolean generarClasificacionGeneral() {
		List<TiempoParaTabla> tiemposTabla = new ArrayList<TiempoParaTabla>();
		int contador = 1;
		for(CategoriaEntity categoria: listaCategorias) {
			List<InscripcionEntity> inscripciones = inscripcionesModel.findByCarreraCategoria(this.id, categoria.getIdCategoria());
			if(inscripciones == null) {
				break;
			}
			for(InscripcionEntity inscripcion: inscripciones) {
				List<TiempoEntity> tiempos = tiemposModel.findClasificacionForCarreraCategoria(id, inscripcion.getEmailAtleta());
				
				for(TiempoEntity tiempo: tiempos) {
					clasificacion.add(tiempo);
					TiempoParaTabla t= new TiempoParaTabla();
					
					
					t.setEmailAtleta(tiempo.getEmailAtleta());
					t.setTiempo(tiempo.getTiempo());
					t.setNombreCategoria(categoria.getNombre());
					t.setDorsal(inscripcion.getDorsal());
					
					tiemposTabla.add(t);
				}
			}
		}
		
		if(clasificacion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Aun no se ha procesado la clasificacion para ninguna categoria");
			this.dispose();
			return false;
			
		}
		java.util.Collections.sort(tiemposTabla);
		
		for(TiempoParaTabla tiempo : tiemposTabla) {
			tiempo.setPosicion(contador);
			contador++;
		}
		
		
//		for(TiempoEntity tiempo: clasificacion) {
//			TiempoParaTabla t= new TiempoParaTabla();
//			
//			t.setPosicion(contador);
//			t.setEmailAtleta(tiempo.getEmailAtleta());
//			t.setTiempo(tiempo.getTiempo());
//			contador++;
//			tiemposTabla.add(t);
//		}
//		
//		
		TableModel tmodel = SwingUtil.getTableModelFromPojos(tiemposTabla,
				new String[] { "emailAtleta","dorsal", "nombreCategoria", "tiempo", "posicion"});

		getTableClasificacion().setModel(tmodel);

		SwingUtil.autoAdjustColumns(getTableCategorias());
		
		return true;
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
			btnVerClasificacion.setBounds(405, 106, 146, 23);
		}
		return btnVerClasificacion;
	}
	
	protected void actualizarTablaClasi() {
		List<TiempoEntity> clasificacionFiltrada = new ArrayList<TiempoEntity>();
		int contador = 1;
		List<TiempoParaTabla> tiemposTabla = new ArrayList<TiempoParaTabla>();
		
		int[] numeros = getTableCategorias().getSelectedRows();
		categoriasSelec = new ArrayList<CategoriaEntity>();
		for(Integer numero: numeros) {
			categoriasSelec.add(listaCategorias.get(numero));
		}
		
		if(categoriasSelec.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor seleccione una o varias categorias");
			return ;
		}
		
		for(CategoriaEntity categoria: categoriasSelec) {
			List<InscripcionEntity> inscripciones = inscripcionesModel.findByCarreraCategoria(this.id, categoria.getIdCategoria());
			
			for(InscripcionEntity inscripcion: inscripciones) {
				List<TiempoEntity> tiempos = tiemposModel.findClasificacionForCarreraCategoria(id, inscripcion.getEmailAtleta());
				if(tiempos.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Aun no se ha procesado la clasificacion para la categoria: "+ categoria.getNombre());
					break;
				}
				for(TiempoEntity tiempo: tiempos) {
					
						clasificacionFiltrada.add(tiempo);	
						TiempoParaTabla t= new TiempoParaTabla();
						
						
						t.setEmailAtleta(tiempo.getEmailAtleta());
						t.setTiempo(tiempo.getTiempo());
						t.setNombreCategoria(categoria.getNombre());
						t.setDorsal(inscripcion.getDorsal());
						
						tiemposTabla.add(t);

				}
			}
		}
		
		tableClasificacion = new JTable();
		tableClasificacion.setEnabled(false);
		tableClasificacion.setDefaultEditor(Object.class, null);
		tableClasificacion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		getScrollPane().setViewportView(tableClasificacion);
		
		java.util.Collections.sort(tiemposTabla);
		

		for(TiempoParaTabla tiempo: tiemposTabla) {
			
			
			tiempo.setPosicion(contador);
			contador++;
			
		}
		
		TableModel tmodel = SwingUtil.getTableModelFromPojos(tiemposTabla,
				new String[] { "emailAtleta","dorsal", "nombreCategoria", "tiempo", "posicion"});

		getTableClasificacion().setModel(tmodel);

		SwingUtil.autoAdjustColumns(getTableCategorias());
		
		
	}
}
