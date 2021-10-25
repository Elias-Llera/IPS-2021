package app.tkrun.controller;

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

import app.tkrun.entities.AtletaEntity;
import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.view.InscripcionTarjetaView;
import app.tkrun.view.InscripcionView;

public class InscripcionController {

	InscripcionModel inscripcionModel = new InscripcionModel();
	AtletaModel atletaModel = new AtletaModel();
	CarreraModel carreraModel = new CarreraModel();
	CategoriaModel categoriaModel = new CategoriaModel();

	InscripcionView inscripcionView;// vista para añadirle el actionListener
	InscripcionTarjetaView it = new InscripcionTarjetaView();

	public void init(String nombreCarrera, int idCarrera) {
		inscripcionView = new InscripcionView(nombreCarrera);
		inscripcionView.getBtnOk().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInscripcion(inscripcionView.getTextEmail().getText(), idCarrera);
			}
		});
		inscripcionView.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inscripcionView.dispose();
			}
		});
		it.getBtnListo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (it.comprobarCampos()) {
					it.mostrarMensaje();
					it.dispose();
				}
			}
		});
		inscripcionView.setModal(true);
		inscripcionView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inscripcionView.setLocationRelativeTo(null);
		inscripcionView.setVisible(true);
	}

	public void addInscripcion(String email, int idCarrera) {
		// Comprobar que no existe otra inscripcion
		InscripcionEntity ie = inscripcionModel.findInscripcion(email, idCarrera);
		if (!(ie == null)) {
			inscripcionView.showErrorDialog("La inscripcion ya existe");
			return;
		}

		// Obtener atleta
		AtletaEntity ae = atletaModel.findAtleta(email);
		if (ae == null) {
			inscripcionView.showErrorDialog("El atleta no existe");
			return;
		}

		// Obtener carrera
		CarreraEntity ce = carreraModel.findCarrera(idCarrera);
		if (ce == null) {
			inscripcionView.showErrorDialog("La carrera no existe");
			return;
		}

		// Comprobar que el plazo de inscripcion esta abierto
		Date now = new Date(System.currentTimeMillis());
		if (Date.valueOf(ce.getInicioInscripcion()).compareTo(now) > 0) {
			inscripcionView.showErrorDialog("La inscripcion todavia no esta abierta");
			return;
		}
		if (Date.valueOf(ce.getFinInscripcion()).compareTo(now) < 0) {
			inscripcionView.showErrorDialog("La inscripcion ya no esta abierta");
			return;
		}

		// Comprobar que hay plazas libres
		List<InscripcionEntity> inscripciones = inscripcionModel.findInscripciones(idCarrera);
		if (inscripciones.size() >= ce.getPlazas()) {
			inscripcionView.showErrorDialog("No quedan plazas libres");
			return;
		}

		// Calcular categoria
		CategoriaEntity categoria = calculateCategoria(idCarrera, ae, now);

		if (categoria == null) {
			inscripcionView.showErrorDialog("No hay categoria para esa carrera");
			return;
		}

		// Calcular dorsal
		int dorsal = inscripciones.size() + 1;

		// Comprobar metodo de pago
		String selectedButtonText = inscripcionView.getSelectedButtonText();
		if (selectedButtonText == null) {
			inscripcionView.showErrorDialog("Seleccione un metodo de pago");
			return;
		}
		
		// Guardar inscripcion
		InscripcionEntity inscripcion = saveInscripcion(email, idCarrera, ae, ce, categoria, dorsal, selectedButtonText);
		
		// Mostrar recibo
		if(selectedButtonText.equals("Tarjeta")) {
			String text = reciboTarjetaDeCredito(inscripcion, ae, ce, categoria);
			text += "Copia del recibo generada en la carpeta recibos";
			inscripcionView.showSuccessDialog(text);
		}else if (selectedButtonText.equals("Transferencia")){
			String text = reciboTransferencia(inscripcion, ae, ce, categoria);
			text += "Copia del recibo generada en la carpeta recibos";
			inscripcionView.showSuccessDialog(text);
		}
		
		// Operacion realizada
		inscripcionView.showSuccessDialog("Inscripcion realizada");
		inscripcionView.dispose();
	}

	private InscripcionEntity saveInscripcion(String email, int idCarrera, AtletaEntity ae, CarreraEntity ce,
			CategoriaEntity categoria, int dorsal, String selectedButtonText) {
		InscripcionEntity inscripcion = new InscripcionEntity();
		inscripcion.setDorsal(dorsal);
		inscripcion.setIdCategoria(categoria.getIdCategoria());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);
		if (selectedButtonText.equals("Tarjeta")) {
			obtenerDatosTarjeta();
			inscripcion.setEstado("Pagado");
		} else if (selectedButtonText.equals("Transferencia")) {
			inscripcion.setEstado("Pendiente de Pago");
		}

		inscripcionModel.addInscripcion(inscripcion);

		return inscripcion;
	}

	private void obtenerDatosTarjeta() {
		it.setModal(true);
		it.setLocationRelativeTo(null);
		it.setVisible(true);
	}

	private CategoriaEntity calculateCategoria(int idCarrera, AtletaEntity ae, Date now) {
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(now);
		Calendar calBirth = Calendar.getInstance();
		calBirth.setTime(Date.valueOf(ae.getFechaNacimiento()));
		int edad = calNow.get(Calendar.YEAR) - calBirth.get(Calendar.YEAR);
		CategoriaEntity categoria = categoriaModel.findCategoria(idCarrera, edad, ae.getSexo());
		return categoria;
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
					+ "� a la siguiente cuenta:\n";
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

	private String reciboTarjetaDeCredito(InscripcionEntity inscripcion, AtletaEntity atleta, CarreraEntity carrera,
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
			text += "Pago realizado con tarjeta de credito\n";
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
