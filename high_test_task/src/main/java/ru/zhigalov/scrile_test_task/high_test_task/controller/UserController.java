package ru.zhigalov.scrile_test_task.high_test_task.controller;

import io.swagger.api.UserApi;
import io.swagger.model.ChangeStatusResponse;
import io.swagger.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zhigalov.scrile_test_task.high_test_task.model.ImageEntity;
import ru.zhigalov.scrile_test_task.high_test_task.model.UserEntity;
import ru.zhigalov.scrile_test_task.high_test_task.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Integer> createUser(User body) {
        var entity = new UserEntity(
                null,
                body.getUsername(),
                body.getEmail(),
                body.getAvatarUrl(),
                body.getUserStatus());
        userRepository.save(entity);
        return null;
    }

    @Override
    public ResponseEntity<User> getUserById(@PathVariable  Long userId) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ChangeStatusResponse> setStatus(Long userId, String body) {
        return null;
    }
}
