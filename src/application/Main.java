package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Main class responsible for creating the user profile and launching the application
*/
public class Main extends Application {
	Scene mainMenuView;
	Stage applicationStage;
	static User user = new User();
	@Override
	public void start(Stage primaryStage) {
		try {
			// initialize the controller
			FXMLLoader loader = new FXMLLoader();
			VBox mainMenuRoot = loader.load(new FileInputStream("src/application/MainMenuView.fxml"));
			MainMenuViewController MainMenucontroller = (MainMenuViewController) loader.getController();
			// set instance variables
			MainMenucontroller.applicationStage = primaryStage;
			MainMenucontroller.user = user;
			mainMenuView = new Scene(mainMenuRoot,400,400);
			MainMenucontroller.mainMenuView = mainMenuView;
			// swtich the scene
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
