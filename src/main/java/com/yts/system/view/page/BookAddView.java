package com.yts.system.view.page;

import com.yts.system.model.Book;
import com.yts.system.view.MainView;
import com.yts.system.view.handler.BookAddViewHandler;

import javax.swing.*;
import java.awt.*;

public class BookAddView extends JDialog{

    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    JLabel numberLable = new JLabel("书号", JLabel.RIGHT);
    JTextField numberText = new JTextField();
    JLabel nameLable = new JLabel("书名", JLabel.RIGHT);
    JTextField nameText = new JTextField();
    JLabel authorLable = new JLabel("作者", JLabel.RIGHT);
    JTextField authorText = new JTextField();
    JLabel publisherLable = new JLabel("出版社", JLabel.RIGHT);
    JTextField publisherText = new JTextField();
    JLabel priceLable = new JLabel("价格", JLabel.RIGHT);
    JTextField priceText = new JTextField();
    JLabel borrowedLable = new JLabel("借阅(0-在馆/1-借出)", JLabel.RIGHT);
    JTextField borrowedText = new JTextField();

    JButton addButton = new JButton("添加");

    public BookAddView(MainView mainView){
        super(mainView,"添加图书",true);

        //设置监听器
        BookAddViewHandler bookAddViewHandler = new BookAddViewHandler(mainView, this);
        addButton.addActionListener(bookAddViewHandler);

        numberLable.setPreferredSize(new Dimension(150, 30));
        jPanel.add(numberLable);
        numberText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(numberText);
        nameLable.setPreferredSize(new Dimension(150, 30));
        jPanel.add(nameLable);
        nameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameText);
        authorLable.setPreferredSize(new Dimension(150, 30));
        jPanel.add(authorLable);
        authorText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(authorText);
        publisherLable.setPreferredSize(new Dimension(150, 30));
        jPanel.add(publisherLable);
        publisherText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(publisherText);
        priceLable.setPreferredSize(new Dimension(150, 30));
        jPanel.add(priceLable);
        priceText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(priceText);
        borrowedLable.setPreferredSize(new Dimension(150, 30));
        jPanel.add(borrowedLable);
        borrowedText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(borrowedText);
        jPanel.add(addButton);

        Container container = getContentPane();
        container.add(jPanel);

        setSize(400, 500);
        setLocationRelativeTo(null);
        //销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public Book getBook(){
        Book book = new Book();
        book.setNumber(Integer.parseInt(numberText.getText().trim()));
        book.setName(nameText.getText().trim());
        book.setAuthor(authorText.getText().trim());
        book.setPublisher(publisherText.getText().trim());
        book.setPrice(Double.parseDouble(priceText.getText()));
        book.setBorrowed("1".equals(borrowedText.getText()));
        return book;
    }

}
