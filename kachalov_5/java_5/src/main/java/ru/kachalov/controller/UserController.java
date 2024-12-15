package ru.kachalov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kachalov.entity.UserEntity;
import ru.kachalov.entity.UserRequest;
import ru.kachalov.repository.UserRepository;
import ru.kachalov.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserEntity> getUserById(
            @RequestParam(required = false) Integer optionalId,
            @RequestBody(required = false) UserRequest requestBody) {

        boolean hasId = optionalId != null;
        boolean hasRequestBody = requestBody != null;

        if (!hasId && !hasRequestBody) {
            return ResponseEntity.badRequest().body(null);
        }

        int finalUserId = hasId ? optionalId : requestBody.getId();
        UserEntity userEntity = userService.getUser(finalUserId);

        return ResponseEntity.ok(userEntity);
    }

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserRequest userRequest) {
        UserEntity user = userService.addUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflict(IllegalArgumentException error) {
        return error.getMessage();
    }
}
