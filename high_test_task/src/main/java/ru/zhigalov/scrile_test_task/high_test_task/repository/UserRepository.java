package ru.zhigalov.scrile_test_task.high_test_task.repository;

import io.swagger.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zhigalov.scrile_test_task.high_test_task.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query("update Users u set u.userStatus = :status where u.id = :id")
    void updateStatusById(@Param(value="id") long id, @Param(value = "status") Status status);
}
