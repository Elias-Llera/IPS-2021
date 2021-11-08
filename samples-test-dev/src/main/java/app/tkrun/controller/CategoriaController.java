package app.tkrun.controller;

import java.util.ArrayList;
import java.util.List;

import app.tkrun.entities.CategoriaEntity;

public class CategoriaController {

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
						throw new IllegalArgumentException("Las primera categoria masculina debe empezar en 18 años.");
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
						throw new IllegalArgumentException("Las primera categoria femenina debe empezar en 18 años.");
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
}
