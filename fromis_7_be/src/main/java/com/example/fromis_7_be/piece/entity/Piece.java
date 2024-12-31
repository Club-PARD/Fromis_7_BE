package com.example.fromis_7_be.piece.entity;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.share.entity.Share;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    //JPA에서 array를 지원하지 않기 때문에 이를 위해 ElementCollection을 사용하여 List<String> member
    @ElementCollection
    @CollectionTable(name = "piece_member_names", joinColumns = @JoinColumn(name = "piece_id"))
    @Column(name = "member_name")
    private List<String> memberNames;
    private String color;
    private Integer startYear;
    private Integer startMonth;
    private Integer startDay;
    private Integer endYear;
    private Integer endMonth;
    private Integer endDay;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPiece> userPieces = new ArrayList<>();

    @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "share_id") // 외래 키로 사용할 컬럼 지정
    private Share share;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }


    public static Piece from(String title, List<String> memberNames, String color,
                             Integer startYear, Integer startMonth, Integer startDay,
                             Integer endYear, Integer endMonth, Integer endDay) {
        return Piece.builder()
                .title(title)
                .memberNames(memberNames)
                .color(color)
                .startYear(startYear)
                .startMonth(startMonth)
                .startDay(startDay)
                .endYear(endYear)
                .endMonth(endMonth)
                .endDay(endDay)
                .build();
    }
    // 업데이트 메서드 구현
    public void update(String title, List<String> memberNames, String color,
                       Integer startYear, Integer startMonth, Integer startDay,
                       Integer endYear, Integer endMonth, Integer endDay) {
        this.title = title;
        this.memberNames = memberNames;
        this.color = color;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.modifiedAt = LocalDateTime.now(); // 수정 시간 갱신
    }

}
