package ru.zhigalov.scrile_test_task.high_test_task.controller;

import io.swagger.api.UserApi;
import io.swagger.model.ChangeStatusResponse;
import io.swagger.model.User;
import org.springframework.http.ResponseEntity;

public class UserController implements UserApi {
    @Override
    public ResponseEntity<Integer> createUser(User body) {
        return null;
    }

    @Override
    public ResponseEntity<User> getUserById(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<ChangeStatusResponse> setStatus(Long userId, String body) {
        return null;
    }
}
