package app.tkrun.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import app.tkrun.entities.DevolucionEntity;
import app.tkrun.entities.InscripcionEntity;
import app.tkrun.model.CarreraModel;
import app.tkrun.model.InscripcionModel;

public class DevolucionesController {

	InscripcionModel inscripcionModel = new InscripcionModel();
	CarreraModel carreraModel = new CarreraModel();

	private class DevolucionesInfo {
		int lineasNoAnalizadas = 0, inscripcionesConfirmadas = 0, devolucionesCalculadas = 0;
	}

	private DevolucionesInfo calcularDevoluciones() throws IOException {
		DevolucionesInfo res = new DevolucionesInfo();
		List<DevolucionEntity> devoluciones = new ArrayList<>();
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date maximoParaPago = new Date(System.currentTimeMillis() - (3 * DAY_IN_MS));

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
					try {
						String email = datosTransferencia[0];
						double cantidadTransferencia = Double.parseDouble(datosTransferencia[1]);
						String nombreCarrera = datosTransferencia[2];
						List<InscripcionEntity> inscripcionesAtleta = inscripcionModel
								.findInscripcionesByEmailAtleta(email);
						double deudaTotalOrganizacion = 0;
						for (InscripcionEntity inscripcion : inscripcionesAtleta) { // cambiar forEach para sacar 1
																					// isncripcion a partir del dni y de
							DevolucionEntity devolucion = new DevolucionEntity();
							devolucion.setEmailAtleta(email);
							devolucion.setNombreCarrera(nombreCarrera);
							// la carrera
							if (inscripcion.getEstado().equals("PREINSCRITO")) { // La inscripicion esta pendiente de
																					// pago
								if (inscripcion.getFecha().before(maximoParaPago)) { // La inscripcion se puede pagar
									double precioInscripcion = carreraModel.findCarrera(inscripcion.getIdCarrera())
											.getPrecioInscripcion();
									if (precioInscripcion < cantidadTransferencia) { // La transferencia es mas de lo
										double deuda = 0;
										devolucion.setCantidad(deuda);						// necesario, devolucion+aceptar

									} else if (precioInscripcion > cantidadTransferencia) {// La transferencia es menor
										double deuda = 0;
										devolucion.setCantidad(deuda);						// de lo necesario,
																							// devolucion+rechazar

									} else { // La transferencia es exacta, aceptar

									}
								} else { // La inscripcion esta pendiente de pago pero no esta dentro del plazo, devolucion + rechazar
									double deuda = 0;
									devolucion.setCantidad(deuda);
								}
							} else { // La inscripcion no esta pendiente de pago, devolucion
								double deuda = 0;
								devolucion.setCantidad(deuda);
							}
						}
						
						
					} catch (NumberFormatException e) {
						res.lineasNoAnalizadas++;
					}
				}
			}
		}
		return res;
	}
}
