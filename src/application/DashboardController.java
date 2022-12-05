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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DashboardController extends MainMenuViewController implements Initializable{

	@FXML
    private VBox sleepVBox;
	
    @FXML
    private HBox chartHBox;

    @FXML
    private VBox doughnutVBox;

    @FXML
    private Label goalLabel;

    @FXML
    private ImageView goalSymbol;

    @FXML
    private Label hoursSleptLabel;

    @FXML
    private ImageView hoursSleptSymbol;

    @FXML
    private Label sleepDebtLabel;

    @FXML
    private ImageView sleepDebtSymbol;

    @FXML
    void switchMainMenuView(ActionEvent event) {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }

    private ObservableList<PieChart.Data> createData(Time sleep, Time debt) {
        return FXCollections.observableArrayList(
                new PieChart.Data("Sleep", sleep.getHours()*60 + sleep.getMinutes()),
                new PieChart.Data("Debt", debt.getHours()*60 + debt.getMinutes()));
    }
    
    public void update() {
    	
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("No sleep/goal set for today", 1));
    	
		String goal = "no goal set for today";
		String sleep = "no sleep set for today";
		String debt = "no sleep/goal set for toaday";
		
		if (user.getGoalTotalSleep() != null) {
			goal = "Goal: " + user.getGoalTotalSleep().printDifferenceFormat();
		}
		
		for (Day i : user.getDiary()) {
			if (i.getDate().equals(LocalDate.now())) {
				pieChartData = createData(i.getTotalSleep(), i.getSleepDebt());
				sleep = "Sleep: " + i.getTotalSleep().printDifferenceFormat();
				debt = "Sleep Debt: " + i.getSleepDebt().printDifferenceFormat();
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
        }else {
        	pieChartData.get(0).getNode().setStyle("-fx-pie-color: grey");
        }
        doughnutVBox.getChildren().add(chart);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        
        // other
        sleepVBox.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-color: #ffffff");
        
        
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
