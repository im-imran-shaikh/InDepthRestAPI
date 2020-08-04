package com.imran.security.service;

import com.imran.security.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserCredentialsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Function<UserCredential,UserCredential> addUsers = userCredential -> {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        return mongoTemplate.save(userCredential, "userCredential");
    };


    public Supplier<List<UserCredential>> getAllUserCredentials = ()->
            mongoTemplate.findAll(UserCredential.class, "userCredential");

    public Function<String, UserCredential> getUser = username -> {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, UserCredential.class, "userCredential");
    };
}
