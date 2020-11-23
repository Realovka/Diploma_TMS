package by.realovka.diploma.service;

import by.realovka.diploma.entity.Like;
import by.realovka.diploma.entity.Post;
import by.realovka.diploma.entity.User;
import by.realovka.diploma.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public void addLike(Post post, User user) {
        List<Like> likes = likeRepository.findByPostId(post.getId());
        if (likes.size() == 0) {
            likeRepository.save(new Like(post, user));
            return;
        }
        for (Like item : likes) {
            if (item.getUser().getId() == user.getId()) {
                return;
            }
        }
        likeRepository.save(new Like(post, user));
    }

}
