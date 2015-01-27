package com.chromia.repository;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Articulo;

public interface IArticuloDao {

	public void addArticulo(Articulo articulo);
	public void updateArticulo(Articulo articulo);
	public void deleteArticulo(Articulo articulo);
	public Articulo getArticuloById(int id);
	public List<Articulo> getArticulos();
	public Integer getMaxId();
	public Articulo getArticuloByName(String name);
}
