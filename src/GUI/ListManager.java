import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ListManager {
	
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
}
