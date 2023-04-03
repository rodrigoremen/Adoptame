package com.example.adoptame.application.entities.pet.controller;

import com.example.adoptame.application.entities.pet.model.PetImage;
import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetImagesService {
    @Autowired
    private EntityRepository<PetImage,Long>petImageRepository;
}
