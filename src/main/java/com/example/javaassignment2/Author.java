package com.example.javaassignment2;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private List<Book> books;  // List of books written by author

    public Author(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (book != null && !this.books.contains(book)) {
            this.books.add(book);
        }
    }

    @Override
    public String toString() {
        return "Author [ID: " + id + ", Name: " + firstName + " " + lastName + "]";
    }
}
