package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.CommentUpdateDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.entity.Comment;
import by.realovka.diploma.entity.Post;
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
    public ModelAndView getPersonPage(@PathVariable("id") long personId,  @AuthenticationPrincipal User user, ModelAndView modelAndView){
        User person = userService.getUserById(personId);
        List<User> friends = friendshipService.getAllFriendsPerson(personId, user.getId());
        List<PostOnPageDTO> posts = postService.getPosts(personId);
        if(friendshipService.getAnswerAreUserAndPersonFriends(user.getId(),personId)){
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
                                       @PathVariable ("id") long personId, @RequestParam("postId") long postId,
                                       ModelAndView modelAndView){
        commentService.addComment(postId,  user, commentAddDTO);
        User person = userService.getUserById(personId);
        List<User> friends = friendshipService.getAllFriendsPerson(personId, user.getId());
        List<PostOnPageDTO> posts = postService.getPosts(personId);
        if(friendshipService.getAnswerAreUserAndPersonFriends(user.getId(),personId)){
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s", personId));
        return modelAndView;

    }

    @GetMapping(path = "/deleteCommentOnPersonPage/commentId/{commentId}/personId/{personId}")
    public ModelAndView deleteCommentOnPersonPage(@PathVariable long commentId, @PathVariable long personId,  @AuthenticationPrincipal User user,
                                                  ModelAndView modelAndView) {
        commentService.deleteCommentOnPersonPage(commentId);
        User person = userService.getUserById(personId);
        List<User> friends = friendshipService.getAllFriendsPerson(person.getId(), user.getId());
        List<PostOnPageDTO> posts = postService.getPosts(personId);
        if (friendshipService.getAnswerAreUserAndPersonFriends(person.getId(), user.getId())) {
            modelAndView.addObject("messageAboutFriend", "It's your friend");
        }
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s",person.getId()));
        return modelAndView;
    }

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
        List<User> friends = friendshipService.getAllFriendsPerson(person.getId(), user.getId());
        List<Post> posts = person.getPosts();
        if (friendshipService.getAnswerAreUserAndPersonFriends(user.getId(), person.getId())) {
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

    @GetMapping(path = "/addLike/{id}")
    public ModelAndView getAddLike(@PathVariable("id") long id, @AuthenticationPrincipal User user, ModelAndView modelAndView){
        Post post =  postService.getPostById(id);
        User person = post.getUser();
        likeService.addLike(post, user);
        List<User> friends = friendshipService.getAllFriendsPerson(person.getId(), user.getId());
        List<PostOnPageDTO> posts = postService.getPosts(person.getId());
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s", person.getId()));
        return modelAndView;
    }
}
