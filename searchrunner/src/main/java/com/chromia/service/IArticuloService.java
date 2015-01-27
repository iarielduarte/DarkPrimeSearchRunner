package com.chromia.service;

/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

import java.util.List;

import com.chromia.model.Articulo;


/**
 * @author Ariel Duarte
 * @since 29-Marzo-2014
 * @version 1.0.0
 */

public interface IArticuloService {
	public boolean addArticulo(Articulo articulo);
	public boolean updateArticulo(Articulo articulo);
	public boolean deleteArticulo(Articulo articulo);
	public Articulo getArticuloById(int id);
	public List<Articulo> getArticulos();
	public Integer getMaxId();
	public Articulo getArticuloByName(String name);
}
