package com.chromia.repository;

import java.util.List;

import com.chromia.model.Categoria;

public interface ICategoria {

	public void addCategoria(Categoria categoria);

	public void updateCategoria(Categoria categoria);

	public void deleteCategoria(Categoria categoria);

	public Categoria getCategoriaById(int id);

	public List<Categoria> getCategorias();

}
