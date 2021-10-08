package giis.demo.tkrun.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import giis.demo.tkrun.entities.AtletaEntity;
import giis.demo.tkrun.entities.CarreraEntity;
import giis.demo.tkrun.entities.CategoriaEntity;
import giis.demo.tkrun.entities.InscripcionEntity;
import giis.demo.tkrun.model.AtletaModel;
import giis.demo.tkrun.model.CarreraModel;
import giis.demo.tkrun.model.CategoriaModel;
import giis.demo.tkrun.model.InscripcionModel;
import giis.demo.tkrun.view.InscripcionView;
import giis.demo.util.SwingUtil;
import giis.demo.util.UnexpectedException;
import giis.demo.util.Util;

public class InscripcionController {
	
	InscripcionModel inscripcionModel = new InscripcionModel();
	AtletaModel atletaModel = new AtletaModel();
	CarreraModel carreraModel = new CarreraModel();
	CategoriaModel categoriaModel = new CategoriaModel();
	
	InscripcionView view = new InscripcionView();//vista para añadirle el actionListener

	
	public void init() {
		view.getBtnOk().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> addInscripcion(null, 0));//FALTA SACAR LOS DATOS DE LA VISTA
			}
		});;
	}
	
	public void addInscripcion(String email, int idCarrera) {
		//Comprobar que no existe otra inscripcion
		InscripcionEntity  ie = inscripcionModel.findInscripcion(email, idCarrera);
		if(!ie.equals(null)) {
			throw new UnexpectedException("La inscripción ya existe.");
		}
		
		//Obtener atleta
		AtletaEntity ae = atletaModel.findAtleta(email);
		Util.validateNotNull(ae, "El atleta no existe.");
		
		//Obtener carrera
		CarreraEntity ce = carreraModel.findCarrera(idCarrera);
		Util.validateNotNull(ce, "La carrera no existe");
		
		//Comprobar que el plazo de inscripcion esta abierto
		Date now = new Date(System.currentTimeMillis());
		if(ce.getFinInscripcion().compareTo(now) < 0) {
			throw new UnexpectedException("La inscripción ya ha finalizado.");
		}
		
		//Comprobar que hay plazas libres
		List<InscripcionEntity> inscripciones = inscripcionModel.findInscripciones(idCarrera);
		if(inscripciones.size()>=ce.getPlazas()) {
			throw new UnexpectedException("No quedan plazas libres en la carrera.");
		}
		
		//Calcular categoria
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(now);
		Calendar calBirth = Calendar.getInstance();
		calBirth.setTime(ae.getNacimiento());
		CategoriaEntity categoria = categoriaModel.findCategoria(idCarrera, calNow.get(Calendar.YEAR)-calBirth.get(Calendar.YEAR), ae.getSexo());
		
		//Calcular dorsal
		int dorsal = inscripciones.size()+1;
		
		//Guardar inscripcion
		InscripcionEntity inscripcion = new InscripcionEntity();
		inscripcion.setDorsal(dorsal);
		inscripcion.setEstado("PREINSCRIPCION");
		inscripcion.setIdCategoria(categoria.getId());
		inscripcion.setIdCarrera(idCarrera);
		inscripcion.setEmailAtleta(email);
		inscripcionModel.addInscripcion(ie);
		
		
		//Generar recibo
	}
	
}
