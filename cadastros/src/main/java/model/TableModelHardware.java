package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelHardware extends AbstractTableModel {

	@Override
	   public int getRowCount() {
	       return hardware.size();
	   }
	 
	   @Override
	   public int getColumnCount() {
	       return colunas.length;
	   }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	private List<Hardware> hardware;
    private String[] colunas = new String[]{"Id","Descrição","Preço"};
    
   public TableModelHardware(List<Hardware> hardware) {
       this.hardware = hardware;
   }
    
   public TableModelHardware(){
    this.hardware = new ArrayList<Hardware>();
   }
 
   
    
}
