package GUI;

import GeneticAlgorithm.Algorithm.Algorithm;
import GeneticAlgorithm.Models.ItemCollection;
import GeneticAlgorithm.Models.Population;
import GeneticAlgorithm.Strategies.Crossover.CrossoverStrategy;
import GeneticAlgorithm.Strategies.Crossover.SplitStrategy;
import GeneticAlgorithm.Strategies.Elitism.ElitismStrategy;
import GeneticAlgorithm.Strategies.Elitism.NullElitismStrategy;
import GeneticAlgorithm.Strategies.Elitism.SimpleElitismStrategy;
import GeneticAlgorithm.Strategies.Mutation.MutationStrategy;
import GeneticAlgorithm.Strategies.Mutation.SingleMutationStrategy;
import GeneticAlgorithm.Strategies.Selection.RouletteWheelSelectionStrategy;
import GeneticAlgorithm.Strategies.Selection.SelectionStrategy;
import com.sun.tools.visualvm.charts.SimpleXYChartSupport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class IterationThread extends Thread {

    private JTable table_1;
    private int iteration, actualIteration;
    private Algorithm algorithm;
    private boolean running;
    private SimpleXYChartSupport support;

    public int getActualIteration(){ return actualIteration; }
    public void increaseActualIteration(){ actualIteration++; }

    public IterationThread(JTable table_1, int iteration, Algorithm algorithm, SimpleXYChartSupport support, boolean isRunning)
    {
        this.iteration = iteration;
        this.algorithm = algorithm;
        this.support = support;
        this.table_1 = table_1;
        this.running = isRunning;
        actualIteration = 1;
    }

    public void run()
    {
        DefaultTableModel tempmodel =  new DefaultTableModel(null,new String[] {"Name"}) {
            Class[] columnTypes = new Class[] {
                    String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };
        for (String s : algorithm.getBestItems()) tempmodel.addRow(new Object[]{s});
        table_1.setModel(tempmodel);
        table_1.getColumnModel().getColumn(0).setResizable(false);
        table_1.getColumnModel().getColumn(0).setPreferredWidth(140);

        while(actualIteration <= iteration)
        {
            while(!running) //Bezczynność
            {
                try { sleep(10); }
                catch (InterruptedException e) {JOptionPane.showMessageDialog(null, "Error!"); }
            }
            algorithm.step();
            long[] values = new long[3];
            values[0] = algorithm.getMaximalFitness();
            values[1] = (long) algorithm.getMeanFitness();
            values[2] = algorithm.getMinimalFitness();
            support.addValues(actualIteration,values);
            support.updateDetails(new String[]{values[0]+"",values[1]+"",values[2]+""});
            actualIteration++;
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { JOptionPane.showMessageDialog(null,"DONE","Message",JOptionPane.INFORMATION_MESSAGE); }
        });
    }

    public void pauseThread()
    {
        running = false;

        DefaultTableModel tempmodel =  new DefaultTableModel(null,new String[] {"Name"}) {
            Class[] columnTypes = new Class[] {
                    String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };
        for (String s : algorithm.getBestItems()) tempmodel.addRow(new Object[]{s});
        table_1.setModel(tempmodel);
        table_1.getColumnModel().getColumn(0).setResizable(false);
        table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
    }

    public void resumeThread(){ running = true; }
}
