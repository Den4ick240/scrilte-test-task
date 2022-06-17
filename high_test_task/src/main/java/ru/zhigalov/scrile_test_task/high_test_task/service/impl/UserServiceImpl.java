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
        var optionalUserEntity =
                repository.findById(request.getId());
        if (optionalUserEntity.isEmpty())
            return null;

        var userEntity = optionalUserEntity.get();

        var oldStatus = userEntity.getUserStatus();

        userEntity.setUserStatus(request.getNewStatus());
        userEntity = repository.save(userEntity);

        return new ChangeStatusResponse()
                .id(userEntity.getId())
                .newStatus(userEntity.getUserStatus())
                .oldStatus(oldStatus);
    }

    @Override
    public User createUser(User user) {
        return mapper.userEntityToUser(
                repository.save(
                        mapper.userToUserEntity(user)
                )
        );
    }
}
