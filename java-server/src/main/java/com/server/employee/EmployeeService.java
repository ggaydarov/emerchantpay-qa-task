package com.server.employee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void save(EmployeeEntity employeeEntity) {
        employeeRepo.save(employeeEntity);
    }

    public void delete(Long id) {
        Optional<EmployeeEntity> opEntity = employeeRepo.findById(id);
        if(opEntity.isPresent()) {
            EmployeeEntity entity = opEntity.get();
            employeeRepo.delete(entity);
        }
    }

    public void edit(Long id, EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity> opEntity = employeeRepo.findById(id);

        if(opEntity.isPresent()) {
            EmployeeEntity entity = opEntity.get();
            entity.setFirstName(employeeEntity.getFirstName());
            entity.setLastName(employeeEntity.getLastName());
            entity.setAddress(employeeEntity.getAddress());
            entity.setCountry(employeeEntity.getCountry());
            entity.setCity(employeeEntity.getCity());
            entity.setZip(employeeEntity.getZip());
            entity.setPhone(employeeEntity.getPhone());
            entity.setSalary(employeeEntity.getSalary());
            employeeRepo.save(entity);
        }
    }

    public List<EmployeeEntity> getAll() {
        return employeeRepo.findAll();
    }

    public EmployeeEntity getById(Long id) {
        return employeeRepo.findById(id).get();
    }
}
