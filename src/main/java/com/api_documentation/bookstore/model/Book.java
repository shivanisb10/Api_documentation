package com.api_documentation.bookstore.model;

public class Book {

    private String id;
    private String author;
    private String title;
    private String description;

    public Book(String id, String author, String title, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
    }
    public Book(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
