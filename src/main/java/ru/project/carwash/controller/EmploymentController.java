package ru.project.carwash.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.project.carwash.entity.Employment;
import ru.project.carwash.service.EmploymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmploymentController {
    EmploymentService employmentService;

    public EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @GetMapping("/employment")
    public List<Employment> getAllEmployments() {
        return employmentService.getAllEmployments();
    }

    @PostMapping("/employment")
    public ResponseEntity<Employment> addEmployment(@Valid @RequestBody Employment employment) {
        return new ResponseEntity<>(employmentService.addEmployment(employment), HttpStatus.CREATED);
    }

    @DeleteMapping("/employment/{id}")
    public ResponseEntity<Void> deleteEmployment(@PathVariable int id) {
        try {
            employmentService.deleteEmployment(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Employment with Id: " + id + " not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
