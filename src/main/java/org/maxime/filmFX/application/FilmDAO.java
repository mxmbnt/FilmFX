package org.maxime.filmFX.application;

import java.sql.Connection;
import java.util.List;

import util.DbConnexion;

public interface FilmDAO<T> {
	
	Connection connect = DbConnexion.getInstance();
	List<T> lister();
	void ajouter(Film film);
	void supprimer(Film film);
	void modifier(Film film);
	
	
	
	

}
