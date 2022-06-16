package ru.zhigalov.scrile_test_task.high_test_task.service;

import io.swagger.model.ChangeStatusRequest;
import io.swagger.model.ChangeStatusResponse;
import io.swagger.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long userId);
    ChangeStatusResponse changeStatus(ChangeStatusRequest request);
    User createUser(User user);
}
