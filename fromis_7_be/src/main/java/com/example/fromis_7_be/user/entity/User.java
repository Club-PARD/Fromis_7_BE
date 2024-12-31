package com.example.fromis_7_be.user.entity;

import com.example.fromis_7_be.comment.entity.Comment;
import com.example.fromis_7_be.share.entity.Share;
import com.example.fromis_7_be.state.align.entity.Align;
import com.example.fromis_7_be.state.like.entity.Like;
import com.example.fromis_7_be.state.unlike.entity.Unlike;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String profileImg;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPiece> userPieces;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Unlike> unlikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Align> aligns;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

    public void update(String name){
        LocalDateTime now = LocalDateTime.now();
        this.name = name;
        this.modifiedAt = now;
    }

    public void setImage(String imageUrl) {
        this.profileImg = imageUrl;
    }
}
