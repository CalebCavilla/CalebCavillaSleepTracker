package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GoalSettingsController {

	Stage applicationStage;
	Scene settingsView;
	User user;
	
	String targetBedTime;
	String targetAwakeTime;
	String targetSleep;
	
	boolean haveGoalsBeenSet = false;
	
	@FXML
	private Label calculatedSleepLabel;
	
    @FXML
    private Button setGoalsButton;
	
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
    	user.setGoalBedTime(targetBedTimeText.getText());
    	user.setGoalAwakeTime(targetAwakeTimeText.getText());
    	user.setGoalTotalSleep(updateSleepTime());
    	System.out.print(user.getGoalTotalSleep());
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    @FXML
    Time updateSleepTime() {
    	
    	String bedtime = targetBedTimeText.getText();
    	Time bedTime = new Time(Integer.parseInt(bedtime.substring(0, bedtime.indexOf(":"))), Integer.parseInt(bedtime.substring(bedtime.indexOf(":")+1, bedtime.indexOf(":")+3)), bedtime.substring(bedtime.length()-2));
    	String awaketime = targetAwakeTimeText.getText();
    	Time awakeTime = new Time(Integer.parseInt(awaketime.substring(0, awaketime.indexOf(":"))), Integer.parseInt(awaketime.substring(awaketime.indexOf(":")+1, awaketime.indexOf(":")+3)), awaketime.substring(awaketime.length()-2));
    	Time timeDifference = bedTime.difference(awakeTime);
    	calculatedSleepLabel.setText(timeDifference.toString());
    	return timeDifference;
    }
    	

}
