package org.maxime.filmFX.application;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FilmController<S> implements Initializable {

	//partie @FXML
	


	@FXML
	private TableView<Film> tabFilm;

    @FXML
    private TableColumn<Film, String> clpaysSorti;

    @FXML
    private TableColumn<Film, String> clsynopsi;

    @FXML
    private TableColumn<Film, Number> cliD;

    @FXML
    private TableColumn<Film, String> clFichier;
    
    @FXML
    private TableColumn<Film, String> clFilm;
    
    @FXML
    private TableColumn<Film, ImageView> clImage;
       
    @FXML
    private TextField tfFichier;

    @FXML
    private TextField tfpaysSorti;
    
    @FXML
    private TextField tfSynopsi;

    @FXML
    private TextField tfiD;

    @FXML
    private TextField tfFilm;
    
    @FXML
    private TextField tfCherche;
    
    @FXML
    private TextField tfFiltre;
    
    @FXML
    private Button btModif;
    
    @FXML
    private Button btNouveau;
    
    @FXML
    private Button btEditer;
    
    @FXML 
    private Button btAjout;
   
    @FXML
    private Button btSupp;
    
    @FXML
    private Button btCherche;
    
    @FXML
    private Button btPlay;
    
    @FXML
    private Button btStop;
    
    @FXML
    private Button btFermer;
    
    @FXML
    private Button btLancement;
    
    @FXML
    private MediaView mdPlayer;
    
    @FXML
    private Slider progressBar;
    
    private Media mVideo;
	private MediaPlayer mPlayer;
	private Image imgPause = new Image(getClass().getResourceAsStream("/image/pause.png"));;
	private ImageView imvPause = new ImageView(imgPause);
	private Image imgPlay = new Image(getClass().getResourceAsStream("/image/play.png"));;
	private ImageView imvPlay = new ImageView(imgPlay);
	private FilmDAO<Film> dao = new FilmIMP();
	private Duration duration = new Duration(0);
	
	static Stage dialogStage;
	
    ObservableList<Film> donnee = FXCollections.observableArrayList();
    
    FilteredList<Film> filterDonnee = new FilteredList<>(donnee);

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		System.out.println();
		System.out.println("Init");
		
		tfFiltre.textProperty().addListener((obsVal, oldValue, newValue) -> {
			filterDonnee.setPredicate(e -> e.getFilm().toUpperCase().contains(newValue.toUpperCase()));
        });
		
		chargeDonnee();
		

		
			
			clImage.setCellValueFactory(e->e.getValue().photoProperty());
		initImage();
		
		cliD.setCellValueFactory(e->e.getValue().iDProperty());
		clFilm.setCellValueFactory(e->e.getValue().filmProperty());
		clpaysSorti.setCellValueFactory(e->e.getValue().paysSortiProperty());
		clsynopsi.setCellValueFactory(e->e.getValue().synopsiProperty());
		clFichier.setCellValueFactory(e->e.getValue().fichierProperty());
		
		tabFilm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
			
			if(newValue != null) {
				tfiD.setText(Integer.toString(tabFilm.getSelectionModel().getSelectedItem().getID()));
				tfFilm.setText(tabFilm.getSelectionModel().getSelectedItem().getFilm());
				tfpaysSorti.setText(tabFilm.getSelectionModel().getSelectedItem().getPaysSorti());
				tfSynopsi.setText(tabFilm.getSelectionModel().getSelectedItem().getSynopsi());
				tfFichier.setText(tabFilm.getSelectionModel().getSelectedItem().getFichier());
				tfCherche.setText(tabFilm.getSelectionModel().getSelectedItem().getNomImage());
			}

			//			try {
//				loadVideo(tabFilm.getSelectionModel().getSelectedItem().getFichier());
//				
//			} catch (MalformedURLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
		});
		
		changeTexteField(true);
		
		btModif.setDisable(true);
		btAjout.setVisible(false);
		btCherche.setDisable(true);
		tabFilm.getSelectionModel().select(0);
		
		

	      
	}
	
	public void loadVideo(String video) throws MalformedURLException {
		String videoPlay = Propriete.REP_VIDEO+ "/"+video;
		File mediaFile = new File(videoPlay);
        mVideo = new Media(mediaFile.toURI().toURL().toString());
     
        mPlayer = new MediaPlayer(mVideo);
        mPlayer.setAutoPlay(false);
        mdPlayer.setMediaPlayer(mPlayer);

       
        
        duration = mPlayer.getMedia().getDuration();
        progressBar.setMaxWidth(duration.toMillis());
        progressBar.setMax(duration.toMinutes());
        progressBar.setValue(10);
                
	}
	
	
	@FXML
 	void supprimer(ActionEvent event) {
		
		Alert alerte = new Alert(AlertType.CONFIRMATION);
		alerte.setTitle("Suppressions");
		alerte.setHeaderText("Etes vous sur de vouloir supprimer le film "+ tabFilm.getSelectionModel().getSelectedItem().getFilm()+ "?");
	    
		
		Optional<ButtonType> result = alerte.showAndWait();
		if (result.get() == ButtonType.OK){
			
			dao.supprimer(tabFilm.getSelectionModel().getSelectedItem());
			tabFilm.getItems().remove(tabFilm.getSelectionModel().getSelectedItem());

		} 
			
		
	}
 
	
	private void chargeDonnee() {
//		// TODO Auto-generated method stub
		
		//Film film = new Film();
		//film.chargeFichier();
		System.out.println("juste la");
		donnee.addAll(dao.lister());
		//filterDonnee.setAll(donnee);
		
		tabFilm.setItems(filterDonnee);
		
	}
	
	
	 @FXML
	 private void modifier(ActionEvent event) {

			 if(isNumeric(tfiD.getText())){
				
				
				
				 
				tabFilm.getSelectionModel().getSelectedItem().setID(Integer.parseInt(tfiD.getText()));		 
				tabFilm.getSelectionModel().getSelectedItem().setFilm(tfFilm.getText());
				tabFilm.getSelectionModel().getSelectedItem().setPaysSorti(tfpaysSorti.getText());
				tabFilm.getSelectionModel().getSelectedItem().setSynopsi(tfSynopsi.getText());
				tabFilm.getSelectionModel().getSelectedItem().setFichier(tfFichier.getText());
				tabFilm.getSelectionModel().getSelectedItem().setNomImage(tfCherche.getText());;
				
				Film filmUpd = tabFilm.getSelectionModel().getSelectedItem();
				System.out.println("Image:" +filmUpd.getNomImage());
				
				dao.modifier(filmUpd);
				
			 	changeTexteField(true);
				btModif.setDisable(true);
				btEditer.setDisable(false);
				btNouveau.setDisable(false);
				btAjout.setVisible(false);
				btSupp.setDisable(false);
				btCherche.setDisable(false);
			 }
			 
		 
	 }

	 @FXML
	    void nouveau(ActionEvent event) {
		 changeTexteField(false);
			btModif.setDisable(true);
			btEditer.setDisable(true);
			btNouveau.setDisable(true);
			btAjout.setVisible(true);
			btSupp.setDisable(true);
			btCherche.setDisable(false);
			
						
			
			tfiD.setText(idMax().toString());
			tfiD.setDisable(true);
			tfFilm.setText("");	
			tfpaysSorti.setText("");	
			tfSynopsi.setText("");	
			tfFichier.setText("");	
			
			
			 
	    }    
	 

	 @FXML
	 void editer (ActionEvent event) {
		 changeTexteField(false);
			btModif.setDisable(false);
			btEditer.setDisable(true);
			btNouveau.setDisable(true);
			btAjout.setVisible(false);
			btSupp.setDisable(true);
			btCherche.setDisable(false);
			
			
	 }
	 
	 
	 @FXML
	 void ajouter (ActionEvent event) throws NumberFormatException, MalformedURLException {
		 if(isNumeric(tfiD.getText())) {
			 if (!tfFilm.getText().isEmpty()) {
				
				 Film newFilm = new Film(Integer.parseInt(tfiD.getText()), tfFilm.getText(), tfpaysSorti.getText(), tfSynopsi.getText(), tfFichier.getText(), tfCherche.getText());
				 donnee.add(newFilm);
				 dao.ajouter(newFilm);
				 tabFilm.refresh();
				 

				 
			 }else {
				 	tfiD.setText(Integer.toString(tabFilm.getSelectionModel().getSelectedItem().getID()));
					tfFilm.setText(tabFilm.getSelectionModel().getSelectedItem().getFilm());
					tfpaysSorti.setText(tabFilm.getSelectionModel().getSelectedItem().getPaysSorti());
					tfSynopsi.setText(tabFilm.getSelectionModel().getSelectedItem().getSynopsi());
					tfFichier.setText(tabFilm.getSelectionModel().getSelectedItem().getFichier());
					
			 }
			 
			 	changeTexteField(true);
			 	 btModif.setDisable(true);
				 btAjout.setVisible(false);
				 //btAjout.setDisable(true);
				 btNouveau.setDisable(false);
				 btEditer.setDisable(false);
				 btSupp.setDisable(false);
				 btCherche.setDisable(true);
		 }
		 		 
	 }
	 

    @FXML
    void chercher(ActionEvent event) {
    	FileDialog fdFilm = new FileDialog(new Frame(), "Choisir Image", FileDialog.LOAD);

         fdFilm.setDirectory(Propriete.REP_IMAGE);

         fdFilm.setFile("*.jpg");
         
         fdFilm.setVisible(true);

         String filename = fdFilm.getFile();
         
                  
         
         if (filename == null){
			tfCherche.setText("vide.jpg");
			System.out.println("je suis là");
			
		}else {
			
			tfCherche.setText(filename);
			System.out.println(fdFilm.getDirectory());
	        System.out.println("fichier : "+ filename);
			//copy image de la source vers destination 
	        File source = new File(fdFilm.getDirectory()+ filename);
	        
		    File destination = new File(Propriete.REP_IMAGE + filename);

		      
		      try {
		    	 
		    	  if(destination.exists()) {
		    		  
		    		  destination.delete();
		    	  }
				
		    	  Files.copy(source.toPath(), destination.toPath());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alerte = new Alert(AlertType.CONFIRMATION);
				alerte.setTitle("Erreur");
				alerte.setHeaderText("Erreur lors de la copie "+ tabFilm.getSelectionModel().getSelectedItem().getFilm()+ "?");
			    
				
				Optional<ButtonType> result = alerte.showAndWait();
				if (result.get() == ButtonType.OK){
					tabFilm.getItems().remove(tabFilm.getSelectionModel().getSelectedItem());
				} 	
			}
		      
//		      source.add("");
//		      destination.add("");
	        
	        
	        
			
		}

         
    }	 

	 

	private boolean isNumeric(String idFilm) {
		 try {

			 int a = Integer.parseInt(idFilm);
			 return true;
		 } catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
				System.out.println("Modif impossible ID doit est un valeur numérique");
				tfiD.setText("");
				return false;
		}
		
	 }
	 
	 private void changeTexteField(boolean in) {
		tfiD.setDisable(in);
		tfFilm.setDisable(in);
		tfpaysSorti.setDisable(in);
		tfSynopsi.setDisable(in);
		tfFichier.setDisable(in);
		tfCherche.setDisable(in);
	 }
	 
	 
	 private void initImage() {
		 
		 // Image Editer
		 Image imgEdit = new Image(getClass().getResourceAsStream("/image/gear.png"));		 
		 btEditer.setGraphic(new ImageView(imgEdit));
		 btEditer.setText("");
		 
		 Tooltip toBtEdit = new Tooltip("Editer");		 
		 toBtEdit.setFont(new Font("Algerian", 14));
		 btEditer.setTooltip(toBtEdit);
		 
		 // Image Nouveau
		 Image imgNew = new Image(getClass().getResourceAsStream("/image/plus.png"));		 
		 btNouveau.setGraphic(new ImageView(imgNew));
		 btNouveau.setText("");
		 
		 Tooltip toBtNew = new Tooltip("Ajouter");		 
		 toBtNew.setFont(new Font("Algerian", 14));
		 btNouveau.setTooltip(toBtNew);
		 
		 // Image Supprimer
		 Image imgSupp = new Image(getClass().getResourceAsStream("/image/delete.png"));		 
		 btSupp.setGraphic(new ImageView(imgSupp));
		 btSupp.setText("");
		 
		 Tooltip toBtSupp = new Tooltip("Supprimer");		 
		 toBtSupp.setFont(new Font("Algerian", 14));
		 btSupp.setTooltip(toBtSupp);
		 
		 // Image modifier
		 Image imgModif = new Image(getClass().getResourceAsStream("/image/edit.png"));		 
		 btModif.setGraphic(new ImageView(imgModif));
		 btModif.setText("");
		 
		 Tooltip toBtModif = new Tooltip("Modifier");		 
		 toBtModif.setFont(new Font("Algerian", 14));
		 btModif.setTooltip(toBtNew);
		 
		 // Image Ajouter 
		 Image imgAjout = new Image(getClass().getResourceAsStream("/image/add.png"));		 
		 btAjout.setGraphic(new ImageView(imgAjout));
		 btAjout.setText("");
		 
		 Tooltip toBtAjout = new Tooltip("Valider");		 
		 toBtSupp.setFont(new Font("Algerian", 14));
		 btModif.setTooltip(toBtAjout);
		
		 
		 
		// Image Lancer
		 Image imgLancement = new Image(getClass().getResourceAsStream("/image/lancer.png"));		 
		 btLancement.setGraphic(new ImageView(imgLancement));
		 btLancement.setText("");
		 
		 Tooltip toBtLancement = new Tooltip("Lancer");		 
		 toBtLancement.setFont(new Font("Algerian", 14));
		 btLancement.setTooltip(toBtLancement);	
		
		// Image Fermer
		 Image imgFermer = new Image(getClass().getResourceAsStream("/image/fermer.png"));		 
		 btFermer.setGraphic(new ImageView(imgFermer));
		 btFermer.setText("");
		 
		 Tooltip toBtFermer = new Tooltip("Fermer");		 
		 toBtFermer.setFont(new Font("Algerian", 14));
		 btFermer.setTooltip(toBtFermer);
		 
//		// Image Stop
//		 Image imgRestart = new Image(getClass().getResourceAsStream("/image/restart.png"));		 
//		 btStop.setGraphic(new ImageView(imgRestart));
//		 btStop.setText("");
//		 
//		 Tooltip toBtStop = new Tooltip("Stop");		 
//		 toBtStop.setFont(new Font("Algerian", 14));
//		 btStop.setTooltip(toBtStop);
	 }

	 
	 @FXML
	    void play(ActionEvent event) {
		
		 System.out.println("Status:" + mPlayer.getStatus());
		 if (!mPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))  {
			System.out.println("Play");
			 mPlayer.play();
			 btPlay.setGraphic(imvPause);
			 
		}else {
			mPlayer.pause();
			System.out.println("Pause");
			btPlay.setGraphic(imvPlay);
		}
		 
		 
	 }
	 	
		 	
		 	
	 
	 
	 @FXML
	    void stop(ActionEvent event) {
		 mPlayer.stop();
		 
	    }
			
	 
	 public Integer idMax() {
			Integer idMax = donnee
							.stream()
							.mapToInt(e->e.getID())
							.max()
							.orElseThrow(NoSuchElementException::new) + 1;
			return idMax;
	}

	 
	 @FXML
	 void lancement(ActionEvent event) throws IOException {
		    
		 		 
		 	FXMLLoader loader = new FXMLLoader();

	        loader.setLocation(getClass().getResource("/fxml/video.fxml"));

	        BorderPane page = (BorderPane) loader.load();

	        // Create the dialog Stage.

	        dialogStage = new Stage();

	        dialogStage.setTitle("Vidéo");

	        Scene scene = new Scene(page);

	        dialogStage.setScene(scene);

	        VideoController controller = loader.getController();

	        controller.setDialogStage(dialogStage);

			controller.loadVideo(tabFilm.getSelectionModel().getSelectedItem().getFichier());

	        dialogStage.showAndWait();
	                
	        
	 }
	 
	 public void taille() {
     	int v = mPlayer.getMedia().getWidth();
     	int h = mPlayer.getMedia().getHeight();
     	
     	
     }
	 
	
	 @FXML
	    void BoutonFermer(ActionEvent event) {
		 Main.stageView.close();
	    }
		 
	    
	 
}



