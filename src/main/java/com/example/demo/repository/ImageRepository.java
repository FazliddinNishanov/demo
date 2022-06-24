package com.example.demo.repository;

import com.example.demo.entitiy.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 21.06.2022
 */

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByUserId(Long userId);

    Optional<ImageModel> findByPostId(Long postId);
}
