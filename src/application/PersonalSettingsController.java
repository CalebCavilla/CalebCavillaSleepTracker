package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

/**
 * PersonalSettingsController is a standard controller class responsible for controlling the functionality of the 
 * PersonalSettings scene. 
*/
public class PersonalSettingsController extends SettingsController implements Initializable {;
	

    @FXML
    private ChoiceBox<String> genderChoice;
    
    @FXML
    private Spinner<Integer> ageSpinner;

    @FXML 
    private Spinner<Integer> heightSpinner;

    @FXML
    private Spinner<Integer> weightSpinner;

    /**
	* switches scene to settings.
	* @param event the event thrown when 'back' button is pressed
	*/
    @FXML
    void switchSettingsView(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    /**
	* confirms the personal information provided by the user and sets the variables in their user profile.
	* @param event the event thrown when 'set goals!' button is pressed
	*/
    @FXML
    void confirmPersonalInformation(ActionEvent event) {
    	
    	// set user variables
    	user.setGender(genderChoice.getValue());
    	user.setAge(ageSpinner.getValue());
    	user.setWeight(weightSpinner.getValue());
    	user.setHeight(heightSpinner.getValue());
    	
    
    	// switch scene back to settings
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    	
    }
    
    /**
   	* constructs the scene/GUI to be displayed to the user
   	*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// default value for the gender choice box
		genderChoice.setValue(genderChoice.getItems().get(2));
		
		// Spinner code was created with help from: https://www.youtube.com/watch?v=hSTEVJe4HSE
		
		// Age spinner setup
		SpinnerValueFactory<Integer> ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 125, 18);
		ageSpinner.setValueFactory(ageValueFactory);
		
		// Weight spinner setup
		SpinnerValueFactory<Integer> weightValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 228, 80);
		weightSpinner.setValueFactory(weightValueFactory);
		
		// Height spinner setup
		SpinnerValueFactory<Integer> heightValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 250, 170);
		heightSpinner.setValueFactory(heightValueFactory);
				
				
		
	}
}
