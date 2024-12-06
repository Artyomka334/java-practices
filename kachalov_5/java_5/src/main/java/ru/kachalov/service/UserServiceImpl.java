package ru.kachalov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kachalov.entity.UserEntity;
import ru.kachalov.entity.UserRequest;
import ru.kachalov.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserEntity getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity addUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail()) == false) {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(userRequest.getFirstName());
            userEntity.setLastName(userRequest.getLastName());
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setJob(userRequest.getJob());

            return userRepository.save(userEntity);
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }
}
