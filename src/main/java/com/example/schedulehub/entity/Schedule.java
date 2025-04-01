package com.example.schedulehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Schedule")
@Getter
@NoArgsConstructor
public class Schedule extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String title;

    private String contents;
}
