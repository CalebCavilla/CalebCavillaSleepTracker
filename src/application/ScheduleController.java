package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * ScheduleController is a standard controller class responsible for controlling the functionality of the 
 * Schedule scene. 
*/
public class ScheduleController extends MainMenuViewController implements Initializable{

	private DatePicker scheduleDatePicker = new DatePicker(LocalDate.now());

	private Week selectedWeek;
	
	private LocalDate selectedDate = scheduleDatePicker.getValue();
	
    @FXML
    private VBox scheduleVBox;

    @FXML
    private GridPane gridPane;
    
    @FXML
    private TextArea aboutTextArea;
    
    @FXML
    private Label weekRange;
    
    @FXML
    private ImageView blueBoxImage;

    @FXML
    private ImageView greenBoxImage;
    
    @FXML
    private ImageView pinkBoxImage;
    
    @FXML
    private ImageView lightPinkBoxImage;
    
    @FXML
    private ImageView warningImage;
    
    @FXML
    private Label sundayLabel;

    @FXML
    private Label mondayLabel;

    @FXML
    private Label tuesdayLabel;
    
    @FXML
    private Label wednesdayLabel;
    
    @FXML
    private Label thursdayLabel;
    
    @FXML
    private Label fridayLabel;
    
    @FXML
    private Label saturdayLabel;
    
    /**
     * Creates and sets a new week based on the date given in the date picker
    */
    void calculateWeek() {
    	selectedWeek = new Week(scheduleDatePicker.getValue());
    }
    
    /**
	* increases the current selectedDate by 7 days (1 week). Updates the scene.
	* @param event the event thrown when '>' button is pressed
	*/
    @FXML
    void followingWeek(ActionEvent event) {
    	selectedDate = selectedDate.plusWeeks(1);
    	update();
    }

    /**
	* decreases the current selectedDate by 7 days (1 week). Updates the scene.
	* @param event the event thrown when '<' button is pressed
	*/
    @FXML
    void previousWeek(ActionEvent event) {
    	selectedDate = selectedDate.minusWeeks(1);
    	update();
    }
    
    /**
	* sets the current selectedDate to the value of the datePicker. Updates the scene.
	* @param event the event thrown when the datePicker is interacted with
	*/
    void setWeek() {
    	selectedDate = scheduleDatePicker.getValue();
    	update();
    }
    
