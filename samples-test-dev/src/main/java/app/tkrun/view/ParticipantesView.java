package app.tkrun.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.InscripcionModel;

public class ParticipantesView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCarrera;
	private JButton btnVolver;
	private int idCarrera;
	private JTextArea tAParticipantes;

	/**
	 * Create the frame.
	 */
	public ParticipantesView(int id) {
		setTitle("Participantes");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 383);
		this.idCarrera = id;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCarrera());
		contentPane.add(getBtnVolver());
		contentPane.add(getTAParticipantes());
	}

	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("Participantes de la carrera");
			lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblCarrera.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarrera.setBounds(59, 11, 389, 37);
		}
		return lblCarrera;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolver.setBounds(406, 315, 89, 23);
		}
		return btnVolver;
	}

	private JTextArea getTAParticipantes() {
		if (tAParticipantes == null) {
			tAParticipantes = new JTextArea();
			tAParticipantes.setEditable(false);
			tAParticipantes.setBounds(10, 54, 496, 254);
			tAParticipantes.setText(getParticipantes());
			;
		}
		return tAParticipantes;
	}

	private String getParticipantes() {
		String cadena = "";
		int contador = 0;

		AtletaModel am = new AtletaModel();
		InscripcionModel im;
		CategoriaModel cm;

		List<AtletaEntity> atletas = am.findAtletasCarrera(idCarrera);

		if (atletas == null || atletas.size() < 1) {
			return "No hay ningun corredor registrado aun";
		}
		
		for (AtletaEntity a : atletas) {
			im = new InscripcionModel();
			InscripcionEntity inscripcion = im.findInscripcion(a.getEmailAtleta(), idCarrera);
			cm = new CategoriaModel();
			CategoriaEntity categoria = cm.findCategoriaCarrera(idCarrera);

			cadena += contador + "- Email: " + a.getEmailAtleta() + " Nombre: " + a.getNombre() + "\n\tCategoria: "
					+ categoria.getIdCarrera() + " Estado de inscripcion: " + inscripcion.getEstado() + "\n\n";
			contador++;
		}

		return cadena;

	}
}
