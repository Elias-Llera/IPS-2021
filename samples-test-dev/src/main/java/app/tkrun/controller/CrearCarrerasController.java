package app.tkrun.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.tkrun.entities.CarreraEntity;
import app.tkrun.entities.PuntoDeControlEntity;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.PuntoDeControlModel;
import app.tkrun.view.CrearCarreraView;
import app.tkrun.view.PuntoDeControlPanel;
import app.util.SwingUtil;

public class CrearCarrerasController {
	CrearCarreraView crearCarreraView;
	CarreraModel carreraModel = new CarreraModel();
	PuntoDeControlModel puntosModel = new PuntoDeControlModel();
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

				new PlanificacionCarrerasController().init(crearCarreraView.getTextFieldFechaCelebracion().getText(),
						id);
			}
		});

		crearCarreraView.getBtnCategorias().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CategoriaController().init(id);
			}
		});

		crearCarreraView.getBtnAddPuntoDeControl().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				PuntoDeControlPanel pn = new PuntoDeControlPanel();
				pn.getBtnDelete().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						crearCarreraView.getPuntosDeControlPanel().remove(pn);
						crearCarreraView.getPuntosDeControlPanel().revalidate();
						crearCarreraView.getPuntosDeControlPanel().repaint();
					}
				});
				crearCarreraView.getPuntosDeControlPanel().add(pn);
				crearCarreraView.revalidate();
				crearCarreraView.repaint();
			}
		});

		crearCarreraView.getBtnCrearCarrera().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (comprobarCampos() && comprobarFecha() && comprobarTipo()) {
					Random r = new Random();
					id = r.nextInt(100000000) + 1;
					CarreraEntity carrera = new CarreraEntity();
					carrera.setIdCarrera(id);
					carrera.setNombre(crearCarreraView.getTextFieldNombreCarrera().getText());
					carrera.setTipo(crearCarreraView.getTextFieldTipoCarrera().getText());
					carrera.setDescripcion(crearCarreraView.getTextFieldDescripcion().getText());
					carrera.setFecha(crearCarreraView.getTextFieldFechaCelebracion().getText());
					carrera.setPlazas(Integer.parseInt(crearCarreraView.getTextFieldNumeroPlazas().getText()));
					carrera.setDistancia(Double.parseDouble(crearCarreraView.getTextFieldDistancia().getText()));
					for (Component panelPunto : crearCarreraView.getPuntosDeControlPanel().getComponents()) {
						try {
							PuntoDeControlEntity punto = new PuntoDeControlEntity();
							punto.setIdCarrera(id);
							punto.setNombre(((PuntoDeControlPanel) panelPunto).getNombrePunto());
							puntosModel.addPuntoDeControl(punto);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(crearCarreraView,
									"Error al parsear los puntos de control. Por favor, separe los puntos con comas e indique los decimales con puntos.");
							return;
						}
					}
					if (carreraModel.findCarreraIdentica(carrera) == 0) {
						carreraModel.addCarrera(carrera);
						crearCarreraView.getBtnCrearCarrera().setEnabled(false);
						crearCarreraView.getBtnPlazosInscripcion().setEnabled(true);
						crearCarreraView.getBtnCategorias().setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null,
								"Como la carrera ya esta creada se te permitira añadirle plazos");
						CarreraEntity buscarId = carreraModel.findIdCarrera(carrera);
						id = buscarId.getIdCarrera();
						crearCarreraView.getBtnCrearCarrera().setEnabled(false);
						crearCarreraView.getBtnPlazosInscripcion().setEnabled(true);
						crearCarreraView.getBtnCategorias().setEnabled(true);
					}

				} else if (!comprobarFecha()) {
					JOptionPane.showMessageDialog(null, "No se puede crear una carrera en el pasado o añadirle plazos");
				} else if (!comprobarTipo()) {
					JOptionPane.showMessageDialog(null, "Ese tipo de carrera no existe");
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

	private boolean comprobarFecha() {

		if (crearCarreraView.getTextFieldFechaCelebracion().getText().isEmpty()) {
			return false;
		}
		Date fechaactual = new Date(System.currentTimeMillis());
		String fechaInicio = crearCarreraView.getTextFieldFechaCelebracion().getText();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicioDate = null;
		try {
			fechaInicioDate = date.parse(fechaInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (fechaInicioDate.after(fechaactual)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comprobarTipo() {
		if (crearCarreraView.getTextFieldTipoCarrera().getText().toLowerCase().equals("montaña")
				|| crearCarreraView.getTextFieldTipoCarrera().getText().toLowerCase().equals("asfalto")) {
			return true;
		}
		return false;

	}

}
