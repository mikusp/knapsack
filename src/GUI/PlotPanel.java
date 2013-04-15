package GUI;

import com.sun.tools.visualvm.charts.ChartFactory;
import com.sun.tools.visualvm.charts.SimpleXYChartDescriptor;
import com.sun.tools.visualvm.charts.SimpleXYChartSupport;

import javax.swing.*;
import java.awt.*;

public class PlotPanel extends JPanel {

    private static final int VALUES_LIMIT = 1000000;
    private SimpleXYChartSupport support;
    private SimpleXYChartDescriptor descriptor;

    public PlotPanel() {
        createModels();
        setLayout(new BorderLayout());
        add(support.getChart(), BorderLayout.CENTER);
    }

    public SimpleXYChartSupport getSupport(){ return support; }

    public void createModels() {
        descriptor = SimpleXYChartDescriptor.decimal( 1000, 1d, true, VALUES_LIMIT);

        descriptor.addLineFillItems("Max Fitness");
        descriptor.addLineFillItems("Mean Fitness");
        descriptor.addLineFillItems("Min Fitness");

        descriptor.setDetailsItems(new String[]{"Max", "Mean", "Min"});
        descriptor.setChartTitle("<html><font size='+1'><b>Knapsack Fitness</b></font></html>");
        descriptor.setXAxisDescription("<html><i>Time</i></html>");
        descriptor.setYAxisDescription("<html><i>Fitness</i></html>");

        support = ChartFactory.createSimpleXYChart(descriptor);
    }
}
