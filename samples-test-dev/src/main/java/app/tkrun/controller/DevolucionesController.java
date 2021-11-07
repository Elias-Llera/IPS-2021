package app.tkrun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import app.tkrun.entities.DevolucionEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.entities.PlazoInscripcionEntity;
import app.tkrun.model.DevolucionModel;
import app.tkrun.model.InscripcionModel;
import app.tkrun.model.PlazoInscripcionModel;
import app.tkrun.view.DevolucionesView;
import app.util.SwingUtil;

public class DevolucionesController {

	private DevolucionesView view;
	private DevolucionModel devolucionesModel = new DevolucionModel();
	private InscripcionModel inscripcionModel = new InscripcionModel();

	private class DevolucionesInfo {
		int lineasNoAnalizadas = 0, inscripcionesConfirmadas = 0, inscripcionesRechazadas = 0,
				devolucionesCalculadas = 0, transferenciasNoConsistentes = 0;
	}

	private class TransferenciaInfo {
		private String email;
		private double cantidadTransferencia;
		private int idCarrera;

		public TransferenciaInfo(String[] datosTransferencia) {
			email = datosTransferencia[0];
			cantidadTransferencia = Double.parseDouble(datosTransferencia[1]);
			idCarrera = Integer.parseInt(datosTransferencia[2]);
		}
	}

	public void init() {
		view.getBtnGenerar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DevolucionesInfo res = parseFileTransferencias();
				String text = "Lineas no analizadas: " + res.lineasNoAnalizadas + "\n";
				text += "Información no consistente: " + res.transferenciasNoConsistentes + "\n";
				text += "Inscripciones confirmadas: " + res.inscripcionesConfirmadas + "\n";
				text += "Inscripciones rechazads: " + res.inscripcionesRechazadas + "\n";
				text += "Devolucinoes calculadas: " + res.devolucionesCalculadas;
				JOptionPane.showMessageDialog(view, text, "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	private DevolucionesInfo parseFileTransferencias() {
		DevolucionesInfo res = new DevolucionesInfo();

		try (BufferedReader br = new BufferedReader(new FileReader("transferencia.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.isBlank()) {
					continue;
				}
				String[] datosTransferencia = line.split("*");
				if (datosTransferencia.length != 3) {
					res.lineasNoAnalizadas++;
				} else {
					TransferenciaInfo transferencia = new TransferenciaInfo(datosTransferencia);
					try {
						calculateDevolucion(res, transferencia);
					} catch (NumberFormatException e) {
						res.lineasNoAnalizadas++;
					}
				}
			}
		} catch (IOException e) {
			return null;
		}
		return res;
	}

	private void calculateDevolucion(DevolucionesInfo res, TransferenciaInfo transferencia) {
		DevolucionEntity devolucion = new DevolucionEntity();
		devolucion.setEmailAtleta(transferencia.email);
		devolucion.setIdCarrera(transferencia.idCarrera);
		devolucion.setCantidad(calculateDeuda(res, transferencia));

		if (devolucion.getCantidad() != 0) {
			devolucionesModel.addDevolucion(devolucion);
			res.devolucionesCalculadas++;
		}
		for (InscripcionEntity inscripcion : new InscripcionModel().findExpiredPreinscriptions()) {
			rechazarInscripcion(res, inscripcion.getEmailAtleta(), inscripcion.getIdCarrera());
		}
	}

	private double calculateDeuda(DevolucionesInfo res, TransferenciaInfo transferencia) {

		InscripcionEntity inscripcion = inscripcionModel.findInscripcion(transferencia.email, transferencia.idCarrera);
		PlazoInscripcionEntity plazo = new PlazoInscripcionModel().findByInscripcion(inscripcion);

		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date maximoParaPago = new Date(System.currentTimeMillis() - (3 * DAY_IN_MS));

		double deuda = 0;

		if (inscripcion == null) {
			res.transferenciasNoConsistentes++;
		} else {
			if (inscripcion.getEstado().equals("PREINSCRITO")) {
				if (inscripcion.getFecha().before(maximoParaPago)) {
					double precioInscripcion = plazo.getPrecio();
					if (precioInscripcion == transferencia.cantidadTransferencia) {
						deuda = 0;
						aceptarInscripcion(res, transferencia.email, transferencia.idCarrera);
					} else if (precioInscripcion < transferencia.cantidadTransferencia) {
						deuda = transferencia.cantidadTransferencia - precioInscripcion;
						aceptarInscripcion(res, transferencia.email, transferencia.idCarrera);
					} else {
						deuda = transferencia.cantidadTransferencia;
						rechazarInscripcion(res, transferencia.email, transferencia.idCarrera);
					}
				} else {
					deuda = transferencia.cantidadTransferencia;
					rechazarInscripcion(res, transferencia.email, transferencia.idCarrera);
				}
			} else {
				deuda = transferencia.cantidadTransferencia;
			}
		}
		return deuda;
	}

	private void rechazarInscripcion(DevolucionesInfo res, String email, int idCarrera) {
		inscripcionModel.rejectInscription(email, idCarrera);
		res.inscripcionesRechazadas++;
	}

	private void aceptarInscripcion(DevolucionesInfo res, String email, int idCarrera) {
		inscripcionModel.acceptInscription(email, idCarrera);
		res.inscripcionesConfirmadas++;
	}
	
	public void getListaDevoluciones() {
		List<DevolucionEntity> devoluciones = devolucionesModel.findAll();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(devoluciones, new String[] { "email", "idCarrera", "cantidad" });
		view.getTablaDevoluciones().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaDevoluciones());
	}


}
