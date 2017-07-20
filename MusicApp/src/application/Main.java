package application;
	
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parameters parameters = getParameters();
			List<String> unnamed = parameters.getUnnamed();
			System.out.println("printing unnamed members");
			for (String unnamedmember : unnamed){
				System.out.println(unnamedmember);
			}
			System.out.println("printing named members");
			Map<String, String> named = parameters.getNamed();
			for(Map.Entry<String, String> entry : named.entrySet()){
				System.out.println(entry);
			}
			System.out.println("printing raw members");
			List<String> raw = parameters.getRaw();
			for (String rawmember : raw){
				System.out.println(rawmember);
			}
			
			URL url = getClass().getResource("MusicMainScene.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(url);
			AnchorPane pane = fxmlLoader.load();
			Scene scene = new Scene(pane);
			ProjectTransformations transformations = new ProjectTransformations(unnamed.get(1),"",""); 
			MusicMainController musicMainController = fxmlLoader.getController();
			musicMainController.setPath(unnamed.get(0));
			musicMainController.setTitle(unnamed.get(1));
			musicMainController.setTransformations(transformations);
			primaryStage.setScene(scene);
			primaryStage.setTitle(unnamed.get(1));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
