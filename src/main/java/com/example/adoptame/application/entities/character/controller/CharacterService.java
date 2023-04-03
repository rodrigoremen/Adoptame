package com.example.adoptame.application.entities.character.controller;

import com.example.adoptame.application.entities.character.model.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

}
