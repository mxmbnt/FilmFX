package org.maxime.filmFX.application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoController implements Initializable {

    @FXML
    private Button btStop;

    @FXML
    private Button btPlay;

    @FXML
    private Slider progressBar;

    @FXML
    private MediaView mdPlayer;

    private Media mVideo;
   	private MediaPlayer mPlayer;
	private Image imgPause = new Image(getClass().getResourceAsStream("/image/pause.png"));;
	private ImageView imvPause = new ImageView(imgPause);
	private Image imgPlay = new Image(getClass().getResourceAsStream("/image/play.png"));;
	private ImageView imvPlay = new ImageView(imgPlay);
	
	private Image imgStop = new Image(getClass().getResourceAsStream("/image/stop.png"));;
	private ImageView imvStop = new ImageView(imgStop);
	
	private Stage dialogStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		

		
		initImage();
		
//		mPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
//				// TODO Auto-generated method stub
//				progressBar.setValue(newValue.toSeconds());
//			}
//
//		});
//		
		progressBar.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				// TODO Auto-generated method stub
				if (progressBar.isValueChanging()) {
				       // multiply duration by percentage calculated by slider position
				       //mPlayer.seek(duration.multiply(progressBar.getValue() / 100.0));
				       mPlayer.seek(Duration.seconds(progressBar.getValue()));
				}
			}
		});
        
	 	progressBar.setOnMousePressed(new javafx.event.EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mPlayer.seek(Duration.seconds(progressBar.getValue()));
				//mPlayer.seek(duration.multiply(progressBar.getValue() / 100.0));
				
			}
		});
	 	
	 	progressBar.setOnMouseDragged(new javafx.event.EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mPlayer.seek(Duration.seconds(progressBar.getValue()));
				//mPlayer.seek(duration.multiply(progressBar.getValue() / 100.0));
				
			}
		});       
 
	 	
	}
	
	
	 
	 private void initImage() {
		// TODO Auto-generated method stub
		 btPlay.setGraphic(imvPlay);
		 btPlay.setText("");
		 
		 Tooltip toBtPlay = new Tooltip("Play/Pause");		 
		 toBtPlay.setFont(new Font("Algerian", 14));
		 btPlay.setTooltip(toBtPlay);
		 
		 //Init bouton STOP
		 btStop.setGraphic(imvStop);
		 btStop.setText("");
		 
		 Tooltip toBtStop = new Tooltip("Stop");		 
		 toBtStop.setFont(new Font("Algerian", 14));
		 btStop.setTooltip(toBtStop);
		 
		 
	}
	 
	public void loadVideo(String video) throws MalformedURLException {
		String videoPlay = Propriete.REP_VIDEO+ "/"+video;
		File mediaFile = new File(videoPlay);
        mVideo = new Media(mediaFile.toURI().toURL().toString());
        
        mPlayer = new MediaPlayer(mVideo);
        mPlayer.setAutoPlay(false);
        mdPlayer.setMediaPlayer(mPlayer);
        play(null);
        
        
        //duration = mPlayer.getMedia().getDuration();
        //progressBar.setMaxWidth(duration.toMillis());
        //progressBar.setMax(duration.toMinutes());
        //progressBar.setValue(10);
        
        
       
		
        
	}



	@FXML
	    void play(ActionEvent event) {


		
		 System.out.println("Status:" + mPlayer.getStatus());
		 if (!mPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))  {
			System.out.println("Play");
			 mPlayer.play();
			 btPlay.setGraphic(imvPause);
			 
			 mPlayer.setOnReady(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						 int v = mPlayer.getMedia().getWidth();
					        System.out.println("Taille v" + v);
					       
					     	int h = mPlayer.getMedia().getHeight();
					     	 System.out.println("Taille h" + h);
					     	dialogStage.setMinWidth(v);
					     	dialogStage.setMinHeight(h+80);
					     	dialogStage.centerOnScreen();
					}
				});
			 //mdPlayer.autosize();
			 //dialogStage.setFullScreen(true);
			 
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



	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage = dialogStage;
		dialogStage.setOnCloseRequest(e-> {
			mPlayer.stop();
	 		System.out.println("Fermeture");
	 	});
	}
			
	
}

