package ru.kachalov.service;

import ru.kachalov.entity.UserEntity;
import ru.kachalov.entity.UserRequest;

public interface UserService {

    UserEntity getUser(int id);
    UserEntity addUser(UserRequest userRequest);
}
