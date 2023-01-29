package ru.project.carwash.controller;

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

    @PostMapping("/admin/registration")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user) {
        UserDTO result = userService.saveAdmin(user);
        return new ResponseEntity<>(
                result,
                result.getEnabled() ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/admin/role")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteUser(Integer.parseInt(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
