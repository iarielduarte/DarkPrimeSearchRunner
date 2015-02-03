package com.chromia.service;

import java.util.List;

import com.chromia.model.Categoria;

public interface ICategoriaService {

	public boolean addCategoria(Categoria categoria);

	public boolean updateCategoria(Categoria categoria);

	public boolean deleteCategoria(Categoria categoria);

	public Categoria getCategoriaById(int id);

	public List<Categoria> getCategorias();
}
