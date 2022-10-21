package com.yts.system.view.handler;

import com.yts.system.service.BookService;
import com.yts.system.view.MainView;
import com.yts.system.view.page.BookAddView;
import com.yts.system.view.page.BookUpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {

    private MainView mainView;

    public MainViewHandler(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("添加".equals(text)){
            new BookAddView(mainView);
        }
        else if ("修改".equals(text)){
            if (mainView.getSelectedBookNumbers().length != 1){
                JOptionPane.showMessageDialog(mainView, "一次只能修改一条数据！");
            }else{
                new BookUpdateView(mainView, mainView.getSelectedBookNumbers()[0]);
            }
        }
        else if ("删除".equals(text)){
            int[] numbers = mainView.getSelectedBookNumbers();
            if (numbers.length <= 0){
                JOptionPane.showMessageDialog(mainView, "请选择要删除的几行数据！");
                return;
            }
            int option = JOptionPane.showConfirmDialog(mainView,"确定要删除这" + numbers.length + "行数据吗？",
                    "确认删除", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                boolean result = new BookService().deleteBook(numbers);
                if (result){
                    JOptionPane.showMessageDialog(mainView, "删除成功!");
                    mainView.reloadView();
                }else {
                    JOptionPane.showMessageDialog(mainView, "删除失败!");
                }
            }
        }
        else if ("查询".equals(text)){
            //从第一页开始查
            mainView.setPageNow(1);
            mainView.reloadView();
        }
        else if("上一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadView();
        }
        else if("下一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadView();
        }
    }
}
