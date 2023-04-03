package com.example.adoptame.application.entities.color.controller;

import com.example.adoptame.application.entities.color.model.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;
}
