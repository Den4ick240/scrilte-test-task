package ru.zhigalov.scrile_test_task.high_test_task.model;

import io.swagger.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String avatarUrl;

    @Column
    @Enumerated(EnumType.STRING)
    private Status userStatus;
}
