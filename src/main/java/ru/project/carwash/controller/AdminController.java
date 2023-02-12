package ru.project.carwash.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.project.carwash.entity.Role;
import ru.project.carwash.entity.User;
import ru.project.carwash.entity.UserDTO;
import ru.project.carwash.service.RoleService;
import ru.project.carwash.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @MutationMapping
    @PostMapping("/admin/registration")
    public ResponseEntity<UserDTO> registerAdmin(@Argument @Valid @RequestBody User user) {
        UserDTO result = userService.saveAdmin(user);
        return new ResponseEntity<>(
                result,
                result.getEnabled() ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @MutationMapping
    @PostMapping("/admin/role")
    public ResponseEntity<Role> addRole(@Argument @Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    @MutationMapping
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUser(@Argument @PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
