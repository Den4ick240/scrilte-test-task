package ru.zhigalov.scrile_test_task.high_test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhigalov.scrile_test_task.high_test_task.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
