import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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


public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ListManager listManager = new ListManager();

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainGUI() {
		try { UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception exc) { JOptionPane.showMessageDialog(null, "Error!"); }
		setTitle("Knapsack Problem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 701);
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
				{null, new Integer(0), new Integer(0)},
			},
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
		table.setRowSelectionInterval(0, 0);
		scrollPane.setViewportView(table);
		Dimension d = table.getPreferredSize();
		scrollPane.setPreferredSize(
		new Dimension(d.width,table.getRowHeight()*3+1));
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.BLACK);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_1.add(panel_3, BorderLayout.CENTER);
		
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
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPopulation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblIteration)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMutation)
					.addGap(18)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(334))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addContainerGap()
							.addComponent(slider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_6.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPopulation)
								.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIteration)
								.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMutation))))
					.addGap(17))
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.addTab("Items", null, panel, null);
		
		JButton btnAddNewRow = new JButton("Add new item");
		
		JLabel lblHowManyRandom = new JLabel("Number of random items");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JButton btnGenerateRandomItems = new JButton("Generate");
		
		JButton btnLoadFromFile = new JButton("Load");
		
		JButton btnSaveToFile = new JButton("Save");
		
		JLabel lblMaxValue = new JLabel("Max Value:");
		
		JLabel lblMaxSize = new JLabel("Max Size:");
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JSpinner spinner_5 = new JSpinner();
		spinner_5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JButton btnDeleteButton = new JButton("Delete item");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAddNewRow)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeleteButton)
					.addGap(39)
					.addComponent(lblHowManyRandom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMaxValue)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spinner_4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMaxSize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_5, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnGenerateRandomItems)
					.addGap(52)
					.addComponent(btnLoadFromFile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSaveToFile)
					.addGap(123))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddNewRow)
						.addComponent(btnDeleteButton)
						.addComponent(lblHowManyRandom)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaxValue)
						.addComponent(spinner_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaxSize)
						.addComponent(spinner_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGenerateRandomItems)
						.addComponent(btnLoadFromFile)
						.addComponent(btnSaveToFile))
					.addContainerGap(10, Short.MAX_VALUE))
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
		
		JComboBox comboBox_1 = new JComboBox();
		panel_4.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Simple Method"}));
		
		JLabel lblMutationStrategies = new JLabel("Mutation Strategy:");
		panel_4.add(lblMutationStrategies);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_4.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Simple Method"}));
		
		JLabel lblStrategies = new JLabel("Elitism Strategy:");
		panel_4.add(lblStrategies);
		
		JComboBox comboBox = new JComboBox();
		panel_4.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Null Method", "Simple Method"}));
		
		JLabel lblSelectionStrategies = new JLabel("Selection Strategy:");
		panel_4.add(lblSelectionStrategies);
		
		JComboBox comboBox_3 = new JComboBox();
		panel_4.add(comboBox_3);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Roulette Wheel"}));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("Symulation", null, panel_5, null);
		
		JButton btnStart = new JButton("Start");
		panel_5.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		panel_5.add(btnStop);
		
		JButton btnNextStep = new JButton("Next step");
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
		
		btnAddNewRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{"", "0", "0"});
				table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
			}
		});
		
		btnDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(table.getSelectedRow());
					table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
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
		            catch (FileNotFoundException e1) { JOptionPane.showMessageDialog(null, "Loading Error!"); }
		        }
			}
		});
		
		btnSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showSaveDialog(MainGUI.this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try{ listManager.saveList(file, table.getModel());}
					catch (FileNotFoundException e1) { JOptionPane.showMessageDialog(null, "Saving Error!"); }
		        }
			}
		});
	}
}
