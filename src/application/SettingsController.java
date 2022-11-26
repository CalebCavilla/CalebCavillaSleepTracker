package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsController extends MainMenuViewController{
	
    @FXML
    void switchGoalSettingView(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox goalSettingsRoot = loader.load(new FileInputStream("src/application/GoalSettingsView.fxml"));
			GoalSettingsController goalSettingsController = (GoalSettingsController) loader.getController();
			goalSettingsController.applicationStage = applicationStage;
			goalSettingsController.user = user;
			Scene goalSettingsView = new Scene(goalSettingsRoot,350,300);
			goalSettingsController.settingsView = settingsView;
			applicationStage.setScene(goalSettingsView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void switchMainMenuView() {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    @FXML
    void switchPersonalInformationView(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			VBox personalSettingsRoot = loader.load(new FileInputStream("src/application/PersonalSettingsView.fxml"));
			PersonalSettingsController personalSettingsController = (PersonalSettingsController) loader.getController();
			personalSettingsController.applicationStage = applicationStage;
			personalSettingsController.user = user;
			Scene personalSettingsView = new Scene(personalSettingsRoot,350,300);
			personalSettingsController.settingsView = settingsView;
			applicationStage.setScene(personalSettingsView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
