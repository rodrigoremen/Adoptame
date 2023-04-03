package com.example.adoptame.application.entities.pet.controller;

import com.example.adoptame.application.entities.pet.model.PetAdoptedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetAdoptedService {
    @Autowired
    private PetAdoptedRepository petAdoptedRepository;
}
