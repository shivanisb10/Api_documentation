package com.api_documentation.bookstore.service;

import com.api_documentation.bookstore.exception.NotFoundException;
import com.api_documentation.bookstore.model.Book;

import java.util.List;

public interface BookService {

    Book addNewBook(Book book);
    List<Book> getBooks();
    Book getBook(String bookId) throws NotFoundException;
    Book removeBook(String bookId) throws NotFoundException;
    Book updateBook(Book book) throws NotFoundException;
}
