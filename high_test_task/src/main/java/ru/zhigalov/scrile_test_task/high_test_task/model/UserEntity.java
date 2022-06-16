package ru.zhigalov.scrile_test_task.high_test_task.model;

import io.swagger.model.Status;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    @Column
    @Enumerated(EnumType.STRING)
    private Status userStatus;
}
