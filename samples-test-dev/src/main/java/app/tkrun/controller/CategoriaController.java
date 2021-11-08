package app.tkrun.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
				view.getPanelCategorias().add(new PanelCategoria());
			}
		});
		view.getBtnConfirmar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<CategoriaEntity> categorias = generateCategorias();
				validateCategorias(categorias);
				addCategorias(categorias);
			}
		});
		this.idCarrera = idCarrera;
	}

	private void validateCategorias(List<CategoriaEntity> categorias) {
		List<CategoriaEntity> categoriasMasculinas = new ArrayList<>();
		List<CategoriaEntity> categoriasFemeninas = new ArrayList<>();
		for (CategoriaEntity categoria : categorias) {
			if (categoria.getSexo().equals("HOMBRE")) {
				if (categoriasMasculinas.size() != 0) {
					if (categoriasMasculinas.get(categoriasMasculinas.size()).getEdadFinal() + 1 != categoria
							.getEdadInicio()) {
						categoriasMasculinas.add(categoria);
					} else {
						throw new IllegalArgumentException("Las categorias masculinas deben cubrir todo el rango de edades sin condlictos.");
					}
				} else {
					if(categoria.getEdadInicio() != 18) {
						throw new IllegalArgumentException("Las primera categoria masculina debe empezar en 18 a�os.");
					}
				}
			} else if (categoria.getSexo().equals("MUJER")) {
				if (categoriasFemeninas.size() != 0) {
					if (categoriasFemeninas.get(categoriasFemeninas.size()).getEdadFinal() + 1 != categoria
							.getEdadInicio()) {
						categoriasFemeninas.add(categoria);
					} else {
						throw new IllegalArgumentException("Las categorias femeninas deben cubrir todo el rango de edades sin conflictos");
					}
				} else {
					if(categoria.getEdadInicio() != 18) {
						throw new IllegalArgumentException("Las primera categoria femenina debe empezar en 18 a�os.");
					}
				}
			} else {
				throw new IllegalArgumentException("El sexo debe ser masculino o femenino.");
			}
		}
		
		if(categoriasMasculinas.get(categoriasMasculinas.size()).getEdadFinal() != Integer.MAX_VALUE) {
			throw new IllegalArgumentException("La ultima categoria masculina no debe tener limite de edad");
		}
		if(categoriasFemeninas.get(categoriasFemeninas.size()).getEdadFinal() != Integer.MAX_VALUE) {
			throw new IllegalArgumentException("La ultima categoria femenina no debe tener limite de edad");
		}
	}
	
	private void addCategorias(List<CategoriaEntity> categorias) {
		for (CategoriaEntity categoria : categorias) {
			cm.addCategoria(categoria);
		}
	}
	
	private List<CategoriaEntity> generateCategorias() {
		List<Component> components = Arrays.asList(view.getPanelCategorias().getComponents());
		List<CategoriaEntity> res = new ArrayList<>();
		for (Component component : components) {
			CategoriaEntity categoria = new CategoriaEntity();
			categoria.setIdCategoria(cm.getNextId());
			categoria.setIdCarrera(idCarrera);
			categoria.setNombre(((PanelCategoria)component).getNombre());
			categoria.setSexo(((PanelCategoria)component).getSexo());
			categoria.setEdadInicio(((PanelCategoria)component).getEdadInicial());
			categoria.setEdadFinal(((PanelCategoria)component).getEdadFinal());
			res.add(categoria);
		}
		Collections.sort(res, new Comparator<CategoriaEntity>() {
		    @Override
		    public int compare(CategoriaEntity lhs, CategoriaEntity rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return lhs.getEdadInicio() > rhs.getEdadInicio() ? 1 : (lhs.getEdadFinal() < rhs.getEdadFinal()) ? -1 : 0;
		    }
		});
		return res;
	}
}
