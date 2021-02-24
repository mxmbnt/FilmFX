package org.maxime.filmFX.application;
	

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainLogin extends Application {
	
	public static Stage stageView;
	@Override
	public void start(Stage primaryStage) {
		
		stageView = primaryStage;
		
		try {
			Propriete propriete = new Propriete();
			propriete.chargeConfig();
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
			
			//primaryStage.setScene(scene);
			//primaryStage.show();			
			 //System.out.println("Fichier: " + MainLogin.class.getResource("/fxml/Login.fxml"));
			 
			 FXMLLoader loader = new FXMLLoader(); 
             loader.setLocation(MainLogin.class.getResource("/fxml/Login.fxml"));
             Scene scene = new Scene(loader.load());
             //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
             primaryStage.setTitle("Login");
             //primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("application/logo-sm.png"))));
             //primaryStage.setMinWidth(1000);
             //primaryStage.setMinHeight(800);        
             //stage.setResizable(false);

             primaryStage.setScene(scene);
             primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
				 
	}
}
