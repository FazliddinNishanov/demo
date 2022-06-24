package com.example.demo.repository;

import com.example.demo.entitiy.Comment;
import com.example.demo.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 21.06.2022
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    Comment findByIdAndUserId(Long commentId, Long userId);


}
