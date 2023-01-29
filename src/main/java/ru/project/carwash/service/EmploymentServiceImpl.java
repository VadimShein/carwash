package ru.project.carwash.service;


import org.springframework.stereotype.Service;
import ru.project.carwash.entity.Employment;
import ru.project.carwash.repository.EmploymentRepository;

import java.util.List;
import java.util.Optional;

@Service("EmploymentServiceImpl")
public class EmploymentServiceImpl implements EmploymentService {
    private final EmploymentRepository employmentRepository;

    public EmploymentServiceImpl(EmploymentRepository employmentRepository) {
        this.employmentRepository = employmentRepository;
    }

    @Override
    public List<Employment> getAllEmployments() {
        return employmentRepository.findAll();
    }

    @Override
    public Employment getEmploymentById(int id) {
        Optional<Employment> employmentFromDb = employmentRepository.findById(id);
        return employmentFromDb.orElse(new Employment());
    }

    // only admin
    @Override
    public Employment addEmployment(Employment employment) {
        return employmentRepository.save(employment);
    }

    //only admin
    @Override
    public void deleteEmployment(int id) {
        if (employmentRepository.findById(id).isPresent()) {
            Employment employment = new Employment();
            employment.setId(id);
            employmentRepository.delete(employment);
        }
    }
}
