package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.maxime.filmFX.application.Propriete;

public class DbConnexion {
	public static Connection connect;
	
	public static Connection getInstance() {
		
		if (connect == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				connect = DriverManager.getConnection(Propriete.REP_DONNEE);
				System.out.println("Connecté");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e+ "");
				
			}
		}
		System.out.println("ici");		
		return connect;
	}
}
