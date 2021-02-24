package org.maxime.filmFX.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propriete {
	public static String REP_IMAGE;
	public static String REP_VIDEO;
	public static String REP_DONNEE;
	
	public void chargeConfig() {
		String srcPath = System.getProperty("user.dir") + "\\conf\\config.properties";
		  Properties properties=new Properties();

	        //reading stuff

	            try {

	                try (FileInputStream in = new FileInputStream(srcPath)) {

	                    properties.load(in);

	                    properties.forEach((k, v) -> System.out.println(k + " = " + v));

	                }

	           } catch (IOException e) {

	                System.err.println("Impossible de charger le fichier de configuration.");
	           }
	
	            
	            REP_IMAGE =properties.getProperty("REP_IMAGE","defaultREP_IMAGE");
	            REP_VIDEO =properties.getProperty("REP_VIDEO","defaultREP_VIDEO");
	            REP_DONNEE =properties.getProperty("REP_DONNEE","defaultREP_DONNEE");
	}
}
	        

