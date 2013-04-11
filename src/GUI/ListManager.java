package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ListManager {

    /**
     * Saving List of Items to File
     * @param file
     * @param model
     * @throws FileNotFoundException
     */
	public void saveList(File file, TableModel model) throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(file);
		for(int i=0; i<model.getRowCount(); i++)
		{
			out.write((String)model.getValueAt(i, 0)+"\n");
			out.write(model.getValueAt(i, 1)+"\n");
			out.write(model.getValueAt(i, 2)+"\n");
		}
		out.close();
	}

    /**
     * Loading TableModel from File
     * @param file
     * @return
     * @throws FileNotFoundException
     */
	public DefaultTableModel loadList(File file) throws FileNotFoundException
	{
		DefaultTableModel model = new DefaultTableModel(null,
			new String[] {
				"Name", "Size", "Value"
			}
		) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		
		Scanner in = new Scanner(file);
		while(in.hasNext())
		{
			model.addRow(new Object[]{in.nextLine(),in.nextLine(),in.nextLine()});
		}
		in.close();
		
		return model;
	}

    /**
     * Generation Random Items
     * @param model
     * @param howMany
     * @param maxSize
     * @param maxValue
     * @param randomCount - how many randoms we have
     * @return
     */
	public DefaultTableModel generateList(TableModel model, int howMany, int maxSize, int maxValue, Integer randomCount)
	{
		DefaultTableModel defmodel = new DefaultTableModel(null,
				new String[] {
					"Name", "Size", "Value"
				}
			) {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Integer.class, Integer.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		Random random = new Random();
		//Przepisanie elementów
		for(int i=0; i<model.getRowCount(); i++) defmodel.addRow(new Object[]{model.getValueAt(i, 0),model.getValueAt(i, 1),model.getValueAt(i, 2)});
		//Dodawanie randomów
		for(int i=0; i<howMany; i++) defmodel.addRow(new Object[]{"RandomItem"+(randomCount+i+1), random.nextInt(maxSize)+1, random.nextInt(maxValue)+1}); 
		return defmodel;
	}
}
