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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class IterationThread extends Thread {

    private JComboBox crossoverBox, mutationBox, elitismBox, selectionBox;
    private JLabel maxFitnessLabel, meanFitnessLabel, minFitnessLabel;
    private JTable table_1;
    private int iteration;
    private Algorithm algorithm;
    private boolean running = true;

    public IterationThread(JPanel contentPane, int iteration, int population, double crossoverProbability, ItemCollection itemCollection, Algorithm algorithm)
    {
        this.iteration = iteration;
        this.algorithm = algorithm;

        table_1 = (JTable)((JViewport)((JScrollPane)((JPanel)((JPanel)((JPanel)contentPane.getComponent(0)).getComponent(1)).getComponent(1)).getComponent(0)).getComponent(0)).getComponent(0);
        crossoverBox = (JComboBox)((JPanel)((JTabbedPane)contentPane.getComponent(1)).getComponent(2)).getComponent(1);
        mutationBox = (JComboBox)((JPanel)((JTabbedPane)contentPane.getComponent(1)).getComponent(2)).getComponent(3);
        elitismBox = (JComboBox)((JPanel)((JTabbedPane)contentPane.getComponent(1)).getComponent(2)).getComponent(5);
        selectionBox = (JComboBox)((JPanel)((JTabbedPane)contentPane.getComponent(1)).getComponent(2)).getComponent(7);
        maxFitnessLabel = (JLabel)((JPanel)((JPanel)((JPanel)contentPane.getComponent(0)).getComponent(1)).getComponent(0)).getComponent(1);
        meanFitnessLabel = (JLabel)((JPanel)((JPanel)((JPanel)contentPane.getComponent(0)).getComponent(1)).getComponent(0)).getComponent(3);
        minFitnessLabel = (JLabel)((JPanel)((JPanel)((JPanel)contentPane.getComponent(0)).getComponent(1)).getComponent(0)).getComponent(5);

        ElitismStrategy elitismStrategy;
        if(elitismBox.getSelectedIndex() == 1) elitismStrategy = new SimpleElitismStrategy(2);
        else if(elitismBox.getSelectedIndex() == 0) elitismStrategy = new NullElitismStrategy();
        else elitismStrategy = new SimpleElitismStrategy(2);

        CrossoverStrategy crossoverStrategy;
        if(crossoverBox.getSelectedIndex() == 0) crossoverStrategy = new SplitStrategy(crossoverProbability);
        else crossoverStrategy = new SplitStrategy(crossoverProbability);

        MutationStrategy mutationStrategy;
        if(mutationBox.getSelectedIndex() == 0) mutationStrategy = new SingleMutationStrategy();
        else mutationStrategy = new SingleMutationStrategy();

        SelectionStrategy selectionStrategy;
        if(selectionBox.getSelectedIndex() == 0) selectionStrategy = new RouletteWheelSelectionStrategy();
        else  selectionStrategy = new RouletteWheelSelectionStrategy();

        Population initialPopulation = new Population(population, population, itemCollection);
        this.algorithm = new Algorithm(crossoverStrategy, selectionStrategy, mutationStrategy, elitismStrategy, initialPopulation);
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
        maxFitnessLabel.setText(algorithm.getMaximalFitness()+"");
        meanFitnessLabel.setText(algorithm.getMeanFitness()+"");
        minFitnessLabel.setText(algorithm.getMinimalFitness()+"");

        for(int i=1; i<iteration; i++)
        {
            while(!running) //Bezczynność
            {
                try { sleep(10); }
                catch (InterruptedException e) {JOptionPane.showMessageDialog(null, "Error!"); }
            }
            algorithm.step();
            maxFitnessLabel.setText(algorithm.getMaximalFitness()+"");
            meanFitnessLabel.setText(algorithm.getMeanFitness()+"");
            minFitnessLabel.setText(algorithm.getMinimalFitness()+"");
        }
        JOptionPane.showMessageDialog(null,"DONE");
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
        maxFitnessLabel.setText(algorithm.getMaximalFitness()+"");
        meanFitnessLabel.setText(algorithm.getMeanFitness()+"");
        minFitnessLabel.setText(algorithm.getMinimalFitness()+"");
        for (String s : algorithm.getBestItems()) tempmodel.addRow(new Object[]{s});
        table_1.setModel(tempmodel);
        table_1.getColumnModel().getColumn(0).setResizable(false);
        table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
    }
    
    public void resumeThread(){ running = true; }
}
