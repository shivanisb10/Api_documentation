package com.api_documentation.bookstore.service.impl;

import com.api_documentation.bookstore.exception.NotFoundException;
import com.api_documentation.bookstore.model.Book;
import com.api_documentation.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    //we can integrate this with db, but as we only want to see swagger demo, we put data in map
    private final Map<String, Book> bookStore = new HashMap<>();

    @Override
    public Book addNewBook(Book book) {

        logger.debug("Add new book to the store");
        String id = UUID.randomUUID().toString();

        bookStore.put(id, book);
        logger.debug("Total Books Present {}", bookStore.size());

        return book;
    }

    @Override
    public List<Book> getBooks() {
        logger.debug("Get all books from the Store");
        List<Book> books = new ArrayList<>();
        bookStore.forEach((id, book) -> books.add(book));
        logger.debug("Total Books Present {}", bookStore.size());
        return books;
    }

    @Override
    public Book getBook(String bookId) throws NotFoundException {
        logger.debug("Get Book using Id from the Store");
        Book book = null;
        if (bookStore.containsKey(bookId)) {
            book = bookStore.get(bookId);
            logger.debug("Requested Book exists");
        } else {
            book = new Book();
            logger.debug("Requested Book does not exists");
            throw new NotFoundException("Record is Not Found");
        }
        return book;
    }

    @Override
    public Book removeBook(String bookId) throws NotFoundException {
        logger.debug("Remove employee from the employee store");
        Book book = null;
        if (bookStore.containsKey(bookId)) {
            book = bookStore.remove(bookId);
            logger.debug("Book removed");
        } else {
            book = new Book();
            logger.debug("Book does not exists");
            throw new NotFoundException("Record to be removed is Not Found");
        }
        return book;
    }

    @Override
    public Book updateBook(Book book) throws NotFoundException {
        logger.debug("Update a Book Record in bookStore");
        Book updatedBook = null;
        if (bookStore.containsKey(book.getId())) {
            updatedBook = book;
            removeBook(book.getId());
            bookStore.put(book.getId(), book);
            logger.debug("Employee updated");
        } else {
            logger.debug("The Book Sent for Update Does not exist");
            throw new NotFoundException("Record to be updated is Not Found");
        }
        return updatedBook;
    }
}