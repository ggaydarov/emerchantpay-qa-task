package com.server.employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void save(EmployeeEntity employeeEntity) {
        employeeRepo.save(employeeEntity);
    }

    public void delete(EmployeeEntity employeeEntity) {
        employeeRepo.delete(employeeEntity);
    }

    public void edit(EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity> entity = employeeRepo.findById(employeeEntity.getId());
    }

    public List<EmployeeEntity> getAll() {
        return employeeRepo.findAll();
    }
}
