package ru.zhigalov.scrile_test_task.high_test_task.service.impl;

import io.swagger.model.ChangeStatusRequest;
import io.swagger.model.ChangeStatusResponse;
import io.swagger.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zhigalov.scrile_test_task.high_test_task.mapper.UserMappper;
import ru.zhigalov.scrile_test_task.high_test_task.repository.UserRepository;
import ru.zhigalov.scrile_test_task.high_test_task.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMappper mapper;

    @Override
    public Optional<User> findUserById(Long userId) {
        return repository
                .findById(userId)
                .map(mapper::userEntityToUser);
    }

    @Override
    public ChangeStatusResponse changeStatus(ChangeStatusRequest request) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
