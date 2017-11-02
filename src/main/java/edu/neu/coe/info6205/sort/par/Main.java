package edu.neu.coe.info6205.sort.par;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {

    public static void main(String[] args) {
        if (args.length>0) ParSort.cutoff = Integer.parseInt(args[0]);
        Random random = new Random(0L);
        int[] array = new int[2000];
        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000);
        
        //int[] array2 = new int[10];
        //for (int i = 0; i < array2.length; i++) array2[i] = 10-i;   
        
        ParSort ps = new ParSort();
        
        int[] res = new int[100];
        int[] origin = new int[2000];
        for (int i = 0; i < 2000; i++) origin[i] = array[i];
        
        for (int iter = 0; iter < 100; iter++) {
        for (int co = 20; co <= 2000; co+=20) {
            for (int i = 0; i < 2000; i++) array[i] = origin[i];
        	Timestamp tss = new Timestamp(System.nanoTime());
        	ParSort.cutoff = co;
        	ps.sort(array, 0, array.length);
        	Timestamp tse = new Timestamp(System.nanoTime());
        	long diff = tse.getTime() - tss.getTime();
        	//System.out.println("When cutoff is " + co + ", the running time is " + diff + " nano seconds");
        	res[co/20-1] +=(int)diff;
        }
        }

        XYSeriesCollection data = new XYSeriesCollection();
        XYSeries series = new XYSeries("xy");
        for (int i = 5; i < 100; i++) {
        	series.add(i*20, res[i]/100);
        }
        data.addSeries(series);
        
        JFreeChart chart = ChartFactory.createScatterPlot("Different cutoff", "cutoff", "NanoSeconds", data, PlotOrientation.VERTICAL, false, false, false);
        ChartFrame frame = new ChartFrame("No title", chart);
        frame.setSize(500, 300);
        frame.setVisible(true);
        //frame.setVisible(true);
        
        //for (int i : array) System.out.println(i);
        if (array[0]==11) System.out.println("Success!");
    }
}
