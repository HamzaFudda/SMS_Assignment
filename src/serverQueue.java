
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Random;
import java.util.Scanner;


//This class is the main class which also uses graphing class to plot the graph
//Have some patience it takes time to calculate then it gives option to plot the graph against diff values


public class serverQueue extends JFrame {
    //declaring some lists to handle data and store queue calculations
    //inter arrival time
    ArrayList<Double> interTime = new ArrayList<Double>();
    //arrival times
    ArrayList<Double> arrivalTime = new ArrayList<Double>();
    //waiting time
    ArrayList<Double> delays = new ArrayList<Double>();
    //time at which customer is served
    ArrayList<Double> custServedTime = new ArrayList<Double>();
    //serving time
    ArrayList<Double> servingTime = new ArrayList<Double>();
    //exit time
    ArrayList<Double> exitTime = new ArrayList<Double>();
    //queue length
    ArrayList<Integer> queueLength = new ArrayList<Integer>();


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            //First server queue to utilise Gaussian Distribution
            serverQueue sq = new serverQueue();
            //Method to initialize Serving times in the array list by gaussian
            sq.gaussian_ST_Initialize();
            //Method to initialize inter arrival times in the array list
            sq.IID_IAT_initialize();
            //This method does all the calculation
            sq.simulation();

            //method to draw graph of waiting time against customers
//            sq.WTGraph(10, "For 10 customers Gaussian", sq.delays);
//            sq.WTGraph(100, "For 100 customers Gaussian",sq.delays);
//            sq.WTGraph(100000,"For 100000 customers Gaussian",sq.delays);
            //method to draw graph of queue length against customers
//            sq.QLGraph(10, "For 10 customers Gaussian", sq.queueLength);
//            sq.QLGraph(100, "For 100 customers Gaussian",sq.queueLength);
//            sq.QLGraph(100000,"For 100000 customers Gaussian",sq.queueLength);

            serverQueue sq2 = new serverQueue();
            //Method to initialize Serving times in the array list by IID
            sq2.IID_ST_initialize();
            sq2.IID_IAT_initialize();
            sq2.simulation();
            //sq2.WTGraph(10, "For 10 customers IID",sq2.delays);
            //sq2.WTGraph(100, "For 100 customers IID",sq2.delays);
            //sq2.WTGraph(100000,"For 100000 customers IID",sq2.delays);
            //sq2.QLGraph(10, "For 10 customers IID",sq2.queueLength);
            //sq2.QLGraph(100, "For 100 customers IID",sq2.queueLength);
            //sq2.QLGraph(100000,"For 100000 customers IID",sq2.queueLength);

            System.out.println("Select choice of no of customers to plot the graph");
            System.out.println("Options are below");
            System.out.println("1- For 10 customers");
            System.out.println("2- For 100 customers");
            System.out.println("3- For 1000 customers");
            System.out.println("4- For 10000 customers");
            System.out.println("5- For 100000 customers");


            Scanner x= new Scanner(System.in);
            int option=0;
            while(option<1 || option>4){
                option=Integer.parseInt(x.nextLine());
            }
            int multiple=0;
            switch (option){
                case 1:
                    multiple=10;
                    break;
                case 2:
                    multiple=100;
                    break;
                case 3:
                    multiple=1000;
                    break;
                case 4:
                    multiple=10000;
                    break;
                case 5:
                    multiple=100000;
                    break;
            }
            sq.WTGraph(multiple, "For "+multiple+" customers Gaussian", sq.delays);
            sq.QLGraph(multiple, "For "+multiple+" customers Gaussian", sq.queueLength);
            sq2.WTGraph(multiple, "For "+multiple+" customers IID",sq2.delays);
            sq2.QLGraph(multiple, "For "+multiple+" customers IID",sq2.queueLength);

        });

    }

    //generates serving time according to gaussian
    public void gaussian_ST_Initialize() {
        Random random = new Random();
        double num;
        double total = 0.0;
        for (int i = 0; i < 100000; i++) {
            num = random.nextGaussian() * 5 + 10;
            if (num < 0) {
                while (num < 0) {
                    num = random.nextGaussian() * 5 + 10;
                }
            }
            total = total + num;
            servingTime.add(Math.floor(num * 100) / 100.0);
        }
       // System.out.println(total / 100000.0);

    }
    //generates serving time according to IID
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

    //generates inter arrival times according to IID
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

    //for hard coded data
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

    //for hard coded data
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
    //it uses multiple conditions to calculate values and populates it in their respective array list
    public void simulation() {
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

    }

    //To create wait time against customers graph
    public void WTGraph(int i, String str, ArrayList<Double> list) {
        graphing example = new graphing(str, str, "Waiting Time", i, "", list);
        example.pack();
        example.setSize(600, 400);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(example);
        example.setVisible(true);
    }

    //To create queue length against customers graph
    public void QLGraph(int i, String str, ArrayList<Integer> list) {
        graphing example = new graphing(str, str, "Queue Length", i, list, "");
        example.pack();
        example.setSize(600, 400);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(example);
        example.setVisible(true);

    }
}