package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsController {
	Stage applicationStage;
	Scene settingsView;
    @FXML
    void switchGoalSettingView(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox goalSettingsRoot = loader.load(new FileInputStream("src/application/GoalSettingsView.fxml"));
			GoalSettingsController goalSettingsController = (GoalSettingsController) loader.getController();
			goalSettingsController.applicationStage = applicationStage;
			Scene goalSettingsView = new Scene(goalSettingsRoot,400,300);
			goalSettingsController.settingsView = settingsView;
			applicationStage.setScene(goalSettingsView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void SetMainSettingsScene(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    @FXML
    void switchPersonalInformationView(ActionEvent event) {

    }

}
