package app.tkrun.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.model.InscripcionModel;
import app.util.SwingUtil;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ShowInscriptionsView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String email;
	private InscripcionModel im= new InscripcionModel();
	private JTable tableInscriptions;
	private JScrollPane tablePanelInscripciones;
	private List<InscripcionEntity> inscripciones;
	
	/**
	 * Create the frame.
	 */
	public ShowInscriptionsView(String email) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.email = email;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		tableInscriptions = new JTable();
		tableInscriptions.setName("tabParticipantes");
		tableInscriptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableInscriptions.setDefaultEditor(Object.class, null); //readonly
		tablePanelInscripciones = new JScrollPane(tableInscriptions);
		contentPane.add(tablePanelInscripciones, "cell 0 0,grow");
		
		getListaCarreras();
		
	}
	
	public void getListaCarreras() {
		inscripciones = im.findInscripcionesParticipante(email);
		for(InscripcionEntity inscripcion: inscripciones) {
//			int plazasOcupadas=atletaModel.findAtletasParticipantesEnCarrera(carrera.getIdCarrera());
//			if(plazasOcupadas > 0) {
//				carrera.setPlazas(carrera.getPlazas()-plazasOcupadas);
//			}
			
		}
		TableModel tmodel = SwingUtil.getTableModelFromPojos(inscripciones, new String[] { "emailAtleta", "idCarrera", "estado", "dorsal",
				"idCategoria"});
		tableInscriptions.setModel(tmodel);
		SwingUtil.autoAdjustColumns(tableInscriptions);

	
	}

}
