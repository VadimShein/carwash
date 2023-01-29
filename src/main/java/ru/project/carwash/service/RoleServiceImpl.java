package ru.project.carwash.service;

import org.springframework.stereotype.Service;
import ru.project.carwash.entity.Role;
import ru.project.carwash.repository.RoleRepository;

@Service("RoleServiceImpl")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
