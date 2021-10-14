package giis.demo.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import giis.demo.tkrun.entities.AtletaEntity;
import giis.demo.tkrun.entities.CarreraEntity;
import giis.demo.tkrun.entities.CategoriaEntity;
import giis.demo.tkrun.entities.InscripcionEntity;
import giis.demo.tkrun.model.AtletaModel;
import giis.demo.tkrun.model.CarreraModel;
import giis.demo.tkrun.model.CategoriaModel;
import giis.demo.tkrun.model.InscripcionModel;
import giis.demo.tkrun.view.InscripcionView;

public class InscripcionController {

	InscripcionModel inscripcionModel = new InscripcionModel();
	AtletaModel atletaModel = new AtletaModel();
	CarreraModel carreraModel = new CarreraModel();
	CategoriaModel categoriaModel = new CategoriaModel();

	InscripcionView view;// vista para añadirle el actionListener

	public void init(String nombreCarrera, int idCarrera) {
		view = new InscripcionView(nombreCarrera);
		view.getBtnOk().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInscripcion(view.getTextEmail().getText(), idCarrera);
			}
		});
		view.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});
		view.setModal(true);
		view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		view.setVisible(true);
	}

	public void addInscripcion(String email, int idCarrera) {
		System.out.println("add inscripcion");
		// Comprobar que no existe otra inscripcion
		InscripcionEntity ie = inscripcionModel.findInscripcion(email, idCarrera);
		if (!(ie == null)) {
			JOptionPane.showMessageDialog(view, "La inscripcion ya existe", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Obtener atleta
		AtletaEntity ae = atletaModel.findAtleta(email);
		if (ae == null) {
			JOptionPane.showMessageDialog(view, "El atleta no existe", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Obtener carrera
		CarreraEntity ce = carreraModel.findCarrera(idCarrera);
		if (ce == null) {
			JOptionPane.showMessageDialog(view, "La carrera no existe", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Comprobar que el plazo de inscripcion esta abierto
		Date now = new Date(System.currentTimeMillis());
		if (Date.valueOf(ce.getFinInscripcion()).compareTo(now) < 0) {
			JOptionPane.showMessageDialog(view, "La inscripcion ya no esta abierta", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Comprobar que hay plazas libres
		List<InscripcionEntity> inscripciones = inscripcionModel.findInscripciones(idCarrera);
		if (inscripciones.size() >= ce.getPlazas()) {
			JOptionPane.showMessageDialog(view, "No quedan plazas libres", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Calcular categoria
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(now);
		Calendar calBirth = Calendar.getInstance();
		calBirth.setTime(Date.valueOf(ae.getFechaNacimiento()));
		int edad = calNow.get(Calendar.YEAR) - calBirth.get(Calendar.YEAR);
		
		System.out.println(edad + " " + idCarrera + " " + ae.getSexo());
		
		CategoriaEntity categoria = categoriaModel.findCategoria(idCarrera, edad, ae.getSexo());
		
		if (categoria == null) {
			JOptionPane.showMessageDialog(view, "No hay categoria para esa carrera", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Calcular dorsal
		int dorsal = inscripciones.size() + 1;

		// Guardar inscripcion
		InscripcionEntity inscripcion = new InscripcionEntity();
		inscripcion.setDorsal(dorsal);
		inscripcion.setEstado("PREINSCRIPCION");
		inscripcion.setIdCategoria(categoria.getId());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);
		inscripcionModel.addInscripcion(inscripcion);

		// Generar recibo

		JOptionPane.showMessageDialog(view, "Inscripcion realizada", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

		view.dispose();
	}

}
