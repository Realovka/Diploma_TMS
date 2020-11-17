package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/friend")
public class FriendController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/friend/{id}")
    public ModelAndView additionToFriend(@PathVariable long id, @AuthenticationPrincipal User user, ModelAndView modelAndView){
        userService.addPersonToFriend(id,user);
        User person = userService.getUserById(id);
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("authUser", user);
        modelAndView.setViewName("person");
        return modelAndView;
    }
}
