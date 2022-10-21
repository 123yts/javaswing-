package com.yts.system.service;

import com.yts.system.dao.BookDao;
import com.yts.system.model.Book;

import java.util.List;
import java.util.Vector;

public class BookService {

    BookDao bookDao = new BookDao();

    //测试通过！
    public Vector<Vector<Object>> getBookListByName(String name, int pageNow, int pageSize){
        //if (name == null || "".equals(name.trim())) return null;
        if (name == null) return null;
        List<Book> bookList = bookDao.getBookListByName(name, pageNow, pageSize);
        Vector<Vector<Object>> data = new Vector<>();
        for (Book book : bookList) {
            Vector<Object> item = new Vector<>();
            item.addElement(book.getNumber());
            item.addElement(book.getName());
            item.addElement(book.getAuthor());
            item.addElement(book.getPublisher());
            item.addElement(book.getPrice());
            item.addElement(book.isBorrowed());
            data.addElement(item);
        }
        return data;
    }

    public boolean addBook(Book book){
        return bookDao.insertBook(book);
    }

    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    public Book getBookByNumber(int number){
        return bookDao.getBookByNumber(number);
    }

    public boolean deleteBook(int[] numbers){
        return bookDao.deleteBook(numbers);
    }

    public int getBookTotalCountByName(String name){
        return bookDao.getBookTotalCountByName(name);
    }


    public static void main(String[] args) {
        //new BookService().getBookListByName("软件").forEach(item -> item.forEach(System.out::println));
    }


}
