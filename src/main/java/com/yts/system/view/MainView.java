package com.yts.system.view;

import com.yts.system.service.BookService;
import com.yts.system.view.ext.MainViewTable;
import com.yts.system.view.ext.MainViewTableModel;
import com.yts.system.view.handler.MainViewHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;


public class MainView extends JFrame {

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("添加");
    JButton updateBtn = new JButton("修改");
    JButton deleteBtn = new JButton("删除");
    JTextField searchText = new JTextField(20);
    JButton searchBtn = new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable = new MainViewTable();
    MainViewHandler mainViewHandler = new MainViewHandler(this);
    BookService bookService = new BookService();

    //当前页数
    private int pageNow = 1;
    //总页数
    private int pageTotalCount = 1;
    //每页显示数据条数
    private int pageSize = 10;

    public MainView(){
        super("图书管理系统");
        Container contentPane = getContentPane();
        //（北边）图书操作组件
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        deleteBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(deleteBtn);
        northPanel.add(searchText);
        northPanel.add(searchBtn);
        contentPane.add(northPanel, BorderLayout.NORTH);
        //（南边）分页按钮
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        showPageBtn(bookService.getBookTotalCountByName(""));
        //中部组件，表格
        Vector<Vector<Object>> data = bookService.getBookListByName("", pageNow, pageSize);

        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
        //图标
        URL imgUrl = MainView.class.getClassLoader().getResource("image/bookIcon.jpeg");
        setIconImage(new ImageIcon(imgUrl).getImage());
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    private void showPageBtn(int totalCount){
        System.out.println(totalCount);
        //没数据不显示按钮
        if (totalCount == 0) {
            preBtn.setVisible(false);
            nextBtn.setVisible(false);
            return;
        }
        if (pageNow == 1) preBtn.setVisible(false);
        else preBtn.setVisible(true);
        if (totalCount % pageSize == 0) pageTotalCount = totalCount / pageSize;
        else pageTotalCount = totalCount / pageSize + 1;
        if (pageNow == pageTotalCount) nextBtn.setVisible(false);
        else nextBtn.setVisible(true);
    }

    public void reloadView(){
        String name = searchText.getText();
        showPageBtn(bookService.getBookTotalCountByName(name));
        Vector<Vector<Object>> data = bookService.getBookListByName(name, pageNow, pageSize);
        MainViewTableModel.updateData(data);
        mainViewTable.renderRule();
    }

    public int[] getSelectedBookNumbers(){
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] numbers = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            numbers[i] = Integer.parseInt(mainViewTable.getValueAt(selectedRows[i], 0).toString());
        }
        return numbers;
    }

    public static void main(String[] args) {
        new MainView();
    }
}
