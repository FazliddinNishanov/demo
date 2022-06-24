package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entitiy.ImageModel;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;
import com.example.demo.exseption.PostNotFoundException;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 22.06.2022
 */

@Service
public class PostService {

    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    public Post createrPost(PostDTO postDTO, Principal principal){

        User user = getUserByPrincipal(principal);
        Post post = new Post();
        post.setUser(user);
        post.setCaption(postDTO.getCaption());
        post.setLocation(postDTO.getLocation());
        post.setTitle(postDTO.getTitle());
        post.setLikes(0);

        LOG.info("saving Post into the Database for User: {}", user.getEmail());
        return postRepository.save(post);
    }

    public List<Post> getAllPost(){
        return postRepository.findAllByOrderByCreatedDateDesc();
    }
    public Post getPostById(Long postId, Principal principal){
        User user = getUserByPrincipal(principal);
        return postRepository.findPostByIdAndUser(postId, user)
                .orElseThrow(() -> new PostNotFoundException("Post is not found:"+ user.getEmail()));
    }

    public List<Post> getAllForUser(Principal principal){
        User user = getUserByPrincipal(principal);
        return postRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public Post likedPost(Long postId, String username){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException("Post is not foud"));

        Optional<String> userLiked = post.getLikedUsers()
                .stream()
                .filter(u->u.equals(username)).findAny();

        if (userLiked.isPresent()){
            post.setLikes(post.getLikes() - 1);
            post.getLikedUsers().remove(username);
        }else {
            post.setLikes(post.getLikes() + 1);
            post.getLikedUsers().add(username);
        }
        return postRepository.save(post);
    }

    public void deletePost(Long postId, Principal principal){
        Post post = getPostById(postId, principal);
        Optional<ImageModel> imageModel = imageRepository.findByPostId(post.getId());
        postRepository.delete(post);
        imageModel.ifPresent(imageRepository::delete);
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found: " + username));
    }

}
