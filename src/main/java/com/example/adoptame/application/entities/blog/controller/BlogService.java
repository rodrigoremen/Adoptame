package com.example.adoptame.application.entities.blog.controller;

import com.example.adoptame.application.entities.blog.model.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
}
