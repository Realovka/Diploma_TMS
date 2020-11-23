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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/deletePost")
public class PostDeleteController {
    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/deletePost/{id}")
    public ModelAndView deletePost(@AuthenticationPrincipal User user,@PathVariable long id, ModelAndView modelAndView){
        postService.deletePost(id);
        List<User> friends = friendshipService.getAllFriendsAuthUser(user);
        List<User> usersWhoMayBeFriends = friendshipService.getUsersWhoMayBeFriends(user);
        List<PostOnPageDTO> posts = postService.getPosts(user);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("usersWhoMayBeFriends", usersWhoMayBeFriends);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("post", new PostAddDTO());
        modelAndView.setViewName("account");
        return modelAndView;
    }
}
