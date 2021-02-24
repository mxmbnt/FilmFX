package org.maxime.filmFX.application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateursIMP implements UtilisateursDAO<Utilisateurs>{

	@Override
	public boolean connexion(Utilisateurs utilisateurs) {
		// TODO Auto-generated method stub
		boolean retour;
		retour = false;
		String sql = "SELECT ID " + 
				"FROM USERS WHERE LOGIN='" + utilisateurs.getLogin()+"'" + " AND PASSWORD = '" + utilisateurs.getPassword() + "'";

		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		
			while (rs.next()) {
				retour = true;
				System.out.println("dans le while");
			}
				
		} catch (SQLException e) {
			Logger.getLogger(UtilisateursIMP.class.getName()).log(Level.SEVERE, "Echec connexion programme", e);
			e.printStackTrace();
			
			
			
		} 
		return retour;
	}

	@Override
	public void lbErreur(Utilisateurs utilisateurs) {
		// TODO Auto-generated method stub
		
	}
	
	
	
		
}
