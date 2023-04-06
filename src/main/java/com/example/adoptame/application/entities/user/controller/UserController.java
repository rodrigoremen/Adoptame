package com.example.adoptame.application.entities.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String USER = "redirect:/user/";
    private static final String LOGIN = "redirect:/login";
    private static final String USERFORM = "views/user/userForm";
    private static final String SMSERROR = "msg_error";
    private static final String SMSSUCCESS = "msg_success";


}
