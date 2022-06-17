package ru.zhigalov.scrile_test_task.high_test_task.controller;

import io.swagger.api.UserApi;
import io.swagger.model.ChangeStatusRequest;
import io.swagger.model.ChangeStatusResponse;
import io.swagger.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zhigalov.scrile_test_task.high_test_task.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<Long> createUser(User user) {
        return ResponseEntity.ok(
          userService.createUser(user).getId()
        );
    }

    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.findUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<ChangeStatusResponse> setStatus(ChangeStatusRequest body) {
        return Optional
                .of(userService.changeStatus(body))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
