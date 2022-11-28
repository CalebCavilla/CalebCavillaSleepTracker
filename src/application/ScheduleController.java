package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.VBox;

public class ScheduleController extends MainMenuViewController implements Initializable{


    @FXML
    private VBox scheduleVBox;

    
    @FXML
    private Label weekRange;

    @FXML
    void followingWeek(ActionEvent event) {

    }

    @FXML
    void previousWeek(ActionEvent event) {

    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DatePicker scheduleDatePicker = new DatePicker(LocalDate.now());
		DatePickerSkin datePickerSkin = new DatePickerSkin(scheduleDatePicker);
		Node popupContent = datePickerSkin.getPopupContent();
		scheduleVBox.getChildren().add(0,popupContent);
		
	}

    
}
