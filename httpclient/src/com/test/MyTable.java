package com.test;
import java.awt.Color;  
import java.awt.Component;  
import java.awt.Point;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseMotionListener;  
  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JTable;  
import javax.swing.table.AbstractTableModel;  
import javax.swing.table.DefaultTableCellRenderer;  
  
/** 
 * TableDemo is just like SimpleTableDemo, except that it uses a custom 
 * TableModel. 
 */  
  
class MyTable extends JTable implements MouseMotionListener{  
  
    private int mouseOnRowIndex = -1;  
  
    public MyTable() {  
          
        this.setModel(new MyTableModel());  
          
        this.addMouseMotionListener(this);  
  
        this.setDefaultRenderer(Object.class, new MyTableCellRenderer());  
    }  
  
    public void setMouseOnRowIndex(int mouseOnRowIndex) {  
        this.mouseOnRowIndex = mouseOnRowIndex;  
    }  
  
    class MyTableCellRenderer extends DefaultTableCellRenderer{  
        @Override  
        public Component getTableCellRendererComponent(JTable table,  
                Object value, boolean isSelected, boolean hasFocus,  
                int row, int column) {  
            if(row == MyTable.this.mouseOnRowIndex){  
                setBackground(Color.YELLOW);  
            }else{  
                setBackground(null);  
            }  
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,  
                    row, column);  
        }  
    }  
      
    class MyTableModel extends AbstractTableModel {  
        @Override  
        public int getRowCount() {  
            return 3;  
        }  
        @Override  
        public int getColumnCount() {  
            return 3;  
        }  
        @Override  
        public Object getValueAt(int rowIndex, int columnIndex) {  
            return rowIndex + " - " + columnIndex;  
        }  
          
    }  
  
    @Override  
    public void mouseDragged(MouseEvent e) {}  
  
    @Override  
    public void mouseMoved(MouseEvent e) {  
        MyTable table = (MyTable) e.getComponent();  
        Point point = e.getPoint();  
        int rowAtPoint = table.rowAtPoint(point);  
        System.out.println(rowAtPoint);  
        table.setMouseOnRowIndex(rowAtPoint);  
        table.updateUI();  
    }  
      
    public static void main(String[] args) {  
        JFrame frame = new JFrame();  
        frame.add(new MyTable());  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        frame.pack();  
        frame.setVisible(true);  
    }  
} 