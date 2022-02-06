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
    public LineChartExample(String title,ArrayList<Double> yAxis,String str) {
        super(title);
        // Create dataset
        DefaultCategoryDataset dataset = createDataset(yAxis);
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
        dataset.addValue(200, series1, ""+1);
        dataset.addValue(150, series1, ""+2);
        dataset.addValue(100, series1, ""+3);
        dataset.addValue(210, series1, ""+4);
        dataset.addValue(240, series1, ""+5);
        dataset.addValue(195, series1, ""+6);
        dataset.addValue(245, series1, ""+7);

        return dataset;
    }
    private DefaultCategoryDataset createDataset(ArrayList<Double> yAxis) {

        String series1 = "";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0; i<10;i++){
            dataset.addValue(yAxis.get(i), series1,""+i);
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