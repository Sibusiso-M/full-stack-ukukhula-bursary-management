package com.ukukhula.bursaryapi.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ukukhula.bursaryapi.assemblers.StudentApplicationAssembler;
import com.ukukhula.bursaryapi.entities.StudentApplication;
import com.ukukhula.bursaryapi.exceptions.StudentApplicationException;
import com.ukukhula.bursaryapi.exceptions.ApplicationInvalidStatusException;
import com.ukukhula.bursaryapi.services.StudentApplicationService;

@RestController
@RestControllerAdvice

public class StudentApplicationController {

    private final StudentApplicationService studentApplicationService;
    private final StudentApplicationAssembler assembler;

    public StudentApplicationController(StudentApplicationService studentApplicationService,
            StudentApplicationAssembler assembler) {
        this.studentApplicationService = studentApplicationService;
        this.assembler = assembler;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentApplications(@PathVariable int studentId) {

        if (studentId <= 0) {
            return ResponseEntity.badRequest().body("Student ID is not provided");
        }
        StudentApplication application = studentApplicationService.findByStudentID(studentId);

        if (application == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<StudentApplication> entityModel = assembler.toModel(application);

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/students")
    public CollectionModel<EntityModel<StudentApplication>> getAllStudentApplications() {
        List<EntityModel<StudentApplication>> applications = studentApplicationService.getAllStudentsApplications()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(applications);
    }

    @ExceptionHandler({ StudentApplicationException.class,
            ApplicationInvalidStatusException.class })
            
    @PutMapping("/status/{studentID}")
    public ResponseEntity<?> updateStudentsApplicationStatus(@PathVariable int studentID,
            @RequestBody Map<String, String> requestBody) {

        if (studentID <= 0) {
            return ResponseEntity.badRequest().body("Student ID is not provided");
        }

        if (requestBody.isEmpty()) {
            return ResponseEntity.badRequest().body("Request body is empty");
        }

        String statusString = requestBody.get("status");

        if (statusString == null || statusString.isEmpty()) {
            return ResponseEntity.badRequest().body("Status value is missing in the request body");
        }

        try {
            Integer rowsAffected = studentApplicationService.updateStudentsApplicationStatus(studentID, statusString);

            if (rowsAffected >= 1) {
                return ResponseEntity.ok("Student status successful");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception error) {
            throw new Error(error.getMessage());
        }
    }

    @PutMapping("/student/updateColumn/{studentID}")
    public ResponseEntity<?> updateStudentsApplicationColumnValue(@PathVariable int studentID,
            @RequestBody Map<String, String> requestBody) {

        if (requestBody.isEmpty()) {
            return ResponseEntity.badRequest().body("Request body is empty");
        }

        String columNameString = new String();

        for (String columName : requestBody.keySet()) {
            columNameString = columName;
        }

        String valueString = requestBody.get(columNameString);

        try {

            if (columNameString == "Status") {
                throw new Error("You have no authority to update Status's");
            }

            Integer rowsAffected = studentApplicationService.updateStudentsApplicationColumnValue(studentID,
                    columNameString, valueString);

            if (rowsAffected >= 1) {
                return ResponseEntity.ok("Column update successful");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unsuccessful update");
        }

    }
}