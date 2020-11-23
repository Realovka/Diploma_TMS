package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.CommentServiceImpl;
import by.realovka.diploma.service.FriendshipService;
import by.realovka.diploma.service.PostServiceImpl;
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
@RequestMapping(path = "/deleteCommentOnPersonPage")
public class CommentOnPersonPageDeleteController {
    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserService userService;
    @Autowired
    private PostServiceImpl postServiceImpl;
    @Autowired
    private FriendshipService friendshipService;

    @GetMapping(path = "/deleteCommentOnPersonPage/{id}")
    public ModelAndView deleteCommentOnPersonPage(@PathVariable long id, @AuthenticationPrincipal User user,
                                                  ModelAndView modelAndView) {
        Comment comment = commentService.getComment(id);
        commentService.deleteCommentOnPersonPage(id);
        Post post = postServiceImpl.getPostByComment(comment);
        User person = post.getUser();
//        List<User> friends = userService.getFriends(person);
        List<PostOnPageDTO> posts = postServiceImpl.getPosts(person);
        if (friendshipService.getAnswerAreUserAndPersonFriends(user, person)) {
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
//        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s",person.getId()));
        return modelAndView;
    }

}
