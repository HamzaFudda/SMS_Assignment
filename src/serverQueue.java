
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.Object;

public class serverQueue extends JFrame {
    //declaring some lists to handle data and store queue calculations
    ArrayList<Double> interTime = new ArrayList<Double>();
    ArrayList<Double> arrivalTime = new ArrayList<Double>();
    ArrayList<Double> delays = new ArrayList<Double>();
    ArrayList<Double> custServedTime = new ArrayList<Double>();
    ArrayList<Double> servingTime = new ArrayList<Double>();
    ArrayList<Double> exitTime = new ArrayList<Double>();
    ArrayList<Integer> queueLength = new ArrayList<Integer>();


    public static void main(String[] args) throws IOException {
        serverQueue sq = new serverQueue();
//        sq.initialize2();
        sq.gaussian_ST_Initialize();
        sq.IID_IAT_initialize();
        sq.simulation("Gaussian");
        sq.graph();
        SwingUtilities.invokeLater(() -> {

            sq.setLocationRelativeTo(null);
            sq.pack();
            sq.setSize(600, 400);
            sq.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            sq.setVisible(true);
        });

        serverQueue sq2 = new serverQueue();
        sq2.IID_ST_initialize();
        sq2.IID_IAT_initialize();
        sq2.simulation("IID");

    }

    public void clearAll() {
        interTime.clear();
        arrivalTime.clear();
        delays.clear();
        custServedTime.clear();
        servingTime.clear();
        exitTime.clear();
        queueLength.clear();
    }

    public void gaussian_ST_Initialize() {
        Random random = new Random();
        double num;
        double total = 0.0;
        for (int i = 0; i < 100000; i++) {
            num = random.nextGaussian() * 5 + 10;
            if (num < 0) {
                num = num * -1;
            }
//            total = total + num;
            //System.out.println(Math.floor(num * 100) / 100.0);
            servingTime.add(Math.floor(num * 100) / 100.0);
        }

    }

    public void IID_ST_initialize() {
        double num;
        double total = 0.0;
        for (int i = 0; i < 100000; i++) {
            num = Math.random() * 20;
//            total = total + num;
//            System.out.println(total/100000.0);
            servingTime.add(Math.floor(num * 100) / 100.0);
        }
    }

    public void IID_IAT_initialize() {
        double num;
        double total = 0.0;
        for (int i = 0; i < 100000; i++) {
            num = Math.random() * 3.4;
            total = total + num;
            //System.out.println(total/100000.0);
            interTime.add(Math.floor(num * 100) / 100.0);
        }
        //now updating arrival times
        arrivalTime.add(interTime.get(0));
        for (int i = 1; i < 100000; i++) {
            arrivalTime.add(Math.floor((arrivalTime.get(i - 1) + interTime.get(i)) * 100) / 100.0);
        }

    }

    public void initialize() {
        //call methods to set arrival time and serving time
        arrivalTime.add(0.5);
        arrivalTime.add(4.1);
        arrivalTime.add(5.5);
        arrivalTime.add(5.9);
        arrivalTime.add(10.5);
        servingTime.add(10.4);
        servingTime.add(5.4);
        servingTime.add(5.2);
        servingTime.add(12.3);
        servingTime.add(9.4);

    }


    public void initialize2() {

        arrivalTime.add(1.3);
        arrivalTime.add(3.800000001);
        arrivalTime.add(6.5);
        arrivalTime.add(9.4);
        arrivalTime.add(12.5);
        arrivalTime.add(27.5);
        arrivalTime.add(28.5);
        servingTime.add(2.5);
        servingTime.add(3.6);
        servingTime.add(9.5);
        servingTime.add(7.8);
        servingTime.add(0.1);
        servingTime.add(5.0);
        servingTime.add(5.0);
    }


    //all work takes place here
    public void simulation(String str) {
        int qLength = 0;
        double delay_time = 0.0;
        double exit_time = 0.0;
        //interTime.add(arrivalTime.get(0));
        delays.add(delay_time);
        exit_time = arrivalTime.get(0) + servingTime.get(0);
        custServedTime.add(arrivalTime.get(0));
        exitTime.add(exit_time);
        //queueLength.add(0);
        for (int i = 1; i < arrivalTime.size(); i++) {
            //interTime.add(arrivalTime.get(i) - arrivalTime.get(i - 1));
            if (arrivalTime.get(i) <= exitTime.get(i - 1)) {
                custServedTime.add(exitTime.get(i - 1));
                delay_time = custServedTime.get(i) - arrivalTime.get(i);
                exit_time = exitTime.get(i - 1) + servingTime.get(i);
                delays.add(Math.floor(delay_time * 100) / 100.0);
                exitTime.add(Math.floor(exit_time * 100) / 100.0);
            } else {
                custServedTime.add(arrivalTime.get(i));
                delay_time = 0.0;
                exit_time = arrivalTime.get(i) + servingTime.get(i);
                //queueLength.add(0);
                delays.add(delay_time);
                exitTime.add(Math.floor(exit_time * 100) / 100.0);
            }
        }
        for (int x = arrivalTime.size() - 1; x >= 0; x--) {
            qLength = 0;
            if (x >= 1 && arrivalTime.get(x) >= exitTime.get(x - 1)) {
                queueLength.add(0);
                System.out.println(x);
                continue;
            }
            int z = 1;
            while (z < x + 1 && arrivalTime.get(x) < exitTime.get(x - z)) {
                qLength++;
                z++;
            }
            queueLength.add(qLength);
        }
        Collections.reverse(queueLength);
//        System.out.println("Inter arrival");
//        System.out.println(interTime.toString());
//        System.out.println("Arrival time");
//        System.out.println(arrivalTime.toString());
//        System.out.println("Delay");
//        System.out.println(delays.toString());
//        System.out.println("Customer served");
//        System.out.println(custServedTime.toString());
//        System.out.println("Serving time");
//        System.out.println(servingTime.toString());
//        System.out.println("Exit time");
//        System.out.println(exitTime.toString());
//        System.out.println("Queue length");
//        System.out.println(queueLength.toString());
//        SwingUtilities.invokeLater(() -> {
//            LineChartExample example = new LineChartExample(str, delays, "Waiting TIme");
//            example.setAlwaysOnTop(true);
//            example.pack();
//            example.setSize(600, 400);
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            example.setVisible(true);
//        });
//        SwingUtilities.invokeLater(() -> {
//            LineChartExample example = new LineChartExample(str, queueLength, "Queue Length");
//            example.setAlwaysOnTop(true);
//            example.pack();
//            example.setSize(600, 400);
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            example.setVisible(true);
//        });

    }

    public void graph() throws IOException {

        DefaultCategoryDataset WTdataset = new DefaultCategoryDataset();
        DefaultCategoryDataset QLdataset = new DefaultCategoryDataset();

        //wt
        for (int i = 0; i < delays.size(); i++) {
            WTdataset.addValue(delays.get(i), "Waiting Time", Integer.toString(i));

        }
        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Waiting Time VS Customers", "Customers",
                "Waiting Time",
                WTdataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */
        ChartPanel panel = new ChartPanel(lineChartObject);
        setContentPane(panel);
        File lineChart = new File("WatingTime.jpeg");
        ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);
    }
}