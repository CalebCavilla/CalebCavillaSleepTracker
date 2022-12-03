package application;


import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import java.util.Calendar;
import java.util.Date;


public class MainMenuViewController extends Main {
	
	Scene settingsView;
	Scene diaryView;
	Scene dashboardView;
	Scene scheduleView;
	
    @FXML
    private Button dashBoardButton;

    @FXML
    private Button diaryButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button settingsButton;

    @FXML
    void switchDashboardView(ActionEvent event) {
		dashboardView = new Scene(new Label("Dashboard"));
		applicationStage.setScene(dashboardView);
		applicationStage.setTitle("Dashboard");
		applicationStage.show();
    }

    @FXML
    void switchDiaryView(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			VBox diaryRoot = loader.load(new FileInputStream("src/application/DiaryView.fxml"));
			DiaryController diaryController = (DiaryController) loader.getController();
			
			// set instance variables
			diaryController.applicationStage = applicationStage;
			diaryController.mainMenuView = mainMenuView;
			diaryController.user = user;
			
			// create the scene
			diaryView = new Scene(diaryRoot,550,400);
			diaryController.diaryView = diaryView;
			diaryController.updateView();
			applicationStage.setScene(diaryView);
			applicationStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    @FXML
    void switchScheduleView(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			BorderPane scheduleRoot = loader.load(new FileInputStream("src/application/ScheduleView.fxml"));
			ScheduleController scheduleController = (ScheduleController) loader.getController();
			scheduleController.applicationStage = applicationStage;
			scheduleView = new Scene(scheduleRoot,1100,750);
			
			
			scheduleController.settingsView = settingsView;
			scheduleController.mainMenuView = mainMenuView;
			scheduleController.user = user;
			applicationStage.setScene(scheduleView);
			applicationStage.setTitle("Schedule");
			scheduleController.update();
			scheduleController.generateGoalSchedule();
			scheduleController.generateDiarySchedule();
			applicationStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	

    @FXML
    void switchSettingsView(ActionEvent event) {
		try {
			System.out.println(user);
			FXMLLoader loader = new FXMLLoader();
			VBox settingsRoot = loader.load(new FileInputStream("src/application/SettingsView.fxml"));
			SettingsController settingsController = (SettingsController) loader.getController();
			settingsController.applicationStage = applicationStage;
			settingsView = new Scene(settingsRoot,250,250);
			settingsController.settingsView = settingsView;
			settingsController.mainMenuView = mainMenuView;
			settingsController.user = user;
			applicationStage.setScene(settingsView);
			applicationStage.setTitle("Settings");
			applicationStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
