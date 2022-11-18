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
	Stage applicationStage;
    @FXML
    void switchGoalSettingView(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox goalSettingsRoot = loader.load(new FileInputStream("src/application/GoalSettingsView.fxml"));
			Scene goalSettingsView = new Scene(goalSettingsRoot,300,300);
			applicationStage.setScene(goalSettingsView);
			applicationStage.setTitle("Goal Settings");
			applicationStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void SetMainSettingsScene(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    }
    @FXML
    void switchPersonalInformationView(ActionEvent event) {

    }

}
