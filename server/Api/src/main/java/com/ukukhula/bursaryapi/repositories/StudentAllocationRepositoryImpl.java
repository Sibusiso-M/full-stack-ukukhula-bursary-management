package com.ukukhula.bursaryapi.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.ukukhula.bursaryapi.entities.StudentAllocation;
import com.ukukhula.bursaryapi.exceptions.StudentAllocationNotFoundException;

@Service
public class StudentAllocationRepositoryImpl implements StudentAllocationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentAllocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<StudentAllocation> studentAllocationRowMapper = ((resultSet, rowNumber) -> {
        return new StudentAllocation(
                resultSet.getInt("id"),
                resultSet.getInt("studentId"),
                resultSet.getBigDecimal("amount"),
                resultSet.getInt("year"));
    });

    @Override
    public List<StudentAllocation> getAllStudentAllocations() {
        String SELECT_ALL_STUDENT_ALLOCATION = "SELECT * FROM [dbo].[StudentAllocation]";

        List<StudentAllocation> students = jdbcTemplate.query(SELECT_ALL_STUDENT_ALLOCATION,
                studentAllocationRowMapper);
        return students;
    }

    @Override
    public StudentAllocation getStudentAllocationById(int id) {
        String SELECT_STUDENT_ALLOCATION_BY_ID = "SELECT * FROM StudentAllocation WHERE [id] = ?";

        try {
            return jdbcTemplate.queryForObject(SELECT_STUDENT_ALLOCATION_BY_ID, studentAllocationRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new StudentAllocationNotFoundException("StudentAllocation not found for ID: " + id);
        }
    }

    @Override
    public StudentAllocation createStudentAllocation(StudentAllocation studentAllocation) {
        String CREATE_STUDENT_ALLOCATION = "INSERT INTO [dbo].[StudentAllocation] ([StudentID], [Amount], [Year]) VALUES(?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(CREATE_STUDENT_ALLOCATION,
                studentAllocation.getStudentId(),
                studentAllocation.getAmount(),
                studentAllocation.getYear());

        if (rowsAffected > 0) {
            return studentAllocation;
        } else {
            throw new RuntimeException("Error creating StudentAllocation. No rows affected.");// customize
        }
    }

    @Override
    public StudentAllocation updateStudentAllocation(int id, StudentAllocation updatedAllocation) {
        updatedAllocation.setId(id);

        String UPDATE_STUDENT_ALLOCATION = "UPDATE [dbo].[StudentAllocation] SET [StudentID] = ?, [Amount] = ?, [Year] = ? WHERE [id] = ?";

        int rowsAffected = jdbcTemplate.update(UPDATE_STUDENT_ALLOCATION,
                updatedAllocation.getStudentId(),
                updatedAllocation.getAmount(),
                updatedAllocation.getYear(),
                id);

        if (rowsAffected > 0) {
            return updatedAllocation;
        } else {
            throw new RuntimeException("Error updating StudentAllocation. No rows were updated.");// customize
        }
    }

    @Override
    public StudentAllocation deleteStudentAllocation(int id) {
        String SELECT_DELETED_ALLOCATION = "SELECT * FROM StudentAllocation WHERE ID = ?";
        StudentAllocation deletedAllocation = jdbcTemplate.queryForObject(SELECT_DELETED_ALLOCATION,
                studentAllocationRowMapper, id);

        String DELETE_STUDENT_ALLOCATION = "DELETE FROM StudentAllocation WHERE ID = ?";

        int rowsAffected = jdbcTemplate.update(DELETE_STUDENT_ALLOCATION, id);

        if (rowsAffected == 0) {
            throw new DataRetrievalFailureException("No student allocation found with ID: " + id);
        } else {
            System.out.println("Deleted StudentAllocation with ID: " + id);
        }
        return deletedAllocation;

    }

    @Override
    public BigDecimal getStudentAllocationsTotalSpent(int year, int universityId) {
        String CALL_UDFGETTOTALSPENTBYUNIVERSITYBYEAR = "SELECT dbo.udfGetTotalSpentByUniversityByYear(?, ?)";

        try {

            BigDecimal universityTotalYearSpend = jdbcTemplate.queryForObject(
                    CALL_UDFGETTOTALSPENTBYUNIVERSITYBYEAR,
                    BigDecimal.class,
                    year, universityId);

            if (universityTotalYearSpend == null) {

                return BigDecimal.ZERO;
            } else {
                return universityTotalYearSpend;
            }
        } catch (EmptyResultDataAccessException e) {

            return BigDecimal.ZERO;
        }
    }
}
