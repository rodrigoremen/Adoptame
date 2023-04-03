package com.example.adoptame.application.entities.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DonationController {
    @Autowired
    private DonationService donationService;

}
