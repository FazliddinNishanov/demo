package com.example.demo.facade;

import com.example.demo.dto.PostDTO;
import com.example.demo.entitiy.Post;
import org.springframework.stereotype.Component;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 23.06.2022
 */

@Component
public class PostFacade {

    public PostDTO postToPostDto(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setId(post.getId());
        postDTO.setCaption(post.getCaption());
        postDTO.setLikes(post.getLikes());
        postDTO.setUserLiked(post.getLikedUsers());
        postDTO.setLocation(post.getLocation());
        postDTO.setTitle(post.getTitle());

        return postDTO;
    }

}
