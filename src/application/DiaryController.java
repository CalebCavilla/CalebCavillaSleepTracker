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
    

    @FXML
    void decreaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(-1);
    	diaryDatePicker.setValue(selectedDate);
    	this.updateView();
    }

    @FXML
    void increaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(1);
    	diaryDatePicker.setValue(selectedDate);
    	this.updateView();
    }
    @FXML
    void setDate(ActionEvent event) {
    	selectedDate = diaryDatePicker.getValue();
    	this.updateView();
    }
    @FXML
    void switchMainMenuView() {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    @FXML 
    void addSleepPeriod() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			VBox addSleepPeriodRoot = loader.load(new FileInputStream("src/application/AddSleepPeriodView.fxml"));
			AddSleepPeriodController addSleepPeriodController = (AddSleepPeriodController) loader.getController();
			addSleepPeriodController.applicationStage = applicationStage;
			Scene addSleepPeriodView = new Scene(addSleepPeriodRoot,350,300);
			addSleepPeriodController.diaryView = diaryView;
			addSleepPeriodController.user = user;
			addSleepPeriodController.selectedDate = selectedDate;
			addSleepPeriodController.diaryController = this;
			applicationStage.setScene(addSleepPeriodView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void updateView() {
    	
    	diaryDatePicker.setValue(selectedDate);
    	sleepPeriodVbox.getChildren().clear();
    	hoursSoFarLabel.setText("0");
		MinutesSoFarLabel.setText("0");
		if (user.getGoalTotalSleep() != null) {
			timeGoalLabel.setText(user.getGoalTotalSleep().printDifferenceFormat());
			remainingTimeLabel.setText(user.getGoalTotalSleep().printDifferenceFormat());
		}else {
			timeGoalLabel.setText("No Goal Set");
			remainingTimeLabel.setText("No Goal Set");
		}
		
    	
    	for (Day i : user.getDiary()) {
			if (i.getDate().equals(selectedDate)) {
				
				// Tracking sleep Progress Code
				if (user.getGoalTotalSleep() != null){
					i.calculateTotalSleep();
					hoursSoFarLabel.setText(String.valueOf(i.getTotalSleep().getHours()));
					MinutesSoFarLabel.setText(String.valueOf(i.getTotalSleep().getMinutes()));
					
					Time sleepSoFar = new Time(i.getTotalSleep().getHours(), i.getTotalSleep().getMinutes(), "am");
					Time sleepGoal = new Time(user.getGoalTotalSleep().getHours(), user.getGoalTotalSleep().getMinutes(), "am");
					
					remainingTimeLabel.setText((sleepSoFar.difference(sleepGoal).printDifferenceFormat()));
				}
				// Sleep Periods Code
				Label sleepPeriodsTitle = new Label("Sleep Periods");
				sleepPeriodsTitle.setFont(Font.font("System",FontWeight.BOLD, 12));
				sleepPeriodsTitle.setPadding(new Insets(0,0,10,10));
				sleepPeriodVbox.getChildren().add(sleepPeriodsTitle);
				for (Sleep j : i.getSleepPeriods()) {
					VBox sleepPeriodVBoxes = new VBox();
					sleepPeriodVBoxes.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
					Label sleepType = new Label("Sleep Type: " + j.getType());
					sleepType.setPadding(new Insets(0,0,10,10));
					HBox sleepInfoHbox = new HBox();
					sleepInfoHbox.setPadding(new Insets(0,0,10,0));
					Label sleepTimes = new Label("Sleep Time: " + j.getStartTime().printTimeFormat() + " -- " + j.getEndTime().printTimeFormat());
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
