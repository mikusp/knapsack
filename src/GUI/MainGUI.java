package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import GeneticAlgorithm.Algorithm.Algorithm;
import GeneticAlgorithm.Models.Item;
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
import net.miginfocom.swing.MigLayout;

import static java.lang.System.out;


public class MainGUI extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ListManager listManager = new ListManager();
    private Integer randomCount = 0;
    private JTable table_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGUI frame = new MainGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "serial" })
    public MainGUI() {
        try { UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) { JOptionPane.showMessageDialog(null, "Error!"); }
        setTitle("Knapsack Problem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1200, 701);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane, BorderLayout.WEST);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        {"Item1", "1", "1"},
                },
                new String[] {
                        "Name", "Size", "Value"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, Object.class, Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(140);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.setRowSelectionInterval(0, 0);
        scrollPane.setViewportView(table);
        Dimension d = table.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(d.width,table.getRowHeight()*3+1));

        JPanel panel_3 = new JPanel();
        panel_3.setForeground(Color.BLACK);
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_1.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.LIGHT_GRAY);
        FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
        flowLayout_2.setHgap(20);
        panel_3.add(panel_2, BorderLayout.NORTH);

        JLabel lblMax = new JLabel("Maximal Fitness:");
        panel_2.add(lblMax);

        final JLabel maxFitnessLabel = new JLabel("0");
        panel_2.add(maxFitnessLabel);

        JLabel lblNewLabel = new JLabel("Mean Fitness:");
        panel_2.add(lblNewLabel);

        final JLabel meanFitenssLabel = new JLabel("0");
        panel_2.add(meanFitenssLabel);

        JLabel lblNewLabel_1 = new JLabel("Minimal Fitenss:");
        panel_2.add(lblNewLabel_1);

        final JLabel minFitnessLabel = new JLabel("0");
        panel_2.add(minFitnessLabel);

        JPanel panel_7 = new JPanel();
        panel_3.add(panel_7, BorderLayout.EAST);
        panel_7.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_7.add(scrollPane_1, BorderLayout.CENTER);

        table_1 = new JTable();
        table_1.setBackground(Color.LIGHT_GRAY);
        table_1.setFillsViewportHeight(true);
        table_1.setModel(new DefaultTableModel(
                new Object[][] {
                        {null},
                },
                new String[] {
                        "Name"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table_1.getColumnModel().getColumn(0).setResizable(false);
        table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
        scrollPane_1.setViewportView(table_1);
        Dimension d2 = table_1.getPreferredSize();
        scrollPane_1.setPreferredSize(new Dimension(d2.width,table_1.getRowHeight()*3+1));

        JPanel panel_8 = new JPanel();
        panel_8.setBackground(Color.LIGHT_GRAY);
        panel_7.add(panel_8, BorderLayout.NORTH);

        JLabel lblActualBestItems = new JLabel("Best Items");
        panel_8.add(lblActualBestItems);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(null);
        contentPane.add(tabbedPane, BorderLayout.NORTH);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        tabbedPane.addTab("Initial Conditions", null, panel_6, null);

        JLabel lblPopulation = new JLabel("Population Size:");

        JSpinner spinner_1 = new JSpinner();
        spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

        JLabel lblIteration = new JLabel("Iterations:");

        JSpinner spinner_3 = new JSpinner();
        spinner_3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

        JLabel lblMutation = new JLabel("Mutation probability:");

        final JSlider slider = new JSlider();
        Hashtable<Integer, JLabel> hashtable = new Hashtable<Integer, JLabel>();
        hashtable.put(0, new JLabel("0"));
        hashtable.put(50, new JLabel("0.5"));
        hashtable.put(100, new JLabel("1"));
        slider.setLabelTable(hashtable);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        JLabel lblCrossoverProbability = new JLabel("Crossover probability:");

        final JSlider slider_1 = new JSlider();
        hashtable = new Hashtable<Integer, JLabel>();
        hashtable.put(0, new JLabel("0"));
        hashtable.put(50, new JLabel("0.5"));
        hashtable.put(100, new JLabel("1"));
        slider_1.setLabelTable(hashtable);
        slider_1.setPaintLabels(true);
        slider_1.setSnapToTicks(true);
        panel_6.setLayout(new MigLayout("", "[76px][113px][65px][124px][120px][220px][130px][200px]", "[29px]"));
        panel_6.add(lblPopulation, "cell 0 0,alignx left,aligny center");
        panel_6.add(spinner_1, "cell 1 0,growx,aligny center");
        panel_6.add(lblIteration, "cell 2 0,alignx center,aligny center");
        panel_6.add(spinner_3, "cell 3 0,growx,aligny center");
        panel_6.add(lblMutation, "cell 4 0,alignx center,aligny center");
        panel_6.add(slider, "cell 5 0,alignx left,aligny center");
        panel_6.add(lblCrossoverProbability, "cell 6 0,alignx center,aligny center");
        panel_6.add(slider_1, "cell 7 0,alignx left,aligny center");

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        tabbedPane.addTab("Items", null, panel, null);

        JButton btnAddNewRow = new JButton("Add new item");
        JLabel lblHowManyRandom = new JLabel("Number of random items");
        final JSpinner howManySpinner = new JSpinner();
        howManySpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
        JButton btnGenerateRandomItems = new JButton("Generate");
        JButton btnLoadFromFile = new JButton("Load");
        JButton btnSaveToFile = new JButton("Save");
        JLabel lblMaxValue = new JLabel("Max Value:");
        JLabel lblMaxSize = new JLabel("Max Size:");
        final JSpinner maxValueSpinner = new JSpinner();
        maxValueSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

        final JSpinner maxSizeSpinner = new JSpinner();
        maxSizeSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

        JButton btnDeleteButton = new JButton("Delete item");
        JButton btnClear = new JButton("Clear");

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAddNewRow)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnDeleteButton)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnClear)
                                .addGap(50)
                                .addComponent(lblHowManyRandom)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(howManySpinner, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblMaxValue)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(maxValueSpinner, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblMaxSize)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(maxSizeSpinner, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnGenerateRandomItems)
                                .addGap(40)
                                .addComponent(btnLoadFromFile)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnSaveToFile)
                                .addGap(41))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(15)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAddNewRow)
                                        .addComponent(btnDeleteButton)
                                        .addComponent(lblHowManyRandom)
                                        .addComponent(howManySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaxValue)
                                        .addComponent(maxValueSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLoadFromFile)
                                        .addComponent(btnSaveToFile)
                                        .addComponent(btnGenerateRandomItems)
                                        .addComponent(lblMaxSize)
                                        .addComponent(maxSizeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnClear))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
        flowLayout_1.setVgap(15);
        flowLayout_1.setHgap(10);
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        tabbedPane.addTab("Strategies", null, panel_4, null);

        JLabel lblCrossoverStrategies = new JLabel("Crossover Strategy:");
        panel_4.add(lblCrossoverStrategies);

        final JComboBox crossoverBox = new JComboBox();
        panel_4.add(crossoverBox);
        crossoverBox.setModel(new DefaultComboBoxModel(new String[] {"Split Method"}));

        JLabel lblMutationStrategies = new JLabel("Mutation Strategy:");
        panel_4.add(lblMutationStrategies);

        final JComboBox mutationBox = new JComboBox();
        panel_4.add(mutationBox);
        mutationBox.setModel(new DefaultComboBoxModel(new String[] {"Single Method"}));

        JLabel lblStrategies = new JLabel("Elitism Strategy:");
        panel_4.add(lblStrategies);

        final JComboBox elitismBox = new JComboBox();
        panel_4.add(elitismBox);
        elitismBox.setModel(new DefaultComboBoxModel(new String[] {"Null Method", "Simple Method"}));

        JLabel lblSelectionStrategies = new JLabel("Selection Strategy:");
        panel_4.add(lblSelectionStrategies);

        final JComboBox selectionBox = new JComboBox();
        panel_4.add(selectionBox);
        selectionBox.setModel(new DefaultComboBoxModel(new String[] {"Roulette Wheel"}));

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
        flowLayout.setHgap(10);
        flowLayout.setVgap(15);
        flowLayout.setAlignment(FlowLayout.LEFT);
        tabbedPane.addTab("Symulation", null, panel_5, null);

        JButton btnStart = new JButton("Start");
        panel_5.add(btnStart);

        final JButton btnStop = new JButton("Stop");
        panel_5.add(btnStop);

        final JButton btnNextStep = new JButton("Next step");
        panel_5.add(btnNextStep);

        /**
         * FUNKCJE
         */

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Hashtable<Integer, JLabel> hashtable = new Hashtable<Integer, JLabel>();
                hashtable.put(0, new JLabel("0"));
                double temp = (double)slider.getValue()/100;
                hashtable.put(50, new JLabel(temp+""));
                hashtable.put(100, new JLabel("1"));
                slider.setLabelTable(hashtable);
                slider.setPaintLabels(true);
            }
        });

        slider_1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Hashtable<Integer, JLabel> hashtable = new Hashtable<Integer, JLabel>();
                hashtable.put(0, new JLabel("0"));
                double temp = (double)slider_1.getValue()/100;
                hashtable.put(50, new JLabel(temp+""));
                hashtable.put(100, new JLabel("1"));
                slider_1.setLabelTable(hashtable);
                slider_1.setPaintLabels(true);
            }
        });

        btnAddNewRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{"", "1", "1"});
                table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
            }
        });

        btnDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(table.getSelectedRow());
                    if(table.getRowCount() > 0) table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
                }
                catch(Exception exc){ JOptionPane.showMessageDialog(null, "List empty!");}
            }
        });

        btnLoadFromFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(MainGUI.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        table.setModel(listManager.loadList(file));
                        table.getColumnModel().getColumn(0).setResizable(false);
                        table.getColumnModel().getColumn(0).setPreferredWidth(140);
                        table.getColumnModel().getColumn(1).setResizable(false);
                        table.getColumnModel().getColumn(1).setPreferredWidth(50);
                        table.getColumnModel().getColumn(2).setResizable(false);
                        table.getColumnModel().getColumn(2).setPreferredWidth(50);
                        table.setRowSelectionInterval(0, 0);
                    }
                    catch (Exception e1) { JOptionPane.showMessageDialog(null, "Loading Error!"); }
                }
            }
        });

        btnSaveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(MainGUI.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try{
                        listManager.saveList(file, table.getModel());
                        JOptionPane.showMessageDialog(null, "List Saved!");
                    }
                    catch (Exception e1) { JOptionPane.showMessageDialog(null, "Saving Error!"); }
                }
            }
        });

        btnGenerateRandomItems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setModel(listManager.generateList(table.getModel(), (Integer)howManySpinner.getValue(), (Integer)maxSizeSpinner.getValue(), (Integer)maxValueSpinner.getValue(), randomCount));
                randomCount += (Integer)howManySpinner.getValue();
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(0).setPreferredWidth(140);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(1).setPreferredWidth(50);
                table.getColumnModel().getColumn(2).setResizable(false);
                table.getColumnModel().getColumn(2).setPreferredWidth(50);
                table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setModel(new DefaultTableModel(null,
                        new String[] {
                                "Name", "Size", "Value"
                        }
                ) {

                    private static final long serialVersionUID = 1L;

                    Class[] columnTypes = new Class[] {
                            String.class, Integer.class, Integer.class
                    };
                    public Class getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                });
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(0).setPreferredWidth(140);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(1).setPreferredWidth(50);
                table.getColumnModel().getColumn(2).setResizable(false);
                table.getColumnModel().getColumn(2).setPreferredWidth(50);
            }
        });

        //Generating

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemCollection itemsCollection = new ItemCollection();
                for(int i=0; i<table.getRowCount(); i++) itemsCollection.addItem(new Item(Integer.parseInt(table.getValueAt(i,1)+""), Integer.parseInt(table.getValueAt(i,2)+""), (String)table.getValueAt(i,0)));
                //TODO
                Population initialPopulation = new Population(500, 500, itemsCollection);

                //TODO - SimpleElitismStrategy
                ElitismStrategy elitismStrategy;
                if(elitismBox.getSelectedIndex() == 1) elitismStrategy = new SimpleElitismStrategy(2);
                else if(elitismBox.getSelectedIndex() == 0) elitismStrategy = new NullElitismStrategy();
                else elitismStrategy = new SimpleElitismStrategy(2);

                CrossoverStrategy crossoverStrategy;
                if(crossoverBox.getSelectedIndex() == 0) crossoverStrategy = new SplitStrategy((double)slider_1.getValue()/100);
                else crossoverStrategy = new SplitStrategy((double)slider_1.getValue()/100);

                MutationStrategy mutationStrategy;
                if(mutationBox.getSelectedIndex() == 0) mutationStrategy = new SingleMutationStrategy();
                else mutationStrategy = new SingleMutationStrategy();

                SelectionStrategy selectionStrategy;
                if(selectionBox.getSelectedIndex() == 0) selectionStrategy = new RouletteWheelSelectionStrategy();
                else  selectionStrategy = new RouletteWheelSelectionStrategy();

                Algorithm a = new Algorithm(crossoverStrategy, selectionStrategy, mutationStrategy, elitismStrategy, initialPopulation);
                //TODO - EDIT - lista, max, mean, min, iteracje
                DefaultTableModel tempmodel =  new DefaultTableModel(null,new String[] {"Name"}) {
                    Class[] columnTypes = new Class[] {
                            String.class
                    };
                    public Class getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                };
                for (String s : a.getBestItems()) tempmodel.addRow(new Object[]{s});
                table_1.setModel(tempmodel);
                table_1.getColumnModel().getColumn(0).setResizable(false);
                table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
                maxFitnessLabel.setText(a.getMaximalFitness()+"");
                meanFitenssLabel.setText(a.getMeanFitness()+"");
                minFitnessLabel.setText(a.getMinimalFitness()+"");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnNextStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
