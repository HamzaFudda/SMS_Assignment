import java.awt.Color;
import java.awt.BasicStroke;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import javax.swing.*;

public class graphing extends ApplicationFrame {
    public graphing(String title) {
        super(title);
    }
    public graphing( String applicationTitle, String chartTitle, String yaxis,int num,String str, ArrayList<Double> list ) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle ,
                "No of Customer" ,
                yaxis ,
                createDataset(num,str,list) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,false );
        renderer.setSeriesPaint( 0 , Color.BLUE );
        renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    public graphing( String applicationTitle, String chartTitle, String yaxis,int num, ArrayList<Integer> list ,String str) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle ,
                "No of Customer" ,
                yaxis ,
                createDataset(num,list,str) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 400 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,false );
        renderer.setSeriesPaint( 0 , Color.RED );

        renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }
    private XYDataset createDataset(int num,String str, ArrayList<Double> list ) {
        final XYSeries WTdataset= new XYSeries(str);
        for (int i = 0; i < num; i++) {
            WTdataset.add(i+1,list.get(i));
        }
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(WTdataset);
        return dataset;
    }
    private XYDataset createDataset(int num, ArrayList<Integer> list ,String str) {
        final XYSeries QLdataset= new XYSeries(str);
        for (int i = 0; i < num; i++) {
            QLdataset.add(i+1,list.get(i));
        }
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(QLdataset);
        return dataset;
    }
    }
