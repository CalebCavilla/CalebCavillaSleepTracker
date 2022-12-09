package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * DiaryController is a standard controller class responsible for controlling the functionality of the 
 * Diary scene. 
*/
public class DiaryController extends MainMenuViewController {

	protected LocalDate selectedDate = LocalDate.now();
	
    @FXML
    private DatePicker diaryDatePicker;
    
    @FXML
    private Label hoursSoFarLabel;
    
    @FXML
    private Label MinutesSoFarLabel;

    @FXML
    private Label remainingTimeLabel;

    @FXML 
    private Label timeGoalLabel;
    
    @FXML
    private VBox sleepPeriodVbox;
    
    /**
     * decreases the day of the selected Date by one
     * @param event the event thrown when '<' button is pressed
    */
    @FXML
    void decreaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(-1);
    	diaryDatePicker.setValue(selectedDate);
    	this.updateView();
    }
    /**
     * increases the day of the selected Date by one
     * @param event the event thrown when '>' button is pressed
    */
    @FXML
    void increaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(1);
    	diaryDatePicker.setValue(selectedDate);
    	this.updateView();
    }
    /**
     * sets the date received by the diaryDatePicker
     * @param event the event thrown when date picker is interacted with
    */
    @FXML
    void setDate(ActionEvent event) {
    	selectedDate = diaryDatePicker.getValue();
    	this.updateView();
    }
    /**
     * switches the scene to the mainMenuView
     * @param event the event thrown when 'back' button is pressed
    */
    @FXML
    void switchMainMenuView() {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    /**
     * launches the addSleepPeriod controller and the addSleepPeriod scene for user input
     * @param event the event thrown when 'add sleep period' button is pressed
    */
    @FXML 
    void addSleepPeriod() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			VBox addSleepPeriodRoot = loader.load(new FileInputStream("src/application/AddSleepPeriodView.fxml"));
			AddSleepPeriodController addSleepPeriodController = (AddSleepPeriodController) loader.getController();
			Scene addSleepPeriodView = new Scene(addSleepPeriodRoot,350,300);
			// set instance variables
			addSleepPeriodController.applicationStage = applicationStage;
			addSleepPeriodController.diaryView = diaryView;
			addSleepPeriodController.user = user;
			addSleepPeriodController.selectedDate = selectedDate;
			addSleepPeriodController.diaryController = this;
			applicationStage.setScene(addSleepPeriodView);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * calculates sleep debt for the given day
     * @param day the day for sleep debt to be calculated and set
    */
    public void calculateSleepDebt(Day day) {
    	if (user.getGoalTotalSleep() != null){
			
			Time sleepSoFar = new Time(day.getTotalSleep().getHours(), day.getTotalSleep().getMinutes(), "am");
			Time sleepGoal = new Time(user.getGoalTotalSleep().getHours(), user.getGoalTotalSleep().getMinutes(), "am");
			
			
			Time sleepDebt = sleepSoFar.difference(sleepGoal);
			day.setSleepDebt(sleepDebt);
		}
    }
    
    /**
     * updates the GUI and controller 
    */
    public void updateView() {
    	
    	// default values for various nodes
    	diaryDatePicker.setValue(selectedDate);
    	sleepPeriodVbox.getChildren().clear();
    	hoursSoFarLabel.setText("0");
		MinutesSoFarLabel.setText("0");
		
		// if the user set a goal, set the goal nodes to the actual goal entered
		if (user.getGoalTotalSleep() != null) {
			timeGoalLabel.setText(user.getGoalTotalSleep().printDifferenceFormat());
			remainingTimeLabel.setText(user.getGoalTotalSleep().printDifferenceFormat());
		// otherwise set default text
		}else {
			timeGoalLabel.setText("No Goal Set");
			remainingTimeLabel.setText("No Goal Set");
		}
		
    	// loop through the days in the users diary to find a match for the selected date
    	for (Day i : user.getDiary()) {
			if (i.getDate().equals(selectedDate)) {
				
				// if the user set a goal, calculate the sleep debt for the day
				if (user.getGoalTotalSleep() != null){
					i.calculateTotalSleep();
					calculateSleepDebt(i);
					hoursSoFarLabel.setText(String.valueOf(i.getTotalSleep().getHours()));
					MinutesSoFarLabel.setText(String.valueOf(i.getTotalSleep().getMinutes()));
					remainingTimeLabel.setText(i.getSleepDebt().printDifferenceFormat());
				}
				// This block of code creates the little pop up box with all the information received in the add sleep period scene
				Label sleepPeriodsTitle = new Label("Sleep Periods");
				sleepPeriodsTitle.setFont(Font.font("System",FontWeight.BOLD, 12));
				sleepPeriodsTitle.setPadding(new Insets(0,0,10,10));
				sleepPeriodVbox.getChildren().add(sleepPeriodsTitle);
				for (Sleep j : i.getSleepPeriods()) {
					VBox sleepPeriodVBoxes = new VBox();
					// Created with help from: https://www.tabnine.com/code/java/methods/javafx.scene.layout.HBox/setBackground
					sleepPeriodVBoxes.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
					Label sleepType = new Label("Sleep Type: " + j.getType());
					sleepType.setPadding(new Insets(0,0,10,10));
					HBox sleepInfoHbox = new HBox();
					sleepInfoHbox.setPadding(new Insets(0,0,10,0));
					Label sleepTimes = new Label("Sleep Time: " + j.getStartTime().printTimeFormat(true) + " -- " + j.getEndTime().printTimeFormat(true));
					sleepTimes.setPadding(new Insets(0,0,0,10));
					Label sleepDuration = new Label("Duration: " + j.getDuration().printDifferenceFormat());
					sleepDuration.setPadding(new Insets(0,0,0,10));
					Label mood = new Label("Mood: " + j.getMood());
					mood.setPadding(new Insets(0,0,0,10));
					sleepInfoHbox.getChildren().addAll(sleepTimes, sleepDuration, mood);
					sleepPeriodVBoxes.getChildren().addAll(sleepType, sleepInfoHbox);
					sleepPeriodVbox.getChildren().addAll(sleepPeriodVBoxes);
					VBox.setMargin(sleepPeriodVBoxes, new Insets(0,0,10,0));
				}
			}
		}
    }
	

}
