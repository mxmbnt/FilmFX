package org.maxime.filmFX.application;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Film {		
		private SimpleIntegerProperty iD;
		private SimpleStringProperty film;
		private SimpleStringProperty paysSorti;
		private SimpleStringProperty synopsi;
		private SimpleStringProperty fichier;
		private SimpleObjectProperty<ImageView> photo;
		private SimpleStringProperty recherche;
		private String nomImage;
		//private ImageView photo;
				

		//Constructeur
		public Film(int iD, String film, String paysSorti, String synopsi, String fichier, String photo) throws MalformedURLException {
			this.iD = new SimpleIntegerProperty(iD);
			this.film = new SimpleStringProperty(film);
			this.paysSorti = new SimpleStringProperty(paysSorti);
			this.synopsi = new SimpleStringProperty(synopsi);
			this.fichier = new SimpleStringProperty(fichier);
			//Image newphoto = new Image(getClass().getResourceAsStream("/Image/"+photo));
			System.out.println("Chemin :"+ Propriete.REP_IMAGE+ photo );
//			URL url = new URL( );
			File fichierUrl = new File(Propriete.REP_IMAGE+ photo);
			Image newphoto = new Image(fichierUrl.toURI().toURL().toString());
			this.photo = new SimpleObjectProperty<ImageView>(new ImageView(newphoto));
			this.nomImage = photo;
					
			//new ImageView(photo);
		}

		
		public Film() {
			// TODO Auto-generated constructor stub
		}
			
		
		
		// Getter et Setter FXML
		public final SimpleIntegerProperty iDProperty() {
			return this.iD;
		}
		

		public final int getID() {
			return this.iDProperty().get();
		}
		

		public final void setID(final int iD) {
			this.iDProperty().set(iD);
		}
		

		public final SimpleStringProperty filmProperty() {
			return this.film;
		}
		

		public final String getFilm() {
			return this.filmProperty().get();
		}
		

		public final void setFilm(final String film) {
			this.filmProperty().set(film);
		}
		

		public final SimpleStringProperty paysSortiProperty() {
			return this.paysSorti;
		}
		

		public final String getPaysSorti() {
			return this.paysSortiProperty().get();
		}
		

		public final void setPaysSorti(final String paysSorti) {
			this.paysSortiProperty().set(paysSorti);
		}
		

		public final SimpleStringProperty synopsiProperty() {
			return this.synopsi;
		}
		

		public final String getSynopsi() {
			return this.synopsiProperty().get();
		}
		

		public final void setSynopsi(final String synopsi) {
			this.synopsiProperty().set(synopsi);
		}
		

		public final SimpleStringProperty fichierProperty() {
			return this.fichier;
		}
		

		public final String getFichier() {
			return this.fichierProperty().get();
		}
		

		public final void setFichier(final String fichier) {
			this.fichierProperty().set(fichier);
		}
			
		
		public String getNomImage() {
			return nomImage;
		}


		public void setNomImage(String nomImage) {
			this.nomImage = nomImage;
		}
		

	public final SimpleObjectProperty<ImageView> photoProperty() {
		return this.photo;
	}
	


	public final ImageView getPhoto() {
		return this.photoProperty().get();
	}
	


	public final void setPhoto(final ImageView photo) {
		this.photoProperty().set(photo);
	}


	public final SimpleStringProperty rechercheProperty() {
		return this.recherche;
	}
	

}
		
		
		
		
		
	


