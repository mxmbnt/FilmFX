package org.maxime.filmFX.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	public static Stage stageView;
	public static  BorderPane mainLayout;
	private double x =0, y =0;
	
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
			 //System.out.println("Fichier: " + Main.class.getResource("Film.fxml"));
			 
			 FXMLLoader loader = new FXMLLoader(); 
             loader.setLocation(Main.class.getResource("/fxml/Login.fxml"));
             mainLayout = loader.load();
             
             
             mainLayout.setOnMousePressed(e -> {
            	 x = e.getSceneX();
            	 y = e.getSceneY();
             });
             
             
             mainLayout.setOnMouseDragged(e -> {
            	 stageView.setX(e.getScreenX());
            	 stageView.setY(e.getScreenY());
             });
             
             Scene scene = new Scene(mainLayout);
             //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
             primaryStage.setTitle("FILM FX");
             primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/logo-sm.png")));
             //primaryStage.setMinWidth(1000);
             //primaryStage.setMinHeight(800);        
             //stage.setResizable(false);
              
             
             primaryStage.initStyle(StageStyle.UNDECORATED);
             primaryStage.setScene(scene);
             primaryStage.centerOnScreen();
             primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
				 
	}
}
