package com.ukukhula.bursaryapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ukukhula.bursaryapi.entities.StudentAllocation;
import com.ukukhula.bursaryapi.repositories.StudentAllocationRepository;
import java.math.BigDecimal;


@Service
public class StudentAllocationService {

    private final StudentAllocationRepository studentAllocationRepository;

    public StudentAllocationService(StudentAllocationRepository studentAllocationRepository) {
        this.studentAllocationRepository = studentAllocationRepository;
    }

    public List<StudentAllocation> getAllStudentAllocations() {
        return studentAllocationRepository.getAllStudentAllocations();
    }

    public StudentAllocation getStudentAllocationById(int id) {
        return studentAllocationRepository.getStudentAllocationById(id);
    }

    public StudentAllocation createStudentAllocation(StudentAllocation studentAllocation) {
        return studentAllocationRepository.createStudentAllocation(studentAllocation);
    }

    public StudentAllocation updateStudentAllocation(int id, StudentAllocation updatedAllocation) {
        return studentAllocationRepository.updateStudentAllocation(id, updatedAllocation);
    }

    public void deleteStudentAllocation(int id) {
        studentAllocationRepository.deleteStudentAllocation(id);
    }

    public BigDecimal getStudentAllocationsTotalSpent(int year, int universityId) {
        return studentAllocationRepository.getStudentAllocationsTotalSpent(year, universityId);
    }
}
