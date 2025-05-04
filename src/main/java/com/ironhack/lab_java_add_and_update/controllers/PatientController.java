package com.ironhack.lab_java_add_and_update.controllers;

import com.ironhack.lab_java_add_and_update.models.Patient;
import com.ironhack.lab_java_add_and_update.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @GetMapping(params = {"minBirthYear", "maxBirthYear"})
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByBirthYearRange(@RequestParam Date minBirthYear, @RequestParam Date maxBirthYear) {
        return patientRepository.findByDateOfBirthBetween(minBirthYear, maxBirthYear);
    }

    @GetMapping(params = "department")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDoctorDeparment(@RequestParam String department) {
        return patientRepository.findByDoctorDepartment(department);
    }

    @GetMapping("/offstatus")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDoctorStatus() {
        return patientRepository.findByOffDoctorStatus();
    }

}
