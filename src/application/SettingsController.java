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
	
	
	/**
     * creates the goal settings controller and scene and switches to it
     * @param event the event thrown when 'Goals' button is pressed
    */
    @FXML
    void switchGoalSettingView(ActionEvent event) {
		try {
			// initialize the controller
			FXMLLoader loader = new FXMLLoader();
			VBox goalSettingsRoot = loader.load(new FileInputStream("src/application/GoalSettingsView.fxml"));
			GoalSettingsController goalSettingsController = (GoalSettingsController) loader.getController();
			
			// set instance variables and create the scene
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
    
    /**
     * switches the scene back to the mainMenuView
    */
    @FXML
    void switchMainMenuView() {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    /**
     * creates the personalInformation controller and scene and switches to it
     * @param event the event thrown when 'Personal Information' button is pressed
    */
    @FXML
    void switchPersonalInformationView(ActionEvent event) {
    	try {
    		// initialize the controller
			FXMLLoader loader = new FXMLLoader();
			VBox personalSettingsRoot = loader.load(new FileInputStream("src/application/PersonalSettingsView.fxml"));
			PersonalSettingsController personalSettingsController = (PersonalSettingsController) loader.getController();
			
			// set instance variables and create the scene
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
