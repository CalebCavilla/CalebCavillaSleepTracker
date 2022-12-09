package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
/**
 * DashboardController is a standard controller class responsible for controlling the functionality of the 
 * Dashboard scene. 
*/
public class DashboardController extends MainMenuViewController implements Initializable{

		@FXML
	    private Label awakeTimeLabel;

	    @FXML
	    private Label bedTimeLabel;

	    @FXML
	    private HBox chartHBox;

	    @FXML
	    private DatePicker datePicker;

	    @FXML
	    private Label deepSleepLabel;

	    @FXML
	    private VBox detailsVBox;

	    @FXML
	    private VBox detailsVBox1;

	    @FXML
	    private VBox doughnutVBox;

	    @FXML
	    private Label durationLabel;

	    @FXML
	    private Label goalLabel;

	    @FXML
	    private ImageView goalSymbol;

	    @FXML
	    private GridPane gridPane;

	    @FXML
	    private Label hoursSleptLabel;

	    @FXML
	    private ImageView hoursSleptSymbol;

	    @FXML
	    private Label lightSleepLabel;

	    @FXML
	    private LineChart<?, ?> lineChart;

	    @FXML
	    private Label qualityLabel;

	    @FXML
	    private Label sleepDebtLabel;

	    @FXML
	    private ImageView sleepDebtSymbol;

	    @FXML
	    private VBox sleepVBox;

	    @FXML
	    private CategoryAxis xAxis;

	    @FXML
	    private NumberAxis yAxis;

    private LocalDate selectedDate = LocalDate.now();
    private Week selectedWeek = new Week(LocalDate.now());
    
    /**
	* switches scene to mainMenuView.
	* @param event the event thrown when 'confirm sleep' button is pressed
	*/
    @FXML
    void switchMainMenuView(ActionEvent event) {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    /**
	* Calls update method. Must use middle man method as update() is not an FXML tagged method 
	* so it will not communicate with the FXML file 
	* @param event the event thrown when 'confirm sleep' button is pressed
	*/
    @FXML
    void dateUpdater(ActionEvent event) {
    	update();
    }
    

    /**
	* creates data to be used in a sleep vs. debt pie chart.
	* @param sleep the time representing the total sleep time to be graphed
	* @param debt the time representing the sleep debt to be graphed
	* @return the obeservableList of pie chart data to be used in displaying a sleep vs. debt pie chart
	*/
    private ObservableList<PieChart.Data> createData(Time sleep, Time debt) {
        return FXCollections.observableArrayList(
                new PieChart.Data("Sleep", sleep.getHours()*60 + sleep.getMinutes()),
                new PieChart.Data("Debt", debt.getHours()*60 + debt.getMinutes()));
    }
    
    /**
   	* creates, constructs, and displays the doughnutChart of sleep vs. Debt
   	* and the corresponding legend and labels
   	*/
    public void doughnutChartSetup() {

    	// clear the doughnut chart for a clean slate to work from
    	// set default values for the chart if no goals/diary was entered
    	doughnutVBox.getChildren().clear();
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("No sleep/goal set for today", 1));
    	
		String goal = "no goal set for today";
		String sleep = "no sleep set for today";
		String debt = "no sleep set for toaday";
		
		// if the user entered a goal, create the pie chart data and set the variables for the legend
		if (user.getGoalTotalSleep() != null) {
			goal = "Goal: " + user.getGoalTotalSleep().printDifferenceFormat();
			pieChartData = FXCollections.observableArrayList(
	                new PieChart.Data("Sleep Debt", 1));
			
			for (Day i : user.getDiary()) {
				if (i.getDate().equals(selectedDate)) {
					pieChartData = createData(i.getTotalSleep(), i.getSleepDebt());
					sleep = "Sleep: " + i.getTotalSleep().printDifferenceFormat();
					debt = "Sleep Debt: " + i.getSleepDebt().printDifferenceFormat();
				}
			}
		}
		
		// create the legend and the doughnut chart
		goalLabel.setText(goal);
		hoursSleptLabel.setText(sleep);
		sleepDebtLabel.setText(debt);
        final DoughnutChart chart = new DoughnutChart(pieChartData);
        chart.setLabelsVisible(true);
        
        // set colors for doughnut chart based on if user has entered a goal or not
        // created with help from: https://stackoverflow.com/questions/15219334/javafx-change-piechart-color
        if (pieChartData.size() > 1){
        	 pieChartData.get(0).getNode().setStyle("-fx-pie-color: #0080ff");
             pieChartData.get(1).getNode().setStyle("-fx-pie-color: orange");
        } else {
        	if (user.getGoalTotalSleep() != null) {
        		pieChartData.get(0).getNode().setStyle("-fx-pie-color: orange");
        	}else {
        		pieChartData.get(0).getNode().setStyle("-fx-pie-color: grey");
        	}
        	
        }
        // add the chart to the scene
        doughnutVBox.getChildren().add(chart);
    }
    
