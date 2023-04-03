package com.example.adoptame.application.entities.tag.controller;

import com.example.adoptame.application.entities.tag.model.Tag;
import com.example.adoptame.application.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private EntityRepository<Tag,Long> tagRepository;
}
