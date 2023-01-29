package ru.project.carwash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.carwash.entity.User;
import ru.project.carwash.entity.UserDTO;
import ru.project.carwash.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user) {
        UserDTO result = userService.saveUser(user);
        return new ResponseEntity<>(
                result,
                result.getEnabled() ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}
