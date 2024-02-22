package com.ukukhula.bursaryapi.repositories;

import java.math.BigDecimal;
import java.util.List;

import com.ukukhula.bursaryapi.entities.StudentAllocation;

public interface StudentAllocationRepository {

    List<StudentAllocation> getAllStudentAllocations();

    StudentAllocation getStudentAllocationById(int id);

    StudentAllocation createStudentAllocation(StudentAllocation studentAllocation);

    StudentAllocation updateStudentAllocation(int id, StudentAllocation updatedAllocation);

    StudentAllocation deleteStudentAllocation(int id);

    BigDecimal getStudentAllocationsTotalSpent(int year, int universityId);

}
