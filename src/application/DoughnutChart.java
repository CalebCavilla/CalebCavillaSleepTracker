package application;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * NOT MY CODE: USED IN THE CREATION OF THE DOUGHNUT CHART IN THE DASHBOARD 
 * LINK TO ORIGINAL AUTHOR: https://stackoverflow.com/questions/24121580/can-piechart-from-javafx-be-displayed-as-a-doughnut
*/
public class DoughnutChart extends PieChart {
     Circle innerCircle;

     /**
     * Constructs a pie chart with a inner circle drawing in the middle to create the illusion of a doughnut chart
     * @param pieData an observable list of data used to construct the slices of the pie chart.
     */
    public DoughnutChart(ObservableList<Data> pieData) {
        super(pieData);

        innerCircle = new Circle();
        innerCircle.setFill(Color.WHITESMOKE);
        innerCircle.setStroke(Color.WHITE);
        innerCircle.setStrokeWidth(3);
    }

    /**
    * Determines where the pie chart will be drawn in its container
    * @param top the top of the container
    * @param left the left of the container
    * @param contentWidth the width of the container
    * @param contentHeight the height of the container
    */
    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);

        addInnerCircleIfNotPresent();
        updateInnerCircleLayout();
    }

    /**
    * Checks if the pie chart has a inner circle, if it does not, it adds the inner circle to the chart
    */
    private void addInnerCircleIfNotPresent() {
        if (getData().size() > 0) {
            Node pie = getData().get(0).getNode();
            if (pie.getParent() instanceof Pane) {
                Pane parent = (Pane) pie.getParent();

                if (!parent.getChildren().contains(innerCircle)) {
                    parent.getChildren().add(innerCircle);
                }
            }
        }
    }

    /**
    * Adjusts the inner circle so that it is centered and the proper size for the pie chart
    */
    private void updateInnerCircleLayout() {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (PieChart.Data data: getData()) {
            Node node = data.getNode();

            Bounds bounds = node.getBoundsInParent();
            if (bounds.getMinX() < minX) {
                minX = bounds.getMinX();
            }
            if (bounds.getMinY() < minY) {
                minY = bounds.getMinY();
            }
            if (bounds.getMaxX() > maxX) {
                maxX = bounds.getMaxX();
            }
            if (bounds.getMaxY() > maxY) {
                maxY = bounds.getMaxY();
            }
        }

        innerCircle.setCenterX(minX + (maxX - minX) / 2);
        innerCircle.setCenterY(minY + (maxY - minY) / 2);

        innerCircle.setRadius((maxX - minX) / 4);
    }
}