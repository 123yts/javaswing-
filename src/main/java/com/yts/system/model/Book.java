package com.yts.system.model;

public class Book {
    private int number;
    private String name;
    private String author;
    private String publisher;
    private double price;
    private boolean borrowed;

    public Book() {
    }

    public Book(int number, String name, String author, String publisher, double price, boolean borrowed) {
        this.number = number;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.borrowed = borrowed;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Book book = (Book) o;
//        return number == book.number && Double.compare(book.price, price) == 0 && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(number);
//    }

    @Override
    public String toString() {
        return "Book{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price + '\'' +
                ", borrowed=" + borrowed +
                '}';
    }
}
