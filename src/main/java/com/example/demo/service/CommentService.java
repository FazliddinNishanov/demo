package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Post;
import com.example.demo.entitiy.User;
import com.example.demo.exseption.PostNotFoundException;
import com.example.demo.repository.CommentRepository;
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
public class CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment saveComment(Long postId, CommentDTO commentDTO, Principal principal){
        User user = getUserByPrincipal(principal);
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException("Post is not foud"));

        Comment comment= new Comment();
        comment.setPost(post);
        comment.setUserId(user.getId());
        comment.setUsername(user.getUsername());
        comment.setMessage(commentDTO.getMessage());

        LOG.info("Saving Comment is successfully: {}" ,post.getId() );
        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsForPost(Long postId){
        Post post = postRepository.findById(postId)
                        .orElseThrow(()->new PostNotFoundException("Post is not foud"));
        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments;

    }

    public void deleteComment(Long commentId){
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        optionalComment.ifPresent(commentRepository::delete);
    }


    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found: " + username));
    }
}
