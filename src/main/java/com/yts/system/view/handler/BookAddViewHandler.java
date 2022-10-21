package com.yts.system.view.handler;

import com.yts.system.model.Book;
import com.yts.system.service.BookService;
import com.yts.system.view.MainView;
import com.yts.system.view.page.BookAddView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookAddViewHandler implements ActionListener {

    private BookAddView bookAddView;
    private MainView mainView;

    public BookAddViewHandler(MainView mainView, BookAddView bookAddView){
        this.bookAddView = bookAddView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("添加".equals(text)){
            BookService bookService = new BookService();
            Book book = bookAddView.getBook();
            if (bookService.getBookByNumber(book.getNumber()) != null){
                JOptionPane.showMessageDialog(bookAddView, "该书已收录在系统中，请勿重复添加！");
                return;
            }
            boolean result = new BookService().addBook(book);
            if(result){
                //刷新主界面
                mainView.reloadView();
                JOptionPane.showMessageDialog(bookAddView, "添加成功！");
                bookAddView.dispose();
            }
            else{
                JOptionPane.showMessageDialog(bookAddView, "添加失败！");
            }
        }
    }
}
