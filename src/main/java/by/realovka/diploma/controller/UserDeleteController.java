package by.realovka.diploma.controller;

import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/deleteUser")
public class UserDeleteController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/deleteUser")
    public ModelAndView userDelete(@AuthenticationPrincipal User user, HttpSession httpSession,ModelAndView modelAndView){
        userService.setUserDeleted(user);
        httpSession.invalidate();
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
