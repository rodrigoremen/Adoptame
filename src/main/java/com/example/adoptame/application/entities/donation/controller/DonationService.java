package com.example.adoptame.application.entities.donation.controller;

import com.example.adoptame.application.entities.donation.model.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

}
