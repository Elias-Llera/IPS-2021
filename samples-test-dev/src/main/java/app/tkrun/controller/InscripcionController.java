package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.PlazosDeInscripcionEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.model.PlazosDeInscripcionModel;
import app.tkrun.view.InscripcionTarjetaView;
import app.tkrun.view.InscripcionView;
import app.util.SwingUtil;

public class InscripcionController {

	InscripcionModel inscripcionModel = new InscripcionModel();
	AtletaModel atletaModel = new AtletaModel();
	CarreraModel carreraModel = new CarreraModel();
	CategoriaModel categoriaModel = new CategoriaModel();
	PlazosDeInscripcionModel plazosModel = new PlazosDeInscripcionModel();

	InscripcionView inscripcionView;// vista para añadirle el actionListener
	InscripcionTarjetaView it = new InscripcionTarjetaView();

	public void init(String nombreCarrera, int idCarrera) {
		inscripcionView = new InscripcionView(nombreCarrera);
		inscripcionView.getBtnOk().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (atletaModel.findSiEsAtleta(inscripcionView.getTextEmail().getText()) == 0) {
					SwingUtil.exceptionWrapper(() -> openInscriptionNoAtletaView());
				} else {
					addInscripcion(inscripcionView.getTextEmail().getText(), idCarrera,inscripcionView.getSelectedButtonText());

				}
			}

			private void openInscriptionNoAtletaView() {
				new InscripcionNoAtletaController().init(nombreCarrera, idCarrera,
						inscripcionView.getTextEmail().getText(), true, inscripcionView.getSelectedButtonText());

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

		inscripcionView.getBtnInscripcionGrupo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtil.exceptionWrapper(() -> openInscriptionGrupalView());
			}

			private void openInscriptionGrupalView() {
				new InscripcionGrupalController().init(nombreCarrera, idCarrera);

			}
		});
		inscripcionView.setModal(true);
		inscripcionView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inscripcionView.setLocationRelativeTo(null);
		inscripcionView.setVisible(true);
	}

	public void addInscripcion(String email, int idCarrera, String seleccionado) {
		// Comprobar que no existe otra inscripcion
		InscripcionEntity ie = inscripcionModel.findInscripcion(email, idCarrera);
		if (!(ie == null)) {
			JOptionPane.showMessageDialog(inscripcionView, "La inscripcion ya existe", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Obtener atleta
		AtletaEntity ae = atletaModel.findAtleta(email);
		if (ae == null) {
			JOptionPane.showMessageDialog(inscripcionView, "El atleta no existe", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Obtener carrera
		CarreraEntity ce = carreraModel.findCarrera(idCarrera);
		if (ce == null) {
			JOptionPane.showMessageDialog(inscripcionView, "La carrera no existe", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Comprobar que hay un plazo de inscripcion esta abierto
		Date fechaActual = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaActual);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date fechaActualTruncada = new Date(cal.getTimeInMillis());
		
		InscripcionEntity aux = new InscripcionEntity();
		aux.setIdCarrera(ce.getIdCarrera());
		aux.setFecha(fechaActualTruncada.toString());
		PlazosDeInscripcionEntity plazo = plazosModel.findByInscripcion(aux);
		if (plazo == null) {
			JOptionPane.showMessageDialog(inscripcionView, "No hay plazos de inscripcion abiertos.", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Comprobar que hay plazas libres
		List<InscripcionEntity> inscripciones = inscripcionModel.findInscripcionesByIdCarrera(idCarrera);
		if (inscripciones.size() >= ce.getPlazas()) {
			JOptionPane.showMessageDialog(inscripcionView, "No quedan plazas libres", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Calcular categoria
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(fechaActualTruncada);
		Calendar calBirth = Calendar.getInstance();
		calBirth.setTime(Date.valueOf(ae.getFechaNacimiento()));
		int edad = calNow.get(Calendar.YEAR) - calBirth.get(Calendar.YEAR);

		System.out.println(edad + " " + idCarrera + " " + ae.getSexo());

		CategoriaEntity categoria = categoriaModel.findCategoria(idCarrera, edad, ae.getSexo().toUpperCase());

		if (categoria == null) {
			JOptionPane.showMessageDialog(inscripcionView, "No hay categoria para esa carrera", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Calcular dorsal
		//int dorsal = inscripciones.size() + 1;
		int dorsal = 0;

		// Guardar inscripcion
		InscripcionEntity inscripcion = new InscripcionEntity();
		inscripcion.setDorsal(dorsal);
		inscripcion.setIdCategoria(categoria.getIdCategoria());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);
		inscripcion.setIdPlazoInscripcion(plazo.getIdPlazosDeInscripcion());

		String selectedButtonText = seleccionado;

		if (selectedButtonText == null) {
			JOptionPane.showMessageDialog(inscripcionView, "Seleccione un metodo de pago", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (selectedButtonText.equals("Tarjeta")) {
			inscripcion.setEstado("Pagado");
			it.setModal(true);
			it.setLocationRelativeTo(null);
			it.setVisible(true);
			String text = reciboTarjetaDeCredito(inscripcion, ae, ce, categoria);
			text += "Copia del recibo generada en la carpeta recibos";
			JOptionPane.showMessageDialog(inscripcionView, text, "RECIBO", JOptionPane.INFORMATION_MESSAGE);
		} else if (selectedButtonText.equals("Transferencia")) {
			inscripcion.setEstado("Pendiente de Pago");
			String text = reciboTransferencia(inscripcion, ae, ce, categoria, plazo);
			text += "Copia del recibo generada en la carpeta recibos";
			JOptionPane.showMessageDialog(inscripcionView, text, "RECIBO", JOptionPane.INFORMATION_MESSAGE);
		}

		inscripcionModel.addInscripcionGrupal(inscripcion);

		JOptionPane.showMessageDialog(inscripcionView, "Inscripcion realizada", "SUCCESS",
				JOptionPane.INFORMATION_MESSAGE);

		//inscripcionView.dispose();
	}

	private String reciboTransferencia(InscripcionEntity inscripcion, AtletaEntity atleta, CarreraEntity carrera,
			CategoriaEntity categoria, PlazosDeInscripcionEntity plazo) {
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
			text += (date.toString() + "\n");
			text += "Atleta: " + atleta.getNombre() + " " + atleta.getApellido() + "\n";
			text += "Carrera: " + carrera.getNombre() + "\n";
			text += "Categoria: " + categoria.getNombre() + "\n";
			text += "Dorsal: " + inscripcion.getDorsal() + "\n";
			text += "Estado de la inscripcion: " + inscripcion.getEstado() + "\n";

			text += "Por favor, haga una transferencia de " + plazo.getPrecio() + "� a la siguiente cuenta:\n";

			text += "*numero de cuenta*\n";
			writer.write(text);
			writer.flush();
			writer.close();

		} catch (IOException e) {
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

		} catch (IOException e) {
			e.printStackTrace();
			return text;
		}
		return text;
	}

}
