package ru.zhigalov.scrile_test_task.high_test_task.mapper;

import io.swagger.model.User;
import org.mapstruct.Mapper;
import ru.zhigalov.scrile_test_task.high_test_task.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMappper {
    UserEntity userToUserEntity(User user);
    User userEntityToUser(UserEntity userEntity);
}
