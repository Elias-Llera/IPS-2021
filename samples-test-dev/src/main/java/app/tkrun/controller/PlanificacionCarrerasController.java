package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.tkrun.entities.AtletaEntity;
import app.tkrun.model.AtletaModel;
import app.tkrun.view.InscripcionNoAtletaView;
import app.tkrun.view.PlanificacionCarrerasView;

public class PlanificacionCarrerasController {

	PlanificacionCarrerasView pcv;;
	AtletaModel atletaModel = new AtletaModel();
	InscripcionController ic = new InscripcionController();

	public void init() {
		pcv = new PlanificacionCarrerasView();

		pcv.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pcv.dispose();
			}
		});

		pcv.setModal(true);
		pcv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pcv.setLocationRelativeTo(null);
		pcv.setVisible(true);
	}

	private boolean comprobarCampos() {

		return true;
	}

}
