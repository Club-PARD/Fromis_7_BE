package com.example.fromis_7_be.userpiece.entity;

import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "piece_id", nullable = false)
    private Piece piece;

    public void setUser(User user){
        this.user = user;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }



}
