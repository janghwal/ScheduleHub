package com.example.schedulehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor
public class User extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Setter
    @Column(nullable = false)
    private String userName;

    @Setter
    @Column(nullable = false, unique = true)
//    이메일 중복 제한
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}
