package by.realovka.diploma.controller;

import by.realovka.diploma.dto.CommentAddDTO;
import by.realovka.diploma.dto.PostOnPageDTO;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.service.FriendshipService;
import by.realovka.diploma.service.LikeService;
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
@RequestMapping(path = "/addLike")
public class LikeOnPersonPageAddController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private PostService postService;

    @GetMapping(path = "/addLike/{id}")
    public ModelAndView getAddLike(@PathVariable("id") long id, @AuthenticationPrincipal User user, ModelAndView modelAndView){
        Post post =  postService.getPostById(id);
        User person = post.getUser();
        likeService.addLike(post, user);
        List<User> friends = friendshipService.getAllFriendsPerson(person, user);
        List<PostOnPageDTO> posts = postService.getPosts(person);
        modelAndView.addObject("comment", new CommentAddDTO());
        modelAndView.addObject("person", person);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("authUser", user);
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName(String.format("redirect:/person/person/%s", person.getId()));
        return modelAndView;
    }
}
