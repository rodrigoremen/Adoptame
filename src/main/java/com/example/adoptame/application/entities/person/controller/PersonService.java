package com.example.adoptame.application.entities.person.controller;

import com.example.adoptame.application.entities.person.model.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
}
