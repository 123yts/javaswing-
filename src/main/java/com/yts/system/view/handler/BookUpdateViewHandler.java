package com.yts.system.view.handler;

import com.yts.system.service.BookService;
import com.yts.system.view.MainView;
import com.yts.system.view.page.BookUpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookUpdateViewHandler implements ActionListener {

    private BookUpdateView bookUpdateView;
    private MainView mainView;

    public BookUpdateViewHandler(MainView mainView, BookUpdateView bookUpdateView){
        this.bookUpdateView = bookUpdateView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("修改".equals(text)){
            boolean result = new BookService().updateBook(bookUpdateView.getBook());
            if(result){
                mainView.reloadView();
                JOptionPane.showMessageDialog(bookUpdateView, "修改成功！");
                bookUpdateView.dispose();
            }
            else{
                JOptionPane.showMessageDialog(bookUpdateView, "修改失败！");
            }
        }
    }
}
