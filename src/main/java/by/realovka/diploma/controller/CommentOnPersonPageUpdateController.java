package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.CommentUpdateDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.CommentService;
import by.realovka.diploma.service.FriendshipService;
import by.realovka.diploma.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/updateComment")
public class CommentOnPersonPageUpdateController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private PostService postService;

    @GetMapping(path = "/updateComment/{id}")
    public ModelAndView getUpdateComment(@PathVariable long id, ModelAndView modelAndView) {
        Comment comment = commentService.getComment(id);
        modelAndView.addObject("updateComment", new CommentUpdateDTO());
        modelAndView.addObject("comment", comment);
        modelAndView.setViewName("updatecomment");
        return modelAndView;
    }

    @PostMapping(path = "/updateComment/{id}")
    public ModelAndView postUpdateComment(@ModelAttribute("updateComment") CommentUpdateDTO commentUpdateDTO, @PathVariable long id,
                                          @AuthenticationPrincipal User user, ModelAndView modelAndView) {
        commentService.updateComment(id, commentUpdateDTO);
        Comment comment = commentService.getComment(id);
        Post post  = postService.getPostByComment(comment);
        User person = post.getUser();
        List<User> friends = friendshipService.getAllFriendsPerson(person, user);
        List<Post> posts = person.getPosts();
        if (friendshipService.getAnswerAreUserAndPersonFriends(user, person)) {
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s", person.getId()));
        return modelAndView;
    }
}
