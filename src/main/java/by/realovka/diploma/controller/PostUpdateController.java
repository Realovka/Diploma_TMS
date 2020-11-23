package by.realovka.diploma.controller;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.dto.PostUpdateDTO;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.FriendshipService;
import by.realovka.diploma.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/updatePost")
public class PostUpdateController {

    @Autowired
    private PostService postService;

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping(path = "/updatePost/{id}")
    public ModelAndView getPageUpdatePost(@PathVariable long id, ModelAndView modelAndView) {
        Post postOnPage = postService.getPostById(id);
        modelAndView.addObject("postOnPage",postOnPage);
        modelAndView.addObject("updatePost", new PostUpdateDTO());
        modelAndView.setViewName("updatepost");
        return modelAndView;
    }

    @PostMapping(path = "/updatePost/{id}")
    public ModelAndView updatePost(@ModelAttribute("updatePost") PostUpdateDTO postUpdateDTO, @PathVariable long id,
                                   @AuthenticationPrincipal User user, ModelAndView modelAndView) {
        postService.updatePost(id, postUpdateDTO);
        List<User> friends = friendshipService.getAllFriendsAuthUser(user);
        List<User> usersWhoMayBeFriends = friendshipService.getUsersWhoMayBeFriends(user);
        List<PostOnPageDTO> posts = postService.getPosts(user);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("post", new PostAddDTO());
        modelAndView.addObject("usersWhoMayBeFriends", usersWhoMayBeFriends);
        modelAndView.setViewName("account");
        return modelAndView;
    }
}
