package org.maxime.filmFX.application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class LoginController implements Initializable{
	

	@FXML
    private Label lbErreur;
	
    @FXML
    private BorderPane bdPane;
    
    @FXML
    private VBox vbBackground;
		
	@FXML
    private TextField tfLogin;

    @FXML
    private PasswordField psField;

    @FXML
    private Button btConnection;
    
    @FXML
    private Button btFermer;
    
    @FXML
    private Button btRéduire;


    
    UtilisateursDAO<Utilisateurs> dao = new UtilisateursIMP();

    private double x = 0, y = 0;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initImage();
		
		psField.setOnAction(e->{
			
			connection(e);
		});
	}
	
	
	
	@FXML 
	public void connection(ActionEvent event){
		Utilisateurs login = new Utilisateurs(tfLogin.getText(), psField.getText());
		boolean resultat = dao.connexion(login);
		System.out.println("coucou");
		if (!resultat) {
			System.out.println("Login KO");
			lbErreur.setText("Erreur d'identifiant");
		}else {
			System.out.println("Login OK");
			showFilm();			
		}
	}

	private void showFilm() {
		// TODO Auto-generated method stub
		
        try {
        	FXMLLoader loader = new FXMLLoader(); 
            loader.setLocation(Main.class.getResource("/fxml/Film.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            
            pane.setOnMousePressed(e->{
            	x = e.getSceneX();
           	 	y = e.getSceneY();
            });
            
            pane.setOnMouseDragged(e->{
            	Main.stageView.setX(e.getScreenX());
            	Main.stageView.setY(e.getScreenY());
            });
            
            
            
        	Main.stageView.setScene(scene);
        	Main.stageView.centerOnScreen();
        	
        	
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
	
    @FXML
    void BoutonFermer(ActionEvent event) {
    	Main.stageView.close();
    }
    
 
    private void initImage() {
    // Image Fermer
    	
    	Image imgFermer = new Image(getClass().getResourceAsStream("/image/fermer.png"));	
 		btFermer.setGraphic(new ImageView(imgFermer));
 		btFermer.setText("");
 		
 		Tooltip tobtFermer = new Tooltip("Fermer");		 
 		tobtFermer.setFont(new Font("Algerian", 14));
 		btFermer.setTooltip(tobtFermer);
	
}
      
    
   
}
