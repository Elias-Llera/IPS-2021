package giis.demo.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	InscripcionView view;// vista para a√±adirle el actionListener

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
		inscripcion.setIdCategoria(categoria.getId());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);

		String selectedButtonText = view.getSelectedButtonText();

		if (selectedButtonText == null) {
			JOptionPane.showMessageDialog(view, "Seleccione un metodo de pago", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (selectedButtonText.equals("Tarjeta")) {
			JOptionPane.showMessageDialog(view, "NOT YET IMPLEMENTED", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (selectedButtonText.equals("Transferencia")) {
			inscripcion.setEstado("Pendiente de Pago");
			String text = reciboTransferencia(inscripcion, ae, ce, categoria);
			text.concat("Copia del recibo generada en la carpeta recibos");
			JOptionPane.showMessageDialog(view, text, "RECIBO", JOptionPane.INFORMATION_MESSAGE);
		}

		inscripcionModel.addInscripcion(inscripcion);

		JOptionPane.showMessageDialog(view, "Inscripcion realizada", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

		view.dispose();
	}

	private String reciboTransferencia(InscripcionEntity inscripcion, AtletaEntity atleta, CarreraEntity carrera,
			CategoriaEntity categoria) {
		File file = new File("recibos/" + inscripcion.getEmailAtleta() + inscripcion.getIdCarrera() + ".txt");
		String text = "";

		try {
			// creates the file
			file.createNewFile();
			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file);

			// Writes the content to the file
			text += "Justificante de inscripcion\n";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String date = LocalDate.now().format(formatter);
			text += (date.toString() + "/n");
			text += "Atleta: " + atleta.getNombre() + " " + atleta.getApellido() + "\n";
			text += "Carrera: " + carrera.getNombre() + "\n";
			text += "Categoria: " + categoria.getNombre() + "\n";
			text += "Dorsal: " + inscripcion.getDorsal() + "\n";
			text += "Estado de la inscripcion: " + inscripcion.getEstado() + "\n";
			text += "Por favor, haga una transferencia de " + carrera.getPrecioInscripcion()
					+ "Ä a la siguiente cuenta:\n";
			text += "*numero de cuenta*\n";
			writer.write(text);
			writer.flush();
			writer.close();

			// Creates a FileReader Object
			FileReader fr = new FileReader(file);
			char[] a = new char[50];
			fr.read(a); // reads the content to the array

			for (char c : a)
				System.out.print(c); // prints the characters one by one
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return text;
		}
		return text;

	}

}
