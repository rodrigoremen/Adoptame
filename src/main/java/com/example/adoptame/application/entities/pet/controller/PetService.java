package com.example.adoptame.application.entities.pet.controller;

import com.example.adoptame.application.entities.pet.model.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

}
