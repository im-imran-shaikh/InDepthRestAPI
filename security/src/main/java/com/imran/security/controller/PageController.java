package com.imran.security.controller;

import com.imran.security.jwt.UsernamePassword;
import com.imran.security.model.Books;
import com.imran.security.model.UserCredential;
import com.imran.security.service.BookService;
import com.imran.security.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private UserCredentialsService userCredentialsService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("getMessage/{message}")
    public String getMessage(@PathVariable String message)
    {
         return message;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("addBooks")
    public Books addBook(@RequestBody Books book)
    {
        System.out.println("add books in controller");
        return bookService.addBook(book);
    }

    @GetMapping("getAllBooks")
    public List<Books> getAllBooks()
    {
        System.out.println("getAll books in controller");
        return bookService.getAllBooks();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("addUser")
    public UserCredential addUser(@RequestBody  UserCredential userCredential)
    {
        return userCredentialsService.addUsers.apply(userCredential);
    }

    @GetMapping("getAllUser")
    public List<UserCredential> getAllUser()
    {
        return userCredentialsService.getAllUserCredentials.get();
    }

    @GetMapping("successfull")
    public String defaultHomePage()
    {
        return "Welcome to imran home page";
    }
}
