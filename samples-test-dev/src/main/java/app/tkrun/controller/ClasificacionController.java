package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.CategoriaEntity;
import app.tkrun.entities.ClasificacionParaTabla;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.TiempoEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.CategoriaModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.model.TiempoModel;
import app.tkrun.view.ClasificacionesView;

public class ClasificacionController {

	private final int CARRERA_NO_CELEBRADA = -1;
	private final int CLASIFICACION_NO_GENERADA = 0;
	private final int CARRERA_CON_CLASIFICACION = 1;

	private int idCarrera;
	private String genero;
	private TiempoModel tiempoModel = new TiempoModel();
	private InscripcionModel inscripcionModel = new InscripcionModel();
	private AtletaModel atletaModel = new AtletaModel();
	private CarreraModel carreraModel = new CarreraModel();
	private CategoriaModel categoriaModel = new CategoriaModel();

	List<ClasificacionParaTabla> definitiva;

	private ClasificacionesView view;

	public ClasificacionController(int idCarrera, String genero) {
		this.idCarrera = idCarrera;
		this.genero = genero;
	}

	public void init() {
		int aux = generateClasificacion();
		if (aux == CARRERA_NO_CELEBRADA) {
			JOptionPane.showMessageDialog(null, "La carrera aun no se ha celebrado", "ERROR",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			view = new ClasificacionesView(definitiva);
			view.setLocationRelativeTo(null);
			view.setVisible(true);
			view.getBtnGenerar().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					generateTiempos();
					view.dispose();
					init();
				}
			});
			
			if(aux == CLASIFICACION_NO_GENERADA) {
				JOptionPane.showMessageDialog(view, "La clasificacion aun no se ha procesado", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}else {
				view.getBtnGenerar().setEnabled(false);
			}
		}
	}

	private int generateClasificacion() {
		CarreraEntity ce = carreraModel.findCarrera(idCarrera);

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha = new Date();
		try {
			fecha = formato.parse(ce.getFecha());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);

		Date actual = Date.from(instant);

		if (!actual.after(fecha)) {
			return CARRERA_NO_CELEBRADA;
		}
		
		List<TiempoEntity> clasificaciones = tiempoModel.findClasificacionForCarrera(idCarrera);

		if (clasificaciones.isEmpty()) {
			return CLASIFICACION_NO_GENERADA;
		}
		
		List<ClasificacionParaTabla> noAcabadas = new ArrayList<ClasificacionParaTabla>();
		List<ClasificacionParaTabla> acabadas = new ArrayList<ClasificacionParaTabla>();
		definitiva = new ArrayList<ClasificacionParaTabla>();

		for (TiempoEntity clasificacion : clasificaciones) {
			AtletaEntity atleta = atletaModel.findAtleta(clasificacion.getEmailAtleta());
			CarreraEntity carrera = carreraModel.findCarrera(clasificacion.getIdCarrera());
			InscripcionEntity inscripcion = inscripcionModel.findInscripcion(atleta.getEmailAtleta(),
					carrera.getIdCarrera());
			CategoriaEntity categoria = categoriaModel.findCategoria(inscripcion.getIdCategoria());

			ClasificacionParaTabla aux = new ClasificacionParaTabla();
			aux.setIdCarrera(carrera.getIdCarrera());
			aux.setIdCategoria(inscripcion.getIdCategoria());
			aux.setNombreCategoria(categoria.getNombre());
			aux.setNombreAtleta(atleta.getNombre());
			aux.setGenero(genero);
			aux.setTiempo(clasificacion.getTiempo());

			if (clasificacion.getTiempo().equals("---")) {
				noAcabadas.add(aux);

			} else {
				acabadas.add(aux);
			}
		}

		int posicion = 0;
		for (ClasificacionParaTabla clasificacion : acabadas) {
			posicion++;
			clasificacion.setPosicion(posicion);
			definitiva.add(clasificacion);
		}

		for (ClasificacionParaTabla clasificacion : noAcabadas) {
			posicion++;
			clasificacion.setPosicion(posicion);
			definitiva.add(clasificacion);
		}
		return CARRERA_CON_CLASIFICACION;
	}
	
	public void generateTiempos() {
		String filename = JOptionPane.showInputDialog(view, "Por favor, introduzca el nombre del archivo que contiene los tiempos.");
		try {
			parseFileTiempos(filename);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Error al analizar el archivo: archivo no encontrado");
			return;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(view, e.getMessage());
			return;
		}
		JOptionPane.showMessageDialog(view, "Archivo de tiempo analizado: clasificacion generada.");
	}
	
	private void parseFileTiempos(String filename) throws IOException {
		List<Integer> dorsales = new ArrayList<>();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.isBlank()) {
				continue;
			}
			String[] datosTiempo = line.split("\\*");
			if (datosTiempo.length != 2) {
				throw new IllegalArgumentException("Formato de cronometraje no valido");
			} else {
				TiempoEntity tiempo = new TiempoEntity();
				tiempo.setIdCarrera(idCarrera);
				tiempo.setTiempo(datosTiempo[1]);
				InscripcionEntity inscripcion = inscripcionModel.findByCarreraAndDorsal(idCarrera, Integer.parseInt(datosTiempo[0]));
				if(inscripcion == null) {
					throw new IllegalArgumentException("Los resultados no son correctos: el dorsal no esta registrado en la carrera");
				}
				tiempo.setEmailAtleta(inscripcion.getEmailAtleta());
				dorsales.add(Integer.parseInt(datosTiempo[0]));
				tiempoModel.addTiempo(tiempo);
			}
		}
		
		List<InscripcionEntity> inscripciones = inscripcionModel.findInscripcionesByIdCarrera(idCarrera);
		for (InscripcionEntity inscripcion : inscripciones) {
			if(!dorsales.contains(inscripcion.getDorsal())) {
				TiempoEntity tiempo = new TiempoEntity();
				tiempo.setEmailAtleta(inscripcion.getEmailAtleta());
				tiempo.setIdCarrera(idCarrera);
				tiempo.setTiempo("DNF");
				tiempoModel.addTiempo(tiempo);
			}
		}
	}

}
