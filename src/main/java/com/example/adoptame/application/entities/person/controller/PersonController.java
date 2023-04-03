package com.example.adoptame.application.entities.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

}
