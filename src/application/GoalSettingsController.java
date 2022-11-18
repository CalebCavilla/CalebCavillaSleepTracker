package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GoalSettingsController {

	Stage applicationStage;
	Scene settingsView;
	
	String targetBedTime;
	String targetAwakeTime;
	double targetSleep;
	
	
    @FXML
    private TextField targetBedTimeText;

    @FXML
    private Button goalSettingsBack;

    @FXML
    private TextField targetSleepText;

    @FXML
    private TextField targetAwakeTimeText;

    @FXML
    void switchSettingsView(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    @FXML
    void setGoals(ActionEvent event) {
    	
    	targetBedTime = targetBedTimeText.getText();
    	targetAwakeTime = targetAwakeTimeText.getText();
    	targetSleep = Double.parseDouble(targetAwakeTimeText.getText());
    	
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    void calculateSleepTime() {
    	
    	// Variables for the goal sleep time
    	String bedTime = targetBedTimeText.getText();
    	int bedTimeHours = Integer.parseInt(bedTime.substring(0, bedTime.indexOf(":")));
    	int bedTimeMinutes = Integer.parseInt(bedTime.substring(bedTime.indexOf(":")+1));
    	
    	// Variable for the goal awake time
    	String awakeTime = targetAwakeTimeText.getText();
    	int awakeTimeHours = Integer.parseInt(awakeTime.substring(0, awakeTime.indexOf(":")));
    	int awakeTimeMinutes = Integer.parseInt(awakeTime.substring(awakeTime.indexOf(":")+1));
    		

    	
    	while (true){
    		if (bedTime.contains("am") && awakeTime.contains("am")){
    			if (bedTimeMinutes > awakeTimeMinutes) {
    				
    			}
    		}
    	}
    }
    
    

}
