package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox mainMenuRoot = loader.load(new FileInputStream("src/application/MainMenuView.fxml"));
			MainMenuViewController MainMenucontroller = (MainMenuViewController) loader.getController();
			MainMenucontroller.applicationStage = primaryStage;
			Scene mainMenuView = new Scene(mainMenuRoot,400,400);
			primaryStage.setScene(mainMenuView);
			primaryStage.setTitle("Sleep Tracker");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
