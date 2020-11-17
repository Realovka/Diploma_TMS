package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.CommentRepository;
import by.realovka.diploma.service.CommentService;
import by.realovka.diploma.service.PostService;
import by.realovka.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/person")
public class PersonController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping(path = "/person/{id}")
    public ModelAndView getPersonPage(@PathVariable("id") long id, @AuthenticationPrincipal User user, ModelAndView modelAndView){
        User person = userService.getUserById(id);
        List<User> friends = userService.getFriends(person);
        List<Post> posts = postService.findAllPosts(person);
        if(userService.getAnswerIsPersonUserFriend(user,person)){
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("person");
        return modelAndView;

    }


    @PostMapping(path = "/person/{id}")
    public ModelAndView postPersonPage(@ModelAttribute("comment") CommentAddDTO commentAddDTO,@AuthenticationPrincipal User user,
                                       @PathVariable ("id") long id, @RequestParam("postId") long postId,
                                       ModelAndView modelAndView){
        User person = userService.getUserById(id);
        commentService.addComment(postId,user.getUsername(), person.getUsername(), commentAddDTO);
        User personWhoWritePost = userService.getUserById(id);
        List<Post> posts = postService.findAllPosts(personWhoWritePost);//TODO
        List<User> friends = userService.getFriends(personWhoWritePost);
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", personWhoWritePost);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("person");
        return modelAndView;

    }
}
