package com.yts.system.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel{

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("书号");
        columns.addElement("书名");
        columns.addElement("作者");
        columns.addElement("出版社");
        columns.addElement("价格");
        columns.addElement("借阅");

    }

    private MainViewTableModel(){
        super(null, columns);
    }

    public static MainViewTableModel mainViewTableModel = new MainViewTableModel();

    public static MainViewTableModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data, columns);
        return mainViewTableModel;
    }

    public static void updateData(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