    /**
   	* creates, constructs, and displays the details section 
   	*/
    public void detailSetup() {
    	
    	// default values for the labels
    	qualityLabel.setText("Please set Goals and Main Sleep");
    	deepSleepLabel.setText("Please set Goals and Diary");
    	lightSleepLabel.setText("Please set Goals and Diary");
    	bedTimeLabel.setText("Please set Goals and Main Sleep");
    	awakeTimeLabel.setText("Please set Goals and Main Sleep");
    	durationLabel.setText("Please set Goals and Main Sleep");
    	
    	
    	// if a user has set a goal, for search the diary for a day that matches the selectedDate and display the details for that day for that day
    	if (user.getGoalTotalSleep() != null) {
    		for (Day i : user.getDiary()) {
        		if (i.getDate().equals(selectedDate)) {
        			
        			
        			// deep and light sleep
        			int deepSleepHours = (int) ((i.getTotalSleep().getHours()*60 + i.getTotalSleep().getMinutes()) * 0.33) / 60;
        			int deepSleepMinutes = (int) ((i.getTotalSleep().getHours()*60 + i.getTotalSleep().getMinutes()) * 0.33) % 60;
        			Time deepSleep = new Time(deepSleepHours, deepSleepMinutes, "am");
        			Time totalSleep = new Time(i.getTotalSleep().getHours(), i.getTotalSleep().getMinutes(), "am");
        			
        			deepSleepLabel.setText(deepSleep.printDifferenceFormat());
        	    	lightSleepLabel.setText(deepSleep.difference(totalSleep).printDifferenceFormat());
        	    	
        	    	// bed, awake, quality, and duration
        	    	for (Sleep j : i.getSleepPeriods()) {
        	    		if (j.getType().equals("Main Sleep")){
        	    			bedTimeLabel.setText(j.getStartTime().printTimeFormat(true));
        	    			awakeTimeLabel.setText(j.getEndTime().printTimeFormat(true));
        	    			qualityLabel.setText(j.getQuality() + "%");
        	    			durationLabel.setText(j.getDuration().printDifferenceFormat());
        	    		}
        	    	}
        		}
        	}
    	}
    }
    
    
    /**
   	* creates, constructs, and displays the line chart of sleep throughout the selected week
   	*/
	public void lineChartSetup() {
    	// lineChart
    	lineChart.getData().clear();
    	lineChart.setAnimated(false);
        XYChart.Series series = new XYChart.Series();
        for (Day i : selectedWeek.getDaysOfWeek()) {
        	for (Day j : user.getDiary()) {
        		if (i.getDate().equals(j.getDate())){
        			series.getData().add(new XYChart.Data(i.getDate().getDayOfWeek().name(), j.getTotalSleep().getHours()));
        		}
        	}
        }
        lineChart.getData().addAll(series);
    }
    
	/**
   	* refreshes the scene by calling all the setup methods and updates the controller
   	* with new values for selectedDate and selectedWeek
   	*/
    public void update() {
    	selectedDate = datePicker.getValue();
    	selectedWeek = new Week(selectedDate);
    	doughnutChartSetup();
    	detailSetup();
    	lineChartSetup();
    }
    /**
   	* constructs the scene/GUI to be displayed to the user for the first time
   	*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        
        // set visual style for various nodes
		// created with help from: https://stackoverflow.com/questions/16564818/top-left-and-right-corner-rounded-in-javafx-css
		datePicker.setValue(LocalDate.now());
        chartHBox.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-color: #ffffff");
        gridPane.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-color: #ffffff");
        gridPane.setPadding(new Insets(10,10,10,10));
        for (Node node : gridPane.getChildren()) {
        	if (node instanceof VBox) {
        		node.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-color: #ffffff");
        		GridPane.setMargin(node, new Insets(10,10,10,10));
        	}
        }
        
        
        
        
        // symbols creation
		try {
			
			// GOAL
			FileInputStream goalSymbolInputStream = new FileInputStream("src/goalSymbol.png");
			Image goalSymbol = new Image(goalSymbolInputStream);
			this.goalSymbol.setImage(goalSymbol);
			
			// SLEEP
			FileInputStream sleepSymbolInputStream = new FileInputStream("src/sleepSymbol.png");
			Image sleepSymbol = new Image(sleepSymbolInputStream);
			this.hoursSleptSymbol.setImage(sleepSymbol);
			
			
			// SLEEP DEBT
			FileInputStream debtSymbolInputStream = new FileInputStream("src/debtSymbol.png");
			Image debtSymbol = new Image(debtSymbolInputStream);
			this.sleepDebtSymbol.setImage(debtSymbol);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
