package org.maxime.filmFX.application;

import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;





public class FilmIMP implements FilmDAO<Film> {
	
	
	
	

	@Override
	public List<Film> lister() {
		List<Film> lstFilm = new ArrayList<Film>();
		
		String sql = "SELECT ID_FILM, TITRE, PAYS_SORTI, SYNOPSI, FICHIER, IMAGE\r\n" + 
					" FROM FILM ";
		System.out.println(sql);
		
		try {
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			
			Film film = new Film(rs.getInt("ID_FILM"), rs.getString("TITRE"), rs.getString("PAYS_SORTI"), rs.getString("SYNOPSI"), rs.getString("FICHIER"), rs.getString("IMAGE"));
			lstFilm.add(film);
		}
		
		} catch (SQLException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstFilm;
	}

	@Override
	public void ajouter(Film film) {
		// TODO Auto-generated method stub
		System.out.println("là");
		String sql = "INSERT INTO FILM(ID_FILM, TITRE, PAYS_SORTI, SYNOPSI, FICHIER, IMAGE) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = null;
			
		try {
			
			ps = connect.prepareStatement(sql);
			
			ps.setInt(1, film.getID());
			
			ps.setString(2, film.getFilm());
			ps.setString(3, film.getPaysSorti());
			ps.setString(4, film.getSynopsi());
			ps.setString(5, film.getFichier());
			ps.setString(6, film.getNomImage());
			System.out.println("je suis sql");
			ps.executeUpdate();
			System.out.println(sql);
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			Logger.getLogger(FilmIMP.class.getName()).log(Level.SEVERE, "Echec ajout film", e);
		} finally {
			  try {
				
				  	if(ps != null) {
				  		ps.close();
				  	}
				  
			  } catch (SQLException e2) {
				  Logger.getLogger(FilmIMP.class.getName()).log(Level.SEVERE, "Echec connection", e2);
			  }
		}
		
	}

	@Override
	public void supprimer(Film film) {
		// TODO Auto-generated method stub
		System.out.println("ici");
		String sql = "DELETE FROM FILM WHERE ID_FILM = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, film.getID());
			
			ps.executeUpdate();
			System.out.println(sql);
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			Logger.getLogger(FilmIMP.class.getName()).log(Level.SEVERE, "Echec suppréssion film", e);
			
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				
			} catch (SQLException e2) {
				Logger.getLogger(FilmIMP.class.getName()).log(Level.SEVERE, "Echec connection 2", e2);
			}
		}
		
		
	}

	@Override
	public void modifier(Film film) {
		// TODO Auto-generated method stub
		System.out.println("ici");
		String sql = "UPDATE FILM SET TITRE = ?, PAYS_SORTI = ?, SYNOPSI = ?, FICHIER = ?, IMAGE = ? WHERE ID_FILM = ?";
		
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			
			ps.setString(1, film.getFilm());
			ps.setString(2, film.getPaysSorti());
			ps.setString(3, film.getSynopsi());
			ps.setString(4, film.getFichier());
			ps.setString(5, film.getNomImage());
			ps.setInt(6, film.getID());
			ps.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			Logger.getLogger(FilmIMP.class.getName()).log(Level.SEVERE, "Echec de maj", e);
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				
			} catch (SQLException e2) {
				Logger.getLogger(FilmIMP.class.getName()).log(Level.SEVERE, "Echec connection 3", e2);
			}
		}
		
	}

	
}	
