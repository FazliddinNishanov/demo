package com.example.demo.facade;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entitiy.Comment;
import org.springframework.stereotype.Component;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 23.06.2022
 */

@Component
public class CommentFacade {

    public CommentDTO commentToCommentDto(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setMessage(comment.getMessage());

        return commentDTO;
    }
}
