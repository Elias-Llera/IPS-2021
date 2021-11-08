package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.model.CarreraModel;
import app.tkrun.view.CrearCarreraView;
import app.util.SwingUtil;

public class CrearCarrerasController {
	CrearCarreraView crearCarreraView;
	CarreraModel carreraModel = new CarreraModel();
	private int id;

	public void init() {
		crearCarreraView = new CrearCarreraView();

		crearCarreraView.getBtnPlazosInscripcion().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comprobarCampos()) {
					SwingUtil.exceptionWrapper(() -> openPlazosParaCrearCarreraView());
				} else {
					JOptionPane.showMessageDialog(null, "Valide sus campos");
				}

			}

			private void openPlazosParaCrearCarreraView() {

				new PlanificacionCarrerasController().init(crearCarreraView.getTextFieldFechaCelebracion().getText(), id);

			}
		});

		crearCarreraView.getBtnCrearCarrera().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comprobarCampos()) {
					Random r = new Random();
					id = r.nextInt(100000000)+1;
					CarreraEntity carrera = new CarreraEntity();
					carrera.setIdCarrera(id);
					carrera.setNombre(crearCarreraView.getTextFieldNombreCarrera().getText());
					carrera.setTipo(crearCarreraView.getTextFieldTipoCarrera().getText());
					carrera.setDescripcion(crearCarreraView.getTextFieldDescripcion().getText());
					carrera.setFecha(crearCarreraView.getTextFieldFechaCelebracion().getText());
					carrera.setPlazas(Integer.parseInt(crearCarreraView.getTextFieldNumeroPlazas().getText()));
					carrera.setDistancia(Double.parseDouble(crearCarreraView.getTextFieldDistancia().getText()));
					if(carreraModel.findCarreraIdentica(carrera)==0) {
						carreraModel.addCarrera(carrera);
						crearCarreraView.getBtnCrearCarrera().setEnabled(false);
						crearCarreraView.getBtnPlazosInscripcion().setEnabled(true);
						crearCarreraView.getBtnCategorias().setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "Como la carrera ya est� creada se te permitir� a�adirle plazos");
						CarreraEntity buscarId = carreraModel.findIdCarrera(carrera);
						id=buscarId.getIdCarrera();
						crearCarreraView.getBtnCrearCarrera().setEnabled(false);
						crearCarreraView.getBtnPlazosInscripcion().setEnabled(true);
						crearCarreraView.getBtnCategorias().setEnabled(true);
					}
					

				} else {
					JOptionPane.showMessageDialog(null, "Valide sus campos");
				}

			}

		});

		crearCarreraView.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crearCarreraView.dispose();
			}
		});

		crearCarreraView.setModal(true);
		crearCarreraView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		crearCarreraView.setLocationRelativeTo(null);
		crearCarreraView.setVisible(true);
	}

	public void addCarrera() {

	}

	private boolean comprobarCampos() {
		if (crearCarreraView.getTextFieldNombreCarrera().getText().isEmpty()
				|| crearCarreraView.getTextFieldDistancia().getText().isEmpty()
				|| crearCarreraView.getTextFieldTipoCarrera().getText().isEmpty()
				|| crearCarreraView.getTextFieldFechaCelebracion().getText().isEmpty()
				|| crearCarreraView.getTextFieldNumeroPlazas().getText().isEmpty()
				|| crearCarreraView.getTextFieldDescripcion().getText().isEmpty()) {
			return false;
		}
		return true;
	}

}