    /**
	* switches the scene to mainMenuView.
	* @param event the event thrown when the 'back' button is pressed
	*/
    @FXML
    void switchMainMenuView(ActionEvent event) {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    /**
	* generates a button to be inserted into the schedule grid to act as a time block in the schedule.
	* @param sleep the Sleep period to converted into a button and inserted
	* @param isAffectedByDebt boolean that indicates if the button should account for sleepDebt
	* @param column the integer index of the column to be inserted into
	*/
    public void generateButton(Sleep sleep, boolean isAffectedByDebt, int column) {
    	
    	// initialize local variables
    	Time bedTime = new Time(sleep.getStartTime());
    	Time awakeTime = new Time(sleep.getEndTime());
    	int bedRowIndex = 0;
    	int awakeRowIndex = 0;
    	Button goalSleepButton = new Button("Somthin broke");
    	int latestDay = 0;
    	Time sleepDebt;
    	// get the latest day entered into the users diary
    	for (Day i : selectedWeek.getDaysOfWeek()) {
    		for (Day j : user.getDiary()) {
    			// If the day of the week has an entry in the users diary
    			if (i.getDate().equals(j.getDate())){
    				latestDay = selectedWeek.getDaysOfWeek().indexOf(i)+1;
    			}
    		}
    	}
    	// get sleep debt for the week
    	if (isAffectedByDebt) {
    		sleepDebt = selectedWeek.calculateSleepDebt(user);
    	} else {
    		sleepDebt = new Time(0,0);
    	}
    	// divide the sleep debt of the whole week into the sleep debt that needs to be added onto each remaining day in the schedule
    	int sleepDebtInMinutes = sleepDebt.getHours() * 60 + sleepDebt.getMinutes();
    	int sleepDebtPerDayInMinutes = sleepDebtInMinutes / (7-latestDay);
    	Time sleepDebtPerDay = new Time(sleepDebtPerDayInMinutes/ 60, sleepDebtPerDayInMinutes % 60);
    	
    	
    	// Fix times to be in 24 hour format
    	if (bedTime.getPeriod().equals("pm") && bedTime.getHours() != 12){
    		bedTime.setHours(bedTime.getHours()+12);
    	} else if (bedTime.getPeriod().equals("am") && bedTime.getHours() == 12) {
    		bedTime.setHours(bedTime.getHours()+12);
    	}
    	if (awakeTime.getPeriod().equals("pm") && awakeTime.getHours() != 12){
    		awakeTime.setHours(awakeTime.getHours()+12);
    	} else if (awakeTime.getPeriod().equals("am") && awakeTime.getHours() == 12) {
    		awakeTime.setHours(awakeTime.getHours()+12);
    	}
    	
    	// Calculate bed time Row Index
    	if (bedTime.getHours() >= 12) {
    		bedRowIndex = bedTime.getHours() - 11;
    	} else {
    		bedRowIndex = bedTime.getHours() + 13;
    	}
    	
    	// Calculate awake time Row Index
    	if (awakeTime.getHours() >= 12) {
    		awakeRowIndex = awakeTime.getHours() - 11;
    	} else {
    		awakeRowIndex = awakeTime.getHours() + 13;
    	}
    	
    	// We need to do different things depending on if we are after or before the latest day in the users diary
    	// This is where we account for sleep debt if the day is after the latest in the diary.
    	if (column > latestDay) {
    		bedTime.setHours(bedTime.getHours()-sleepDebtPerDay.getHours());
    		bedTime.setMinutes(bedTime.getMinutes()-sleepDebtPerDay.getMinutes());
    		// If sleep is negative that means we went back at least one hour, so we need to adjust hours and minutes so they are not negative
    		if (bedTime.getMinutes() < 0) {
    			bedRowIndex -= sleepDebtPerDay.getHours()+1;
    			bedTime.setHours(bedTime.getHours()-Math.abs(bedTime.getMinutes() /60 + 1));
    			bedTime.setMinutes(bedTime.getMinutes()+(60 * Math.abs(bedTime.getMinutes() /60 + 1)));
    		} else {
    			bedRowIndex -= sleepDebtPerDay.getHours();
    		}
    		
    		goalSleepButton = new Button(bedTime.printTimeFormat(false) + " - \n" + awakeTime.printTimeFormat(false));
    	// sleep debt does not apply if we are before or equal to the latest day in the diary
    	} else if (column <= latestDay) {
    		goalSleepButton = new Button(bedTime.printTimeFormat(false) + " - \n" + awakeTime.printTimeFormat(false));
    	}
    	
    	// margin to account for bed time minutes
    	GridPane.setMargin(goalSleepButton, new Insets(28*((bedTime.getMinutes())/60.0),0,0,0));
    	// set the height of the button based on the awake time hours and minutes,                  					  Here we account for the bedtime margin. 		
    	goalSleepButton.setMinHeight(Math.abs(awakeRowIndex - bedRowIndex) * 28 + ((28*(awakeTime.getMinutes()/60.0))-(28*(bedTime.getMinutes()/60.0))));
    	
    	// set style to the button based
    	GridPane.setValignment(goalSleepButton, VPos.TOP);
    	GridPane.setHalignment(goalSleepButton, HPos.LEFT);
    	goalSleepButton.setMinWidth(90);
    	
    	// different colors depending on the type of sleep
    	if (sleep.getType().equals("Main Sleep")){
    		goalSleepButton.setStyle("-fx-background-color: #0080FF;");
    	} else if (sleep.getType().equals("Nap")) {
    		goalSleepButton.setStyle("-fx-background-color: #00FFBB;");
    	} else if (sleep.getType().equals("recommended")) {
    		goalSleepButton.setStyle("-fx-background-color: #FF98DD;");
    	}
    	// add button to the schedule
    	gridPane.add(goalSleepButton, column, bedRowIndex);   	
    }
    
    /**
	* Creates the schedule to be displayed in the GUI
	*/
    public void generateSchedule() {
    	int matchingDayFlag = 1;
    	
    	// loop through all the days of the week and check if each one matches a day in the users diary
    	for (Day i : selectedWeek.getDaysOfWeek()) {
    		for (Day j : user.getDiary()) {
    			// if we have a match...
    			if (i.getDate().equals(j.getDate())){
    				// note the index of the day we found the match on
    				matchingDayFlag = selectedWeek.getDaysOfWeek().indexOf(i)+2;
    				// then for each sleep period on the matching day draw a button
    				for (Sleep sleepPeriod : j.getSleepPeriods()) {
    					generateButton(sleepPeriod, false, selectedWeek.getDaysOfWeek().indexOf(i)+1);
    				}
    			}
    		}
    	}
    	
    	// if the user has set a goal then for each day after the latest day in the diary, create a recommended button for the schedule
    	if (user.getGoalTotalSleep() != null) {
    		for (int x = matchingDayFlag; x < 8; x++) {
    			generateButton(new Sleep("recommended", user.getGoalBedTime(), user.getGoalAwakeTime(), "neutral"), true, x);
        	}
    	}
    }
    
    /**
	* refreshes the scene and the controller
	*/
    public void update() {
    	
    	// calculate the given week, and set the labels on the grid to display the correct dates
    	calculateWeek();	
		weekRange.setText(selectedWeek.toString());
		scheduleDatePicker.setValue(selectedDate);
    	sundayLabel.setText("Sunday " + selectedWeek.getDaysOfWeek().get(0).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(0).getDate().getDayOfMonth());
    	mondayLabel.setText("Monday " + selectedWeek.getDaysOfWeek().get(1).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(1).getDate().getDayOfMonth());
    	tuesdayLabel.setText("Tueday " + selectedWeek.getDaysOfWeek().get(2).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(2).getDate().getDayOfMonth());
    	wednesdayLabel.setText("Wednesday " + selectedWeek.getDaysOfWeek().get(3).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(3).getDate().getDayOfMonth());
    	thursdayLabel.setText("Thursday " + selectedWeek.getDaysOfWeek().get(4).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(4).getDate().getDayOfMonth());
    	fridayLabel.setText("Friday " + selectedWeek.getDaysOfWeek().get(5).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(5).getDate().getDayOfMonth());
    	saturdayLabel.setText("Saturday " + selectedWeek.getDaysOfWeek().get(6).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(6).getDate().getDayOfMonth());
    	
    	// for each node in the grid
    	for (Node node : gridPane.getChildren()) {
    		// if the node is a VBox
    		if (node instanceof VBox) {
    			node.setStyle(null);
    			node.setStyle("-fx-border-color: #E8E8E8;");
    			int dayofWeekValue = 0;
    			
    			/* getDayOfWeek.getValue() gives Monday a value of 1 and Sunday a value of 7, since my project considers Sunday #1
    			* we have change the day of the week value to fit our system a bit
    			*/
    			if (selectedDate.getDayOfWeek().getValue() == 7) {
    				dayofWeekValue = 1;
    			} else {
    				dayofWeekValue = selectedDate.getDayOfWeek().getValue()+1;
    			}
    			
    			// if the gridPane column index of the selected node is the same as the value for the current day of the week, then that must be the selected day, HIGHLIGHT IT!
    			if (GridPane.getColumnIndex(node) == dayofWeekValue) {
        			node.setStyle("-fx-background-color: #e6fcff; -fx-border-color: #E8E8E8;");
        			// How to send a node to the front of a scene: https://stackoverflow.com/questions/51071095/javafx-css-move-node-to-front
        			node.setViewOrder(1);
        		}
    		} 
    		// in order to clear the current schedule when a new week is selected, we can just hide the old buttons rather than deleting them
    		if (node instanceof Button) {
    			node.setOpacity(0);
    		}
    	}
    	// generate the schedule
    	generateSchedule();
    	
    	// Code for fixing blur in about text area, fixed using help from: https://stackoverflow.com/questions/23728517/blurred-text-in-javafx-textarea
    	aboutTextArea.setCache(false);
		ScrollPane sp = (ScrollPane) aboutTextArea.getChildrenUnmodifiable().get(0);
		sp.setCache(false);
		for (Node n : sp.getChildrenUnmodifiable()) {
		    n.setCache(false);
		}
		
		
		// code for sleep debt warning image
		warningImage.setImage(null);
		// if there is no debt pass
		if (selectedWeek.calculateSleepDebt(user).getHours() == 0 && selectedWeek.calculateSleepDebt(user).getMinutes() == 0) {
			; // pass
		} else {
			// if there is debt, create the warning image and give it a tool tip explaining the warning
			try {
				// Blue Box
				FileInputStream warningInputStream = new FileInputStream("src/warning.png");
				Image warning = new Image(warningInputStream);
				warningImage.setImage(warning);
				
				Button x = new Button();
				Tooltip.install(warningImage, new Tooltip("Total sleep debt accounted for: " + selectedWeek.calculateSleepDebt(user).printDifferenceFormat()+"\n" + "Recommended bed times adjusted to catch up on sleep"));
	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
    }
    /**
   	* constructs the scene/GUI to be displayed to the user for the first time
   	*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//
		scheduleDatePicker.setOnAction(event -> setWeek());
		
		// code for displaying the date picker. Help from: https://stackoverflow.com/questions/34681975/javafx-extract-calendar-popup-from-datepicker-only-show-popup
		DatePickerSkin datePickerSkin = new DatePickerSkin(scheduleDatePicker);
		Node popupContent = datePickerSkin.getPopupContent();
		LocalDate x = scheduleDatePicker.getValue();
		scheduleVBox.getChildren().add(0,popupContent);
		
		// adding the actual grid pattern to the grid pane 
		for (int i = 0; i < gridPane.getColumnCount(); i++) {
			for (int j = 0; j < gridPane.getRowCount(); j++) {
				VBox vbox = new VBox();
				// created with help from: https://stackoverflow.com/questions/20598778/drawing-a-border-around-a-javafx-text-node
				vbox.setStyle("-fx-border-color: #E8E8E8;");
				gridPane.add(vbox, i, j);
			}
		}
		
		// get the current week and set the week label
		calculateWeek();	
		weekRange.setText(selectedWeek.toString());
		
		// set up the images for the legend
		// got help for using images in javaFX from: https://www.tutorialspoint.com/javafx/javafx_images.htm
		try {
			// Blue Box
			FileInputStream blueBoxInputStream = new FileInputStream("src/bluebox.jpg");
			Image bluebox = new Image(blueBoxInputStream);
			blueBoxImage.setImage(bluebox);
			
			// Green Box
			FileInputStream greenBoxInputStream = new FileInputStream("src/greenbox.jpg");
			Image greenbox = new Image(greenBoxInputStream);
			greenBoxImage.setImage(greenbox);
			
			// Pink Box
				FileInputStream pinkBoxInputStream = new FileInputStream("src/pinkbox.jpg");
				Image pinkbox = new Image(pinkBoxInputStream);
				pinkBoxImage.setImage(pinkbox);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

    
}
