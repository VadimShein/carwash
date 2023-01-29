package ru.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.carwash.entity.User;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(int id);
    Optional<User> findUserByUsername(String name);
}
