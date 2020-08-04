package com.imran.security.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Document("Books")
@Getter
@Setter
public class Books
{
    @Id
    private String id;
    private String title;
    private int price;
}
