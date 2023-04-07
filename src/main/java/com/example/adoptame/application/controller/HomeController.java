package com.example.adoptame.application.controller;

import com.example.adoptame.application.entities.blog.controller.BlogService;
import com.example.adoptame.application.entities.donation.controller.DonationService;
import com.example.adoptame.application.entities.person.model.Person;
import com.example.adoptame.application.entities.pet.controller.PetAdoptedService;
import com.example.adoptame.application.entities.pet.controller.PetService;
import com.example.adoptame.application.entities.role.model.Role;
import com.example.adoptame.application.entities.role.model.RoleRepository;
import com.example.adoptame.application.entities.user.controller.UserService;
import com.example.adoptame.application.entities.user.model.User;
import com.example.adoptame.application.entities.user.model.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private PetService petService;
    @Autowired
    private PetAdoptedService petAdoptedService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private DonationService donationService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public String index() {
        boolean isADmin = true;

        Optional<User> existingUser  = userRepository.findByUsername("ejemplo@utez.edu.mx");

        if(existingUser.isPresent()) {
            return "index";
        }
        User user = new User();
        user.setUsername("ejemplo@utez.edu.mx");
        user.setPassword(passwordEncoder().encode("pass"));
        user.setIsActive(true);

        Role role = new Role("ROLE_ADMIN");
        roleRepository.save(role);

        user.addRoles();
        user.getRoles().add(role);

        userRepository.save(user);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, Person person, RedirectAttributes redirectAttributes) {
        model.addAttribute("person", person);
        return "login";
    }

//    @GetMapping("/login-error")
//    public String login(HttpServletRequest request, Model model, Person person) {
//        HttpSession session = request.getSession(false);
//        String errorMessage = null;
//        if (session != null) {
//            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            if (ex != null) {
//                errorMessage = ex.getMessage();
//            }
//        }
//        model.addAttribute("errorMessage", errorMessage);
//        return "views/auth/login";
//    }

    @GetMapping("/about")
    public String about() {
        return "views/about";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication, HttpSession httpSession) {
        String username = authentication.getName();
//        Optional<User> user = userService.findByEmail(username);
//        user.ifPresent(value->httpSession.setAttribute("user",value));


        return "views/dashboard";
    }

    @GetMapping("/noscript")
    public String noscript() {
        return "views/errorpages/noscript";
    }

    @GetMapping("/logs")
    public String logs(Model model) {
//        model.addAttribute("list", logService.findAll());
        return "views/logs";
    }

    @GetMapping("/{tiny}")
    public String tiny() {
        return "tinyMce";
    }
}
