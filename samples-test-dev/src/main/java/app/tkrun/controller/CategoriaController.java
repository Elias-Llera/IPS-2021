package app.tkrun.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.tkrun.entities.CategoriaEntity;
import app.tkrun.model.CategoriaModel;
import app.tkrun.view.ConfiguracionCategoriasView;
import app.tkrun.view.PanelCategoria;

public class CategoriaController {

	CategoriaModel cm = new CategoriaModel();
	ConfiguracionCategoriasView view = new ConfiguracionCategoriasView();

	private int idCarrera;

	public void init(int idCarrera) {
		view.getBtnAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelCategoria pn = new PanelCategoria();
				pn.getBtnDelete().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						view.getPanelCategorias().remove(pn);
						view.getPanelCategorias().revalidate();
						view.getPanelCategorias().repaint();
					}
				});
				view.getPanelCategorias().add(pn);
				view.getPanelCategorias().revalidate();
				view.getPanelCategorias().repaint();
			}
		});
		view.getBtnConfirmar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<CategoriaEntity> categorias = generateCategorias();
				if (validateCategorias(categorias)) {
					addCategorias(categorias);
					JOptionPane.showMessageDialog(view, "Categorias creadas");
					view.getBtnConfirmar().setEnabled(false);
					view.getBtnAdd().setEnabled(false);
				}
			}
		});
		this.idCarrera = idCarrera;
		view.setModal(true);
		view.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}

	private boolean validateCategorias(List<CategoriaEntity> categorias) {
		List<CategoriaEntity> categoriasMasculinas = new ArrayList<>();
		List<CategoriaEntity> categoriasFemeninas = new ArrayList<>();
		for (CategoriaEntity categoria : categorias) {
			if (categoria.getEdadFinal() == 100) {
				categoria.setEdadFinal(Integer.MAX_VALUE);
			}
			if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
				JOptionPane.showMessageDialog(view, "Todas las categorias deben tener nombre.");
				return false;
			}
			if (categoria.getSexo().equals("HOMBRE")) {
				if (categoriasMasculinas.size() != 0) {
					if (categoriasMasculinas.get(categoriasMasculinas.size() - 1).getEdadFinal() + 1 == categoria
							.getEdadInicio()) {
						categoriasMasculinas.add(categoria);
					} else {
						JOptionPane.showMessageDialog(view,
								"Las categorias masculinas deben cubrir todo el rango de edades sin conflictos. Revise las categorias "
										+ categoriasMasculinas.get(categoriasMasculinas.size() - 1).getNombre() + " y "
										+ categoria.getNombre());
						return false;
					}
				} else {
					if (categoria.getEdadInicio() != 18) {
						JOptionPane.showMessageDialog(view,
								"Las primera categoria masculina debe empezar en 18 años.");
						return false;
					} else {
						categoriasMasculinas.add(categoria);
					}
				}
			} else if (categoria.getSexo().equals("MUJER")) {
				if (categoriasFemeninas.size() != 0) {
					if (categoriasFemeninas.get(categoriasFemeninas.size() - 1).getEdadFinal() + 1 == categoria
							.getEdadInicio()) {
						categoriasFemeninas.add(categoria);
					} else {
						JOptionPane.showMessageDialog(view,
								"Las categorias femeninas deben cubrir todo el rango de edades sin conflictos.  Revise las categorias "
										+ categoriasFemeninas.get(categoriasFemeninas.size() - 1).getNombre() + " y "
										+ categoria.getNombre());
						return false;
					}
				} else {
					if (categoria.getEdadInicio() != 18) {
						JOptionPane.showMessageDialog(view,
								"Las primera categoria femenina debe empezar en 18 a�os.");
						return false;
					} else {
						categoriasFemeninas.add(categoria);
					}
				}
			}
		}

		if (categoriasFemeninas.size() == 0) {
			JOptionPane.showMessageDialog(view, "Debe haber categorias para mujeres");
			return false;
		}

		if (categoriasMasculinas.size() == 0) {
			JOptionPane.showMessageDialog(view, "Debe haber categorias para hombres");
			return false;
		}

		if (categoriasMasculinas.get(categoriasMasculinas.size() - 1).getEdadFinal() != Integer.MAX_VALUE) {
			JOptionPane.showMessageDialog(view, "La ultima categoria masculina no debe tener limite de edad");
			return false;
		}
		if (categoriasFemeninas.get(categoriasFemeninas.size() - 1).getEdadFinal() != Integer.MAX_VALUE) {
			JOptionPane.showMessageDialog(view, "La ultima categoria femenina no debe tener limite de edad");
			return false;
		}
		return true;
	}

	private void addCategorias(List<CategoriaEntity> categorias) {
		for (CategoriaEntity categoria : categorias) {
			cm.addCategoria(categoria);
		}
	}

	private List<CategoriaEntity> generateCategorias() {
		List<Component> components = Arrays.asList(view.getPanelCategorias().getComponents());
		List<CategoriaEntity> res = new ArrayList<>();
		int nextId = cm.getNextId();
		for (Component component : components) {
			CategoriaEntity categoria = new CategoriaEntity();
			categoria.setIdCategoria(nextId);
			categoria.setIdCarrera(idCarrera);
			categoria.setNombre(((PanelCategoria) component).getNombre());
			categoria.setSexo(((PanelCategoria) component).getSexo());
			categoria.setEdadInicio(((PanelCategoria) component).getEdadInicial());
			categoria.setEdadFinal(((PanelCategoria) component).getEdadFinal());
			res.add(categoria);
			nextId++;
		}
		Collections.sort(res, new Comparator<CategoriaEntity>() {
			@Override
			public int compare(CategoriaEntity lhs, CategoriaEntity rhs) {
				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				return lhs.getEdadInicio() > rhs.getEdadInicio() ? 1
						: (lhs.getEdadInicio() < rhs.getEdadInicio()) ? -1 : 0;
			}
		});
		return res;
	}

}
