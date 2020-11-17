package by.realovka.diploma.controller;

import by.realovka.diploma.dto.PostAddDTO;
import by.realovka.diploma.dto.PostUpdateDTO;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.PostService;
import by.realovka.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/updatePost")
public class PostUpdateController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping (path = "/updatePost/{id}")
    public ModelAndView getPageUpdatePost(@PathVariable long id, ModelAndView modelAndView){
        Optional<Post> post = postService.findPost(id);
        if(post.isPresent()) {
            Post postOnPage = post.get();
            modelAndView.addObject("postOnPage", postOnPage);
            modelAndView.addObject("updatePost", new PostUpdateDTO());
        }
        modelAndView.setViewName("updatepost");
        return modelAndView;
    }

    @PostMapping(path = "/updatePost/{id}")
    public ModelAndView updatePost(@ModelAttribute("updatePost")PostUpdateDTO postUpdateDTO,@PathVariable long id,
                                   @AuthenticationPrincipal User user, ModelAndView modelAndView){
        postService.updatePost(id,postUpdateDTO);
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
