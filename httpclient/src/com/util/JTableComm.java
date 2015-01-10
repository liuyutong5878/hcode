package com.util;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * @ClassName JTableComm.java
 * @Description 显示隐藏列
 *
 * @author 1904
 * @version V1.0 
 * @Date 2014年12月29日 下午1:01:41
 */
public class JTableComm {
   
	/**
	 * 隐藏列
	 * @param table
	 * @param column
	 */
    public static void HiddenCell(JTable table, int column) {
        TableColumn tc = table.getTableHeader().getColumnModel().getColumn(column);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(0);
    }
   
    /**
     * 显示列
     * @param table
     * @param column
     * @param width
     */
    public static void showColumn(JTable table, int column, int width) {
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setMaxWidth(width);
        tc.setPreferredWidth(width);
        tc.setWidth(width);
        tc.setMinWidth(width);
        table.getTableHeader().getColumnModel().getColumn(column).setMaxWidth(width);
        table.getTableHeader().getColumnModel().getColumn(column).setMinWidth(width);
    }
}