package com.yts.system.dao;

import com.yts.system.model.Book;
import com.yts.system.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BookDao {

   private Connection conn = null;
   private PreparedStatement pst = null;
   private ResultSet rs = null;

    public boolean insertBook(Book book){
        try {
            conn = JdbcUtils.getConnection();
            //sql语句
            String sql = "insert into book (number, name, author, publisher, " +
                    "price, borrowed) values ( ?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, book.getNumber());
            pst.setString(2,"" + book.getName());
            pst.setString(3,"" + book.getAuthor());
            pst.setString(4,"" + book.getPublisher());
            pst.setDouble(5, book.getPrice());
            pst.setInt(6, book.isBorrowed() ? 1 : 0);
            return pst.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            System.out.println("数据插入失败！");
            throwables.printStackTrace();
        }
        finally {
            //释放资源
            JdbcUtils.release(conn, pst, rs);
        }
        return false;
    }

    public boolean deleteBook(int[] numbers){
        try {
            conn = JdbcUtils.getConnection();
            StringBuffer sql = new StringBuffer("delete from book where number in (");
            int length = numbers.length;
            for (int i = 0; i < length; i++) {
                if (i == length - 1) sql.append('?');
                else sql.append("?, ");
            }
            sql.append(')');
            pst = conn.prepareStatement(sql.toString());
            for (int i = 0; i < length; i++) {
                pst.setInt(i+1, numbers[i]);
            }
            return pst.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            System.out.println("数据删除失败！");
            throwables.printStackTrace();
        }
        finally {
            //释放资源
            JdbcUtils.release(conn, pst, rs);
        }

        return false;
    }

    public boolean updateBook(Book book){
        try {
            conn = JdbcUtils.getConnection();
            //sql语句
            String sql = "update book set name=?, author=?, publisher=?, price=?, borrowed=? where number = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,"" + book.getName());
            pst.setString(2,"" + book.getAuthor());
            pst.setString(3,"" + book.getPublisher());
            pst.setDouble(4, book.getPrice());
            pst.setInt(5, book.isBorrowed() ? 1 : 0);
            pst.setInt(6, book.getNumber());
            return pst.executeUpdate() > 0 ? true : false;
        } catch (SQLException throwables) {
            System.out.println("数据更新失败！");
            throwables.printStackTrace();
        }
        finally {
            //释放资源
            JdbcUtils.release(conn, pst, rs);
        }
        return false;
    }

    public Book getBookByNumber(int number){
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from book where number = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,  number);
            rs = pst.executeQuery();
            if(rs.next()){
                return new Book(rs.getInt("number"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDouble("price"),
                        "0".equals(rs.getString("borrowed")) ? false : true);
            }
        } catch (SQLException throwables) {
            System.out.println("数据获取失败！");
            throwables.printStackTrace();
        }
        finally {
            //释放资源
            JdbcUtils.release(conn, pst, rs);
        }
        return null;
    }

    public List<Book> getBookList(){
        List<Book> bookList = new LinkedList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from book";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                Book book = new Book(rs.getInt("number"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDouble("price"),
                        "0".equals(rs.getString("borrowed")) ? false : true);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            //释放资源
            JdbcUtils.release(conn, pst, rs);
        }
        return bookList;
    }

    public List<Book> getBookListByName(String name, int pageNow, int pageSize){
        List<Book> bookList = new LinkedList<>();
        try {
            conn = JdbcUtils.getConnection();
            int start = (pageNow - 1) * pageSize;
            StringBuffer sql = new StringBuffer("select * from book where name like ? limit ");
            sql.append(start).append(", ").append(pageSize);
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, "%" + name + "%");
            rs = pst.executeQuery();
            while (rs.next()){
                Book book = new Book(rs.getInt("number"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDouble("price"),
                        "0".equals(rs.getString("borrowed")) ? false : true);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            System.out.println("数据获取失败！");
            throwables.printStackTrace();
        }
        finally {
            //释放资源
            JdbcUtils.release(conn, pst, rs);
        }
        return bookList;
    }

    public int getBookTotalCountByName(String name){
        try {
            conn = JdbcUtils.getConnection();
            StringBuffer sql = new StringBuffer("select count(*) from book where name like ?");
            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, "%"+name+"%");
            rs = pst.executeQuery();
            if (rs.next()){
                return rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            System.out.println("数据获取失败！");
            throwables.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        Book book = new Book(1004, "软件工程", "李四", "电子工业出版社", 108.5, true);
        //System.out.println(bookDao.getBookListByName("软件工程"));
        System.out.println("=======================");
        System.out.println(bookDao.getBookByNumber(1001));
        //bookDao.getBookList().forEach(System.out::println);
        //System.out.println(bookDao.insertBook(book));
        //System.out.println(bookDao.deleteBook(1003));
        System.out.println("=======================");
        bookDao.getBookList().forEach(System.out::println);
        //System.out.println(bookDao.getBookByNumber(1003));
    }
}
