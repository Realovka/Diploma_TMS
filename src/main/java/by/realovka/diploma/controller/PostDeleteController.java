package by.realovka.diploma.controller;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
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
@RequestMapping(path = "/deletePost")
public class PostDeleteController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/deletePost/{id}")
    public ModelAndView deletePost(@AuthenticationPrincipal User user,@PathVariable long id, ModelAndView modelAndView){
        postService.deletePost(id);
        List<User> allUsers = userService.getAllUsersWithoutAuthUser(user);
        List<Post> posts = postService.findAllPosts(user);
        modelAndView.addObject("allUsers", allUsers);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("post", new PostAddDTO());
        modelAndView.setViewName("account");
        return modelAndView;
    }
}
