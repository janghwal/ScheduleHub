package com.example.schedulehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Schedule")
@Getter
@NoArgsConstructor
public class Schedule extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false)
    private String userName;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    private String contents;

    //유저 테이블과 연결
    @Setter
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public Schedule(String userName, String title, String contents) {
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
}
