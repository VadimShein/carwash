package ru.project.carwash.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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


    @QueryMapping
    @GetMapping("user/{username}")
    public User findUserByName(@Argument @PathVariable String username) {
        return userService.findUserByName(username);
    }

    @MutationMapping
    @PostMapping("/user/registration")
    public ResponseEntity<UserDTO> registerUser(@Argument @Valid @RequestBody User user) {
        UserDTO result = userService.saveUser(user);
        return new ResponseEntity<>(
                result,
                result.getEnabled() ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}
