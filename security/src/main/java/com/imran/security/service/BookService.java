package com.imran.security.service;

import com.imran.security.model.Books;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;


@Service
public class BookService
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public Books addBook(Books book)
    {
        System.out.println("getAll books in service");
        return mongoTemplate.insert(book,"Books");
    }

    public List<Books> getAllBooks()
    {
        System.out.println("getAll books in service");
        return mongoTemplate.findAll(Books.class, "Books");
    }

}
