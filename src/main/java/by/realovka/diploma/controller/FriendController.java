package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.FriendshipService;
import by.realovka.diploma.service.PostService;
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
    private FriendshipService friendshipService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping(path = "/friend/{personId}")
    public ModelAndView additionToFriend(@PathVariable long personId, @AuthenticationPrincipal User user, ModelAndView modelAndView){
        User person = userService.getUserById(personId);
        friendshipService.saveFriendship(user,person);
        List<User> friends = friendshipService.getAllFriendsPerson(person.getId(), user.getId());
        if (friendshipService.getAnswerAreUserAndPersonFriends(user.getId(),person.getId())) {
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        List<PostOnPageDTO> posts = postService.getPosts(personId);
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("friends", friends);
        modelAndView.setViewName(String.format("redirect:/person/person/%s",person.getId()));
        return modelAndView;
    }
}
