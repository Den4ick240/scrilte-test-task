package ru.zhigalov.scrile_test_task.high_test_task.controller;

import io.swagger.api.UserApi;
import io.swagger.model.ChangeStatusRequest;
import io.swagger.model.ChangeStatusResponse;
import io.swagger.model.Status;
import io.swagger.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.CharacterNCharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zhigalov.scrile_test_task.high_test_task.mapper.UserMappper;
import ru.zhigalov.scrile_test_task.high_test_task.model.UserEntity;
import ru.zhigalov.scrile_test_task.high_test_task.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMappper userMappper;

    @Override
    public ResponseEntity<Long> createUser(User user) {
        var userEntity = userMappper.userToUserEntity(user);
        return ResponseEntity.ok(userRepository.save(userEntity).getId());
    }

    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userRepository
                .findById(userId)
                .map(userMappper::userEntityToUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<ChangeStatusResponse> setStatus(ChangeStatusRequest body) {
        var optionalUserEntity = userRepository.findById(body.getId());
        if (optionalUserEntity.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var userEntity = optionalUserEntity.get();

        var oldStatus = userEntity.getUserStatus();
        var newStatus = body.getNewStatus();

        userEntity.setUserStatus(newStatus);

        var response = new ChangeStatusResponse();
        response.setId(body.getId());
        response.setNewStatus(newStatus);
        response.setOldStatus(oldStatus);
        return ResponseEntity.ok(response);
    }
}
