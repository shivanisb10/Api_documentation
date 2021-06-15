package com.api_documentation.bookstore.controller;

import com.api_documentation.bookstore.exception.NotFoundException;
import com.api_documentation.bookstore.model.Book;
import com.api_documentation.bookstore.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books", produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Retrieve all the Books available in Store", response = List.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of Books in response", response = List.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<List<Book>> getBooks(
    ) {
        List<Book> response = null;
        try{
            response = bookService.getBooks();
           return  ResponseEntity.ok().body(response);
        }catch(Exception e){
            logger.error("Exception while retrieving book records: {}",e );
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping(value = "/books", produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Add a book to the Book Store", response = Book.class, responseContainer = "Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New Book Added", response = Book.class, responseContainer = "Book"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Book> addBooks(@RequestBody Book book) {
     Book response;
        try{
         response = bookService.addNewBook(book);
         return ResponseEntity.ok().body(response);
     }catch(Exception e){
         logger.error("Exception while Adding a book: {}",e );
         return ResponseEntity.status(500).body(null);
     }
    }

    @GetMapping(value = "/books/{bookId}", produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Get a book by it's Id", response = Book.class, responseContainer = "Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book retrieved by bookId", response = Book.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Book> getBookById(
            @ApiParam(value = "Id to get Book") @PathVariable("bookId") String bookId
    ) {
        Book response;
        try{
            response = bookService.getBook(bookId);
            return ResponseEntity.ok().body(response);
        }catch(NotFoundException e){
            logger.error("Not Found Exception: {}",e );
            return ResponseEntity.status(404).body(null);
        }
        catch(Exception e){
            logger.error("Exception while Retrieving a book by Id: {}",e );
            return ResponseEntity.status(500).body(null);
        }

    }

    @PutMapping(value = "/books", produces = {"application/json", "application/xml"})
    @ApiOperation(value = "update a book to the Book Store", response = Book.class, responseContainer = "Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated Book Record", response = Book.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {

        Book response;
        try{
            response = bookService.updateBook(book);
            return ResponseEntity.ok().body(response);
        }catch(NotFoundException e){
            logger.error("Not Found Exception: {}",e );
            return ResponseEntity.status(404).body(null);
        }catch(Exception e){
            logger.error("Exception while Updating book by Id: {}",e );
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping(value = "/books/{bookId}", produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Delete a book from Book Store", response = Book.class, responseContainer = "Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Deleted from the store", response = Book.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Book> deleteBook(
            @ApiParam(value = "Id of the Book") @PathVariable("bookId") String bookId
    ) {
        Book response;
        try{
            response = bookService.removeBook(bookId);
            return ResponseEntity.ok().body(response);
        }catch(NotFoundException e){
            logger.error("Not Found Exception: {}",e );
            return ResponseEntity.status(404).body(null);
        }catch(Exception e){
            logger.error("Exception while Updating book by Id: {}",e );
            return ResponseEntity.status(500).body(null);
        }
    }
}
