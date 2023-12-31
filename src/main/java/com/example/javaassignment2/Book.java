package com.example.javaassignment2;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList;

    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new ArrayList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public void addAuthor(Author author) {
        if (!this.authorList.contains(author)) {
            this.authorList.add(author);
        }
    }

    @Override
    public String toString() {
        StringBuilder authors = new StringBuilder();
        for (Author author : authorList) {
            authors.append(author.getFirstName()).append(" ").append(author.getLastName()).append(", ");
        }
        // Remove the trailing comma and space
        if (authors.length() > 0) {
            authors.setLength(authors.length() - 2);
        }
        return "Book [ISBN: " + isbn + ", Title: " + title + ", Authors: " + authors.toString() + "]";
    }
}
