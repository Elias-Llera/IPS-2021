package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.ParticipanteEntity;
import app.tkrun.entities.PlazosDeInscripcionEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.DevolucionModel;
import app.tkrun.model.InscripcionGrupalModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.model.PlazosDeInscripcionModel;
import app.tkrun.view.InscripcionGrupalView;
import app.tkrun.view.InscripcionTarjetaView;
import app.tkrun.view.InscripcionView;
import app.util.SwingUtil;

public class InscripcionGrupalController {

	InscripcionGrupalView insGrupalView;
	AtletaModel atletaModel = new AtletaModel();
	InscripcionController ic = new InscripcionController();
	InscripcionGrupalModel igrupalmodel = new InscripcionGrupalModel();

	InscripcionModel inscripcionModel = new InscripcionModel();
	CarreraModel carreraModel = new CarreraModel();
	CategoriaModel categoriaModel = new CategoriaModel();
	PlazosDeInscripcionModel plazosModel = new PlazosDeInscripcionModel();
	DevolucionModel devolucionM = new DevolucionModel();

	InscripcionView inscripcionView;// vista para añadirle el actionListener
	InscripcionTarjetaView it = new InscripcionTarjetaView();
	private String club;

	public void init(String nombreCarrera, int idCarrera) {
		insGrupalView = new InscripcionGrupalView();

		insGrupalView.getBtnInsertar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobarCampos()) {
					club = insGrupalView.getTextFieldNombreClub().getText();
					if (atletaModel.findSiEsAtleta(insGrupalView.getTextFieldEmail().getText()) == 0) {
						SwingUtil.exceptionWrapper(() -> openInscriptionNoAtletaView());
					} else {
						InscripcionEntity ie = inscripcionModel
								.findInscripcion(insGrupalView.getTextFieldEmail().getText(), idCarrera);
						if (!(ie == null)) {

							InscripcionEntity i = inscripcionModel
									.findInscripcion(insGrupalView.getTextFieldEmail().getText(), idCarrera);

							if (i.getEstado().equals("INSCRITO")
									&& igrupalmodel.findInscripcion(insGrupalView.getTextFieldEmail().getText(),
											idCarrera, club) == 0) {

								PlazosDeInscripcionEntity plazo = plazosModel.getListaPlazosInscripciones(idCarrera,
										i.getFecha());
								devolucionM.addDevolucion(insGrupalView.getTextFieldEmail().getText(), idCarrera,
										plazo.getPrecio());
							}

							inscripcionModel.actualizarInscripcion(insGrupalView.getTextFieldEmail().getText(),
									idCarrera);
							igrupalmodel.addInscripcion(idCarrera, insGrupalView.getTextFieldEmail().getText(), club);
							JOptionPane.showMessageDialog(inscripcionView, "Actualizacion realizada", "SUCCESS",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							addInscripcion(insGrupalView.getTextFieldEmail().getText(), idCarrera, club);

						}

					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Obligatorio rellenar el nombre del club y el email del atleta");
				}

				getInscritos(idCarrera);
			}

			private void openInscriptionNoAtletaView() {
				new InscripcionNoAtletaController().init(nombreCarrera, idCarrera,
						insGrupalView.getTextFieldEmail().getText(), false, club);

			}

		});

		insGrupalView.getBtnRefrescar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getInscritos(idCarrera);
			}
		});

		insGrupalView.getBtnMedianteArchivo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtil.exceptionWrapper(() -> openInscripcionGrupalTXTView());
			}

			private void openInscripcionGrupalTXTView() {
				new InscripcionGrupalTXTController().init(nombreCarrera, idCarrera);

			}

		});

		insGrupalView.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insGrupalView.dispose();
			}
		});

		insGrupalView.setModal(true);
		insGrupalView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		insGrupalView.setLocationRelativeTo(null);
		insGrupalView.setVisible(true);
	}

	private void getInscritos(int idCarrera) {
		AtletaModel am = new AtletaModel();
		InscripcionModel im;
		CategoriaModel cm;
		InscripcionEntity inscripcion =null;

		List<AtletaEntity> atletas = am.findAllAtletas();
		List<ParticipanteEntity> participantes = new ArrayList<ParticipanteEntity>();

		for (AtletaEntity a : atletas) {
			im = new InscripcionModel();

			inscripcion = im.findInscripcion(a.getEmailAtleta(), idCarrera);
			if (igrupalmodel.findInscripcion(a.getEmailAtleta(), idCarrera,
					insGrupalView.getTextFieldNombreClub().getText()) == 0) {
				continue;
			} else {
				cm = new CategoriaModel();
				CategoriaEntity categoria = cm.findCategoria(inscripcion.getIdCategoria());
				ParticipanteEntity participante = new ParticipanteEntity();
				participante.setEmailAtleta(a.getEmailAtleta());
				participante.setNombreAtleta(a.getNombre());
				participante.setApellidoAtleta(a.getApellido());
				
				participante.setNombreCategoria(categoria.getNombre());
				participante.setEstado(inscripcion.getEstado());
				participante.setIdCarrera(inscripcion.getIdCarrera());
				participante.setDorsal(inscripcion.getDorsal());

				participantes.add(participante);
			}

		}

		TableModel tmodel = SwingUtil.getTableModelFromPojos(participantes,
				new String[] { "emailAtleta", "nombreAtleta", "nombreCategoria", "estado", "dorsal" });

		insGrupalView.getTableIGrupal().setModel(tmodel);

		SwingUtil.autoAdjustColumns(insGrupalView.getTableIGrupal());
	}

	private boolean comprobarCampos() {
		if (insGrupalView.getTextFieldNombreClub().getText().strip().length() == 0
				|| insGrupalView.getTextFieldEmail().getText().strip().length() == 0) {
			return false;
		}

		return true;
	}

	public void addInscripcion(String email, int idCarrera, String Club) {
		System.out.println("add inscripcion");
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
		Date now = new Date(System.currentTimeMillis());

		InscripcionEntity aux = new InscripcionEntity();
		aux.setIdCarrera(ce.getIdCarrera());
		aux.setFecha(now.toString());
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
		calNow.setTime(now);
		Calendar calBirth = Calendar.getInstance();
		calBirth.setTime(Date.valueOf(ae.getFechaNacimiento()));
		int edad = calNow.get(Calendar.YEAR) - calBirth.get(Calendar.YEAR);

		System.out.println(edad + " " + idCarrera + " " + ae.getSexo());

		CategoriaEntity categoria = categoriaModel.findCategoria(idCarrera, edad, ae.getSexo());

		if (categoria == null) {
			JOptionPane.showMessageDialog(inscripcionView, "No hay categoria para esa carrera", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		// Calcular dorsal
		int dorsal = inscripciones.size() + 1;

		// Guardar inscripcion
		InscripcionEntity inscripcion = new InscripcionEntity();
		inscripcion.setDorsal(dorsal);
		inscripcion.setIdCategoria(categoria.getIdCategoria());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);
		inscripcion.setIdPlazoInscripcion(plazo.getIdPlazosDeInscripcion());
		inscripcion.setEstado("INSCRITO");

		inscripcionModel.addInscripcionGrupal(inscripcion);
		igrupalmodel.addInscripcion(idCarrera, email, Club);

		JOptionPane.showMessageDialog(inscripcionView, "Inscripcion realizada", "SUCCESS",
				JOptionPane.INFORMATION_MESSAGE);

	}

}
