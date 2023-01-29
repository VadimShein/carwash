package ru.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.carwash.entity.Employment;

import java.util.Optional;

public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
//    List<Employment> findAll();
    Optional<Employment> findById(int id);

}
