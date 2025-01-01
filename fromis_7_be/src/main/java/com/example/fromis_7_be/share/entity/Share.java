package com.example.fromis_7_be.share.entity;

import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String shareURL;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "share", cascade = CascadeType.ALL)
    private Piece piece; // 관계의 반대쪽

}
