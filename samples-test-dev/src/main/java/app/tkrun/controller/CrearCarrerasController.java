package app.tkrun.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.view.CrearCarreraView;
import app.tkrun.view.InscripcionNoAtletaView;
import app.util.SwingUtil;

public class CrearCarrerasController {
	CrearCarreraView crearCarreraView;

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

				new PlanificacionCarrerasController().init();

			}
		});
		
		//Debajo el tuyo

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
				|| crearCarreraView.getTextFieldNumeroPlazas().getText().isEmpty()) {
			return false;
		}
		return true;
	}

}
