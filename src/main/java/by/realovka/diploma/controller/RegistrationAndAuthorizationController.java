package by.realovka.diploma.controller;

import by.realovka.diploma.dto.UserRegDTO;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.UserRepository;
import by.realovka.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping(path = "/home")
public class RegistrationAndAuthorizationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/reg")
    public ModelAndView getRegistrationPage(ModelAndView modelAndView){
        modelAndView.addObject("userReg", new UserRegDTO());
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @PostMapping(path = "/reg")
        public ModelAndView postRegistrationUser(@ModelAttribute("userReg") UserRegDTO userRegDTO, ModelAndView modelAndView ){
        userService.registrationUser(userRegDTO);
        modelAndView.setViewName("hello");
        return  modelAndView;

    }

    @GetMapping(path = "/auth")
    public ModelAndView getAuthorizationPage(ModelAndView modelAndView){
        modelAndView.setViewName("auth");
        return modelAndView;
    }

}
