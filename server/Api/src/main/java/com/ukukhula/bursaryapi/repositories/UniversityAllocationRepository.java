package com.ukukhula.bursaryapi.repositories;

import java.math.BigDecimal;
import java.util.List;

import com.ukukhula.bursaryapi.entities.UniversityAllocation;

public interface UniversityAllocationRepository {
    UniversityAllocation findById(int id);

    List<UniversityAllocation> getAllUniversityAllocations();

    Integer allocateFundsToUniversity(int id, BigDecimal amount);

    Integer allocateFundsToAllUniversities();

    Integer addNewAllocation(int universityId, BigDecimal amount, int bursaryDetailsID);

    BigDecimal getTotalSpentInYear(int year);

}