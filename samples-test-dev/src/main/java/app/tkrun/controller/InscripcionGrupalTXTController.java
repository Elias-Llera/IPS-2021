package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import app.tkrun.view.InscripcionGrupalTXTView;
import app.util.SwingUtil;

public class InscripcionGrupalTXTController {
	InscripcionGrupalTXTView nscripcionGrupalView;

	InscripcionGrupalModel igrupalmodel = new InscripcionGrupalModel();
	AtletaModel atletaModel = new AtletaModel();
	InscripcionModel inscripcionModel = new InscripcionModel();
	PlazosDeInscripcionModel plazosModel = new PlazosDeInscripcionModel();
	DevolucionModel devolucionM = new DevolucionModel();
	CarreraModel carreraModel = new CarreraModel();
	CategoriaModel categoriaModel = new CategoriaModel();

	private String club;
	private int contIns;
	private int contNotIns;
	private List<AtletaEntity> noInscritos = new ArrayList<AtletaEntity>();

	public void init(String nombreCarrera, int idCarrera) {
		nscripcionGrupalView = new InscripcionGrupalTXTView();

		nscripcionGrupalView.getBtnInsertar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contIns = 0;
				contNotIns = 0;
				noInscritos.clear();
				if (comprobarCampos()) {

					logicaRellenar(nombreCarrera, idCarrera, leerFichero());

					nscripcionGrupalView.getTextFieldNumeroIns().setText(Integer.toString(contIns));
					nscripcionGrupalView.getTextFieldNumeroNoIns().setText(Integer.toString(contNotIns));
					nscripcionGrupalView.getTextFieldTotal().setText(Integer.toString(contIns + contNotIns));
				} else {
					JOptionPane.showMessageDialog(null, "Obligatorio indicar el dichero txt del que se quiere leer");
				}

				getInscritos(idCarrera);
				getNoInscritos();

			}

		});

		nscripcionGrupalView.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nscripcionGrupalView.dispose();
			}
		});

		nscripcionGrupalView.setModal(true);
		nscripcionGrupalView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		nscripcionGrupalView.setLocationRelativeTo(null);
		nscripcionGrupalView.setVisible(true);
	}

	private void logicaRellenar(String nombreCarrera, int idCarrera, List<String> fichero) {
		club = fichero.get(0);
		for (int i = 1; i < fichero.size(); i++) {
			String[] atleta = fichero.get(i).split(" ");

			if (atletaModel.findSiEsAtleta(atleta[0]) == 0) {
				// no atleta no inscito
				if(addInscripcionNoAttleta(atleta[0], atleta[1], atleta[2], atleta[3], atleta[4])) {
					addInscripcion(atleta[0], idCarrera, club);
				}
				
				
			} else {
				// atleta y inscrito
				InscripcionEntity ie = inscripcionModel.findInscripcion(atleta[0], idCarrera);
				if (!(ie == null)) {
					InscripcionEntity insc = inscripcionModel.findInscripcion(atleta[0], idCarrera);
					if (insc.getEstado().equals("INSCRITO")
							&& igrupalmodel.findInscripcion(atleta[0], idCarrera, club) == 0) {
						PlazosDeInscripcionEntity plazo = plazosModel.getListaPlazosInscripciones(idCarrera,
								insc.getFecha());

						devolucionM.addDevolucion(atleta[0], idCarrera, plazo.getPrecio());

					}
					inscripcionModel.actualizarInscripcion(atleta[0], idCarrera);
					if (igrupalmodel.findInscripcion(atleta[0], idCarrera, club) == 0) {
						igrupalmodel.addInscripcion(idCarrera, atleta[0], club);
					}

					contIns++;

				} else {
					// atleta no inscrito
					addInscripcion(atleta[0], idCarrera, club);

				}

			}
		}

	}

	private void getInscritos(int idCarrera) {
		AtletaModel am = new AtletaModel();
		InscripcionModel im;
		CategoriaModel cm;

		List<AtletaEntity> atletas = am.findAllAtletas();
		List<ParticipanteEntity> participantes = new ArrayList<ParticipanteEntity>();

		for (AtletaEntity a : atletas) {
			im = new InscripcionModel();

			InscripcionEntity inscripcion = im.findInscripcion(a.getEmailAtleta(), idCarrera);
			if (igrupalmodel.findInscripcion(a.getEmailAtleta(), idCarrera, club) == 0) { // sacar club del txt
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
				new String[] { "emailAtleta", "nombreAtleta", "nombreCategoria", "estado" });

		nscripcionGrupalView.getTableInscritosArchivo().setModel(tmodel);

		SwingUtil.autoAdjustColumns(nscripcionGrupalView.getTableInscritosArchivo());
	}

	private void getNoInscritos() {

		TableModel tmodel = SwingUtil.getTableModelFromPojos(noInscritos,
				new String[] { "emailAtleta", "nombre", "apellido", "fechaNacimiento", "sexo", "causa" });

		nscripcionGrupalView.getTableNoInscritosArchivo().setModel(tmodel);

		SwingUtil.autoAdjustColumns(nscripcionGrupalView.getTableNoInscritosArchivo());
	}

	private boolean comprobarCampos() {
		if (nscripcionGrupalView.getTextFieldArchivo().getText().strip().length() == 0) {
			return false;
		}

		return true;
	}

	public boolean addInscripcionNoAttleta(String email, String nombre, String apellido, String fechaNacimiento,
			String sexo) {
		
		if(comprobarMayorDeEdad(fechaNacimiento) && comprobarGenero(sexo)) {
			AtletaEntity atleta = new AtletaEntity();
			atleta.setEmailAtleta(email);
			atleta.setNombre(nombre);
			atleta.setApellido(apellido);
			atleta.setFechaNacimiento(fechaNacimiento);
			atleta.setSexo(sexo);
			atletaModel.addAtleta(atleta);
			return true;
		}else {
			contNotIns++;
			return false;
		}
		
		
	}

	private boolean comprobarMayorDeEdad(String fechaNacimiento) {

		DateTimeFormatter formatoDelTexto = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaCumple = null;
		fechaCumple = LocalDate.parse(fechaNacimiento, formatoDelTexto);
		if (LocalDate.now().minusYears(18).isAfter(fechaCumple)) {
			return true;
		}

		return false;

	}

	private boolean comprobarGenero(String sexo) {
		if (sexo.toLowerCase().equals("hombre")
				||sexo.toLowerCase().equals("mujer")) {
			return true;
		}
		return false;

	}

	public void addInscripcion(String email, int idCarrera, String Club) {
		System.out.println("add inscripcion");
		InscripcionEntity inscripcion = new InscripcionEntity();

		// Obtener atleta
		AtletaEntity ae = atletaModel.findAtleta(email);
		if (ae == null) {

			contNotIns++;
			noInscritos.add(ae);
			noInscritos.get(noInscritos.size() - 1).setCausa("No atleta");
			return;
		}

		// Obtener carrera
		CarreraEntity ce = carreraModel.findCarrera(idCarrera);
		if (ce == null) {

			contNotIns++;
			noInscritos.add(ae);
			noInscritos.get(noInscritos.size() - 1).setCausa("No carrera");
			return;
		}

		// Comprobar que hay un plazo de inscripcion esta abierto
		Date now = new Date(System.currentTimeMillis());

		InscripcionEntity aux = new InscripcionEntity();
		aux.setIdCarrera(ce.getIdCarrera());
		aux.setFecha(now.toString());
		PlazosDeInscripcionEntity plazo = plazosModel.findByInscripcion(aux);
		if (plazo == null) {

			contNotIns++;
			noInscritos.add(ae);
			noInscritos.get(noInscritos.size() - 1).setCausa("No plazo");
			return;
		}

		// Comprobar que hay plazas libres
		List<InscripcionEntity> inscripciones = inscripcionModel.findInscripcionesByIdCarrera(idCarrera);
		if (inscripciones.size() >= ce.getPlazas()) {

			contNotIns++;
			noInscritos.add(ae);
			noInscritos.get(noInscritos.size() - 1).setCausa("No plazas");
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

			contNotIns++;

			noInscritos.add(ae);
			noInscritos.get(noInscritos.size() - 1).setCausa("No categoria");
			return;
		}

		// Calcular dorsal
		int dorsal = inscripciones.size() + 1;

		// Guardar inscripcion

		inscripcion.setDorsal(dorsal);
		inscripcion.setIdCategoria(categoria.getIdCategoria());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);
		inscripcion.setIdPlazoInscripcion(plazo.getIdPlazosDeInscripcion());
		inscripcion.setEstado("INSCRITO");

		inscripcionModel.addInscripcionGrupal(inscripcion);
		igrupalmodel.addInscripcion(idCarrera, email, Club);
		contIns++;

	}

	private List<String> leerFichero() {
		List<String> txt = new ArrayList<String>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(nscripcionGrupalView.getTextFieldArchivo().getText());
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null)
				txt.add(linea);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {

				JOptionPane.showMessageDialog(nscripcionGrupalView, "No se ha encontrado ese archivo", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		return txt;
	}
}
