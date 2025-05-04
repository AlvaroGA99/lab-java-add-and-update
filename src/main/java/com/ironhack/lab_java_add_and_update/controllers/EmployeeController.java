package com.ironhack.lab_java_add_and_update.controllers;

import com.ironhack.lab_java_add_and_update.EmployeeStatus;
import com.ironhack.lab_java_add_and_update.models.Employee;
import com.ironhack.lab_java_add_and_update.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getPatientById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @GetMapping(params = "status")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getPatientsByStatus(@RequestParam EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    @GetMapping(params = "department")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getPatientsByDepartment(@RequestParam String department) {
        return employeeRepository.findByDepartment(department);
    }


}
