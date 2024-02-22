package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.University;
import com.ukukhula.bursaryapi.entities.UniversityAllocation;
import com.ukukhula.bursaryapi.repositories.UniversityAllocationRepository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityAllocationService {
    @Autowired
    private final UniversityAllocationRepository universityAllocationRepository;

    public UniversityAllocationService(UniversityAllocationRepository universityAllocationRepository) {
        this.universityAllocationRepository = universityAllocationRepository;
    }

    public UniversityAllocation findUniversityAllocationById(int id) {
        return universityAllocationRepository.findById(id);
    }

    public List<UniversityAllocation> getAllUniversityAllocations() {
        return universityAllocationRepository.getAllUniversityAllocations();
    }

    public Integer allocateFundsToUniversity(int id, BigDecimal amount) {
        return universityAllocationRepository.allocateFundsToUniversity(id, amount);
    }

    public Integer allocateFundsToAllUniversities() {
        return universityAllocationRepository.allocateFundsToAllUniversities();
    }

    public Integer addNewAllocation(int universityId, BigDecimal amount, int bursaryDetails) {
      
        return universityAllocationRepository.addNewAllocation(universityId, amount, bursaryDetails);
    }

    public BigDecimal getTotalSpentInYear(int year){
        return universityAllocationRepository.getTotalSpentInYear(year);
    }
   
}