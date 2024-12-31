package com.example.fromis_7_be.state.unlike.entity;

import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "list_unlike")
public class Unlike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private Listup listup;


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public static Unlike form(User user, Listup listup){
        return new Unlike(null, null, user, listup);
    }

}
