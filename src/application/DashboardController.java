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

    LocalDate selectedDate = LocalDate.now();
    Week selectedWeek = new Week(LocalDate.now());
    
    @FXML
    void switchMainMenuView(ActionEvent event) {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    @FXML
    void dateUpdater(ActionEvent event) {
    	update();
    }
    

    
    private ObservableList<PieChart.Data> createData(Time sleep, Time debt) {
        return FXCollections.observableArrayList(
                new PieChart.Data("Sleep", sleep.getHours()*60 + sleep.getMinutes()),
                new PieChart.Data("Debt", debt.getHours()*60 + debt.getMinutes()));
    }
    
    public void doughnutChartSetup() {
    	doughnutVBox.getChildren().clear();
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("No sleep/goal set for today", 1));
    	
		String goal = "no goal set for today";
		String sleep = "no sleep set for today";
		String debt = "no sleep set for toaday";
		
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
		
		
		goalLabel.setText(goal);
		hoursSleptLabel.setText(sleep);
		sleepDebtLabel.setText(debt);
		
        final DoughnutChart chart = new DoughnutChart(pieChartData);
        chart.setLabelsVisible(true);
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
        doughnutVBox.getChildren().add(chart);
    }
    
    public void detailSetup() {
    	qualityLabel.setText("Please set Goals and Main Sleep");
    	deepSleepLabel.setText("Please set Goals and Diary");
    	lightSleepLabel.setText("Please set Goals and Diary");
    	bedTimeLabel.setText("Please set Goals and Main Sleep");
    	awakeTimeLabel.setText("Please set Goals and Main Sleep");
    	durationLabel.setText("Please set Goals and Main Sleep");
    	
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
    
    public void update() {
    	selectedDate = datePicker.getValue();
    	selectedWeek = new Week(selectedDate);
    	doughnutChartSetup();
    	detailSetup();
    	lineChartSetup();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        
        // other
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
        
        
        
        
        // symbols
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
