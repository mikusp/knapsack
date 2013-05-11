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
import net.miginfocom.swing.MigLayout;


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
    private Algorithm algorithm = null;
    private IterationThread iterationThread = null;

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
        panel_3.setBackground(Color.WHITE);
        panel_1.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));

        final PlotPanel plotPanel = new PlotPanel();
        panel_3.add(plotPanel, BorderLayout.CENTER);

        JPanel panel_7 = new JPanel();
        panel_3.add(panel_7, BorderLayout.EAST);
        panel_7.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_7.add(scrollPane_1, BorderLayout.CENTER);

        table_1 = new JTable();
        table_1.setBackground(Color.WHITE);
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
        panel_8.setBackground(Color.WHITE);
        panel_7.add(panel_8, BorderLayout.NORTH);

        JLabel lblActualBestItems = new JLabel("Best Items");
        panel_8.add(lblActualBestItems);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(null);
        contentPane.add(tabbedPane, BorderLayout.NORTH);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        tabbedPane.addTab("Initial Conditions", null, panel_6, null);

        final JLabel lblPopulation = new JLabel("Population Size:");

        final JSpinner populationSpinner = new JSpinner();
        populationSpinner.setModel(new SpinnerNumberModel(new Integer(500), new Integer(0), null, new Integer(1)));

        JLabel lblIteration = new JLabel("Iterations:");

        final JSpinner iterationSpinner = new JSpinner();
        iterationSpinner.setModel(new SpinnerNumberModel(new Integer(500), new Integer(0), null, new Integer(1)));

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

        JLabel knapsackSizeLbl = new JLabel("Knapsack Size:");
        final JSpinner knapsackSpinner = new JSpinner();
        knapsackSpinner.setModel(new SpinnerNumberModel(new Integer(500), new Integer(0), null, new Integer(1)));

        panel_6.setLayout(new MigLayout("", "[76px][113px][76px][113px][76px][113px][130px][200px][130px][200px]", "[29px]"));
        panel_6.add(lblPopulation, "cell 0 0,alignx left,aligny center");
        panel_6.add(populationSpinner, "cell 1 0,growx,aligny center");
        panel_6.add(knapsackSizeLbl, "cell 2 0,alignx left,aligny center");
        panel_6.add(knapsackSpinner, "cell 3 0,growx,aligny center");
        panel_6.add(lblIteration, "cell 4 0,alignx center,aligny center");
        panel_6.add(iterationSpinner, "cell 5 0,growx,aligny center");
        panel_6.add(lblMutation, "cell 6 0,alignx center,aligny center");
        panel_6.add(slider, "cell 7 0,alignx left,aligny center");
        panel_6.add(lblCrossoverProbability, "cell 8 0,alignx center,aligny center");
        panel_6.add(slider_1, "cell 9 0,alignx left,aligny center");

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        tabbedPane.addTab("Items", null, panel, null);

        JButton btnAddNewRow = new JButton("Add new item");
        JLabel lblHowManyRandom = new JLabel("Number of random items");
        final JSpinner howManySpinner = new JSpinner();
        howManySpinner.setModel(new SpinnerNumberModel(new Integer(1000), new Integer(0), null, new Integer(1)));
        JButton btnGenerateRandomItems = new JButton("Generate");
        JButton btnLoadFromFile = new JButton("Load");
        JButton btnSaveToFile = new JButton("Save");
        JLabel lblMaxValue = new JLabel("Max Value:");
        JLabel lblMaxSize = new JLabel("Max Size:");
        final JSpinner maxValueSpinner = new JSpinner();
        maxValueSpinner.setModel(new SpinnerNumberModel(new Integer(1000), new Integer(1), null, new Integer(1)));

        final JSpinner maxSizeSpinner = new JSpinner();
        maxSizeSpinner.setModel(new SpinnerNumberModel(new Integer(200), new Integer(1), null, new Integer(1)));

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
        tabbedPane.addTab("Simulation", null, panel_5, null);

        JButton btnStart = new JButton("Start");
        panel_5.add(btnStart);

        final JButton btnStop = new JButton("Pause");
        panel_5.add(btnStop);

        final JButton btnResume = new JButton("Resume");
        panel_5.add(btnResume);

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

        //makes the same thing as random list but for only one item added

        btnAddNewRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                table.setModel(listManager.generateList(model, 1, 1, 1, randomCount));
                randomCount++;
                table.getColumnModel().getColumn(0).setResizable(false);
                table.getColumnModel().getColumn(0).setPreferredWidth(140);
                table.getColumnModel().getColumn(1).setResizable(false);
                table.getColumnModel().getColumn(1).setPreferredWidth(50);
                table.getColumnModel().getColumn(2).setResizable(false);
                table.getColumnModel().getColumn(2).setPreferredWidth(50);
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
                int population = Integer.parseInt(populationSpinner.getValue() + "");
                int knapsacksize = Integer.parseInt(knapsackSpinner.getValue() + "");
                int iterations = Integer.parseInt(iterationSpinner.getValue() + "");
                double crossoverProbability = (double)slider_1.getValue()/100;
                iterationThread = new IterationThread(contentPane, iterations, population, knapsacksize, crossoverProbability, itemsCollection, algorithm, plotPanel.getSupport());
                iterationThread.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(iterationThread != null) iterationThread.pauseThread();
                else JOptionPane.showMessageDialog(null,"Start first!");
            }
        });

        btnResume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(iterationThread != null) iterationThread.resumeThread();
                else JOptionPane.showMessageDialog(null,"Start first!");
            }
        });

        btnNextStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(algorithm != null)
                {
                    algorithm.step();
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
                else JOptionPane.showMessageDialog(null, "Start first!");
            }
        });
    }
}
