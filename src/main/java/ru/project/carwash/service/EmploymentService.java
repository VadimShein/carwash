package ru.project.carwash.service;

import ru.project.carwash.entity.Employment;

import java.util.List;

public interface EmploymentService {
    Employment addEmployment(Employment employment);
    List<Employment> getAllEmployments();
    Employment getEmploymentById(int id);
//    Optional<Employment> getEmploymentById(int id);
    void deleteEmployment(int id);
}
