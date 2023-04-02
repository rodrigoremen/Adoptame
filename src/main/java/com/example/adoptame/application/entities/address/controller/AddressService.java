package com.example.adoptame.application.entities.address.controller;

import com.example.adoptame.application.entities.address.model.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
}
