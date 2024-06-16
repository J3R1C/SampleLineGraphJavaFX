package application;
	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

    private XYChart.Series<Number, Number> series;
    private int xValue = 0;
    private Random random = new Random();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Auto-updating Line Chart");

        // Defining the axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Value");

        // Creating the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Random Data over Time");

        // Defining a series
        series = new XYChart.Series<>();
        series.setName("Random Data");

        // Adding the series to the chart
        lineChart.getData().add(series);

        // Creating a scene
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Set up a timeline to add new data every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> addData()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void addData() {
        // Generate a random value
        double yValue = random.nextDouble() * 100;
        series.getData().add(new XYChart.Data<>(xValue++, yValue));

        // Remove old data if there are too many points (optional)
        if (series.getData().size() > 50) {
            series.getData().remove(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
