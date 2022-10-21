package com.yts.system.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {
    public MainViewTable(){
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.black);
        setGridColor(Color.BLACK);
        setRowHeight(30);
    }

    public void renderRule(){
        Vector<String> columns = MainViewTableModel.getColumns();
        MainViewCellRender render = new MainViewCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
        }

    }
}
