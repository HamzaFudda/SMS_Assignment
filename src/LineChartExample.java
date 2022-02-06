import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

public class LineChartExample extends JFrame {

    private static final long serialVersionUID = 1L;

    public LineChartExample(String title) {
        super(title);
        // Create dataset
        DefaultCategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Simulation", // Chart title
                "No of Customers", // X-Axis Label
                "Queue Length", // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    public LineChartExample(String title,ArrayList<Double> yAxis,ArrayList<Integer> xAxis,String str) {
        super(title);
        // Create dataset
        DefaultCategoryDataset dataset = createDataset(yAxis,xAxis);
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Simulation", // Chart title
                "No of Customers", // X-Axis Label
                str, // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    private DefaultCategoryDataset createDataset() {

        String series1 = "";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(200, series1, "2016-12-19");
        dataset.addValue(150, series1, "2016-12-20");
        dataset.addValue(100, series1, "2016-12-21");
        dataset.addValue(210, series1, "2016-12-22");
        dataset.addValue(240, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(245, series1, "2016-12-25");

        return dataset;
    }
    private DefaultCategoryDataset createDataset(ArrayList<Double> yAxis,ArrayList<Integer> xAxis) {

        String series1 = "";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0; i<yAxis.size();i++){
            dataset.addValue(yAxis.get(i), series1,xAxis.get(i));
        }

        return dataset;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LineChartExample example = new LineChartExample("Simulation");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(600, 400);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}