package com.example.demo.entitiy;

import javafx.geometry.Pos;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Developed by: Fazliddin Nishanov
 * Date: 20.06.2022
 */

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Long userId;
    @Column(columnDefinition = "text", nullable = false)
    private String message;
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    private void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
