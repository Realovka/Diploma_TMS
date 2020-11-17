package by.realovka.diploma.controller;

import by.realovka.diploma.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HelloController {

    @GetMapping
    public ModelAndView getHelloPage(@AuthenticationPrincipal User user, ModelAndView modelAndView) {
        modelAndView.addObject("user" , user);
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
