package by.realovka.diploma.controller;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.FriendshipService;
import by.realovka.diploma.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private PostService postService;

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping(path = "/account")
    public ModelAndView getHomePageForUser(@AuthenticationPrincipal User user, ModelAndView modelAndView){
        List<PostOnPageDTO> posts = postService.getPosts(user);
        List<User> friends = friendshipService.getAllFriendsAuthUser(user);
        List<User> usersWhoMayBeFriends = friendshipService.getUsersWhoMayBeFriends(user);
        modelAndView.addObject("usersWhoMayBeFriends", usersWhoMayBeFriends);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("post", new PostAddDTO());
        modelAndView.setViewName("account");
       return modelAndView;
    }

    @PostMapping(path = "/account")
    public ModelAndView addPost(@AuthenticationPrincipal User user, @ModelAttribute("post") PostAddDTO postAddDTO, ModelAndView modelAndView){
        List<User> usersWhoMayBeFriends = friendshipService.getUsersWhoMayBeFriends(user);
        List<User> friends = friendshipService.getAllFriendsAuthUser(user);
        postService.addPost(user,postAddDTO);
        List<PostOnPageDTO> posts = postService.getPosts(user);
        modelAndView.addObject("usersWhoMayBeFriends", usersWhoMayBeFriends);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("post", new PostAddDTO());
        modelAndView.setViewName("account");
        return modelAndView;
    }
}
