package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.CommentService;
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
@RequestMapping(path = "/deleteCommentOnPersonPage")
public class DeleteCommentOnPersonPage {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/deleteCommentOnPersonPage/{id}")
    public ModelAndView deleteCommentOnPersonPage(@PathVariable long id, @AuthenticationPrincipal User user,
//                                                  @RequestParam("person") User person,
                                                  ModelAndView modelAndView) {
        Comment comment = commentService.getComment(id);
        commentService.deleteCommentOnPersonPage(id);
        User person = userService.getByUserName(comment.getUserWhoWritePostName());
        List<User> friends = userService.getFriends(person);
        if (userService.getAnswerIsPersonUserFriend(user, person)) {
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.setViewName("person");
        return modelAndView;
    }

}
