package com.example.fromis_7_be.listup.entity;

import com.example.fromis_7_be.state.align.entity.Align;
import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.comment.entity.Comment;
import com.example.fromis_7_be.state.like.entity.Like;
import com.example.fromis_7_be.state.unlike.entity.Unlike;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Listup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listId;
    private String name; //meta data로 가져온 컨텐츠 이름
    private String url;
    private String image; //meta 데이터 이미지

    @Lob private String description; //여기 주택 너무 예쁨

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    //likecount
    //commentcount;


    @ManyToOne
    @JsonIgnore // 순환 참조 방지
    @JoinColumn(name = "cate_id")
    private Category category;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "listup", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Like> likes;

    @OneToMany(mappedBy = "listup", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Unlike> unlikes;

    @OneToMany(mappedBy = "listup", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Align> aligns;

    @OneToMany(mappedBy = "listup", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> comments;

    public static Listup from(String name, String url, String image, String description, Category category ) {
        return new Listup(null, name, url, image, description, LocalDateTime.now(), LocalDateTime.now(), category, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
    }

    public void update(String name, String url, String image, String description) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.description = description;
    }
}
