package com.library.vlad.Model;

public class Book {
    private String name;
    private String author;
    private boolean borrowedNow;

    public Book() {

    }


    public class bookBorrowedException extends Exception {
        public bookBorrowedException(String s) {
            super(s);
        }

    }

    public void borrowBook() throws bookBorrowedException {

        if (this.borrowedNow == true) {
            throw new bookBorrowedException("The book " + this.getName() + " by " + this.getAuthor() + " is borrowed for the moment!");
        } else
            borrowedNow = true;

    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        borrowedNow = false;

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

    public boolean isBorrowedNow() {
        return borrowedNow;
    }

    public void setBorrowedNow(boolean borrowedNow) {
        this.borrowedNow = borrowedNow;
    }
}
