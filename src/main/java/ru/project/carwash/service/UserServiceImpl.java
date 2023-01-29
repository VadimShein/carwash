package ru.project.carwash.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.carwash.entity.Role;
import ru.project.carwash.entity.User;
import ru.project.carwash.entity.UserDTO;
import ru.project.carwash.repository.RoleRepository;
import ru.project.carwash.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public User findUserById(int id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Override
    public User findUserByName(String username) {
        Optional<User> userFromDb = userRepository.findUserByUsername(username);
        return userFromDb.orElse(new User());
    }

    @Override
    public UserDTO saveUser(User user) {
        Optional<User> findUser = userRepository.findUserByUsername(user.getUsername());
        if (findUser.isPresent()) {
            return new UserDTO(user.getUsername(), false);
        }
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return new UserDTO(user.getUsername(), user.getEnabled());
    }

    //admin only
    @Override
    public UserDTO saveAdmin(User user) {
        Optional<User> findUser = userRepository.findUserByUsername(user.getUsername());
        if (findUser.isPresent()) {
            return new UserDTO(user.getUsername(), false);
        }
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(role));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return new UserDTO(user.getUsername(), user.getEnabled());
    }

    // admin only
    @Override
    public void deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
