package com.example.fromis_7_be.category.entity;

import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.piece.entity.Piece;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private Boolean isHighlighted;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Listup> lists = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "piece_id", nullable = false)
    private Piece piece;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
    public void setIsHighlighted(Boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
        this.modifiedAt = LocalDateTime.now();
    }

    public static Category from(String name, String color, Boolean isHighlighted, Piece piece){
        return new Category(null, name, color, isHighlighted, new ArrayList<>(), piece, LocalDateTime.now(),  LocalDateTime.now());
    }

    public void update(String name, String color, Boolean isHighlighted) {
        this.name = name; // 이름 수정
        this.color = color; // 색상 수정
        this.isHighlighted = isHighlighted; // 하이라이트 상태 수정
        this.modifiedAt = LocalDateTime.now(); // 수정 시간 갱신
    }

}
