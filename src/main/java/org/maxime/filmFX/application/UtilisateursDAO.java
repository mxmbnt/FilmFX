package org.maxime.filmFX.application;

import java.sql.Connection;

import util.DbConnexion;

public interface UtilisateursDAO<T>  {

	Connection connect = DbConnexion.getInstance();
	

	
	boolean connexion(Utilisateurs utilisateurs);
	
	void lbErreur(Utilisateurs utilisateurs);
	
}
