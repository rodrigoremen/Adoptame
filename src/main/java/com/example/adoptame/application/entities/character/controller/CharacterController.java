package com.example.adoptame.application.entities.character.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CharacterController {
    @Autowired
    private CharacterService characterService;

}
