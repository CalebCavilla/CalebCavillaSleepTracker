package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DiaryController extends MainMenuViewController implements Initializable {

	private LocalDate selectedDate = LocalDate.now();
	private Stage applicationStage;
	private Scene mainMenuView;
	private Scene diaryView;
	private User user;
	
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
    void decreaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(-1);
    	diaryDatePicker.setValue(selectedDate);
    }

    @FXML
    void increaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(1);
    	diaryDatePicker.setValue(selectedDate);
    }
    @FXML
    void setDate(ActionEvent event) {
    	selectedDate = diaryDatePicker.getValue();
    	System.out.println("Hello");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		diaryDatePicker.setValue(selectedDate);
	}
	
	
	
	
	
	
	
	
	
	
	
	// Getting and Setting methods
	
	public void setHoursSoFarLabel(String text) {
		hoursSoFarLabel.setText(text);
	}
	
	public String getHoursSoFarLabel() {
		return hoursSoFarLabel.getText();
	}
	
	public void setMinutesSoFarLabel(String text) {
		MinutesSoFarLabel.setText(text);
	}
	
	public String getMinutesSoFarLabel() {
		return MinutesSoFarLabel.getText();
	}
	
	public void setRemainingTimeLabel(String text) {
		remainingTimeLabel.setText(text);
	}
	
	public String getRemainingTimeLabel() {
		return remainingTimeLabel.getText();
	}
	
	public void setTimeGoalLabel(String text) {
		timeGoalLabel.setText(text);
	}
	
	public String getTimeGoalLabel() {
		return timeGoalLabel.getText();
	}

	public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}

	public Scene getMainMenuView() {
		return mainMenuView;
	}

	public void setMainMenuView(Scene mainMenuView) {
		this.mainMenuView = mainMenuView;
	}

	public Scene getDiaryView() {
		return diaryView;
	}

	public void setDiaryView(Scene diaryView) {
		this.diaryView = diaryView;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
