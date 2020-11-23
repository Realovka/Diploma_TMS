package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.*;
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

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private LikeService likeService;

    @GetMapping(path = "/person/{id}")
    public ModelAndView getPersonPage(@PathVariable("id") long id, @AuthenticationPrincipal User user, ModelAndView modelAndView){
        User person = userService.getUserById(id);
        List<User> friends = friendshipService.getAllFriendsPerson(person, user);
        List<PostOnPageDTO> posts = postService.getPosts(person);
        if(friendshipService.getAnswerAreUserAndPersonFriends(user,person)){
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
        commentService.addComment(postId,  user, commentAddDTO);
        User person = userService.getUserById(id);
        List<User> friends = friendshipService.getAllFriendsPerson(person, user);
        List<PostOnPageDTO> posts = postService.getPosts(person);
        if(friendshipService.getAnswerAreUserAndPersonFriends(user,person)){
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s", id));
        return modelAndView;

    }
}
