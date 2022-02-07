import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class graph extends JFrame {


    public void waitingGraph(int num, String str, ArrayList <Double> list) {
        DefaultCategoryDataset WTdataset = new DefaultCategoryDataset();

        for (int i = 0; i < num; i++) {
            WTdataset.addValue(list.get(i), "Waiting Time", Integer.toString(i + 1));

        }
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Waiting Time VS NO of Customers " + str, "NO of Customers",
                "Waiting Time",
                WTdataset, PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(lineChartObject);
        setContentPane(panel);
        this.setAlwaysOnTop(true);
        this.pack();
        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void queueLengthGraph(int num, String str, ArrayList<Integer> list) {
        DefaultCategoryDataset QLdataset = new DefaultCategoryDataset();

        for (int i = 0; i < num; i++) {
            QLdataset.addValue(list.get(i), "Queue Length", Integer.toString(i + 1));

        }
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Queue Length VS NO of Customers " + str, "NO of Customers",
                "Queue Length",
                QLdataset, PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(lineChartObject);
        setContentPane(panel);
    }


}
