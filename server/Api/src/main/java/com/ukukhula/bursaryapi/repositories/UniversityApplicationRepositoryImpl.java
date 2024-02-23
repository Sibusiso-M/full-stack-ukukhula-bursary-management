package com.ukukhula.bursaryapi.repositories;

import com.ukukhula.bursaryapi.entities.UniversityApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityApplicationRepositoryImpl implements UniversityApplicationRepository {

    final
    JdbcTemplate jdbcTemplate;

    public UniversityApplicationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UniversityApplication getApplicationByUniversityId(int universityId) {
        String GET_APPLICATION_BY_UNIVERSITY_ID = "SELECT * FROM " +
                "UniversityApplication WHERE UniversityID = ?";
        return jdbcTemplate.queryForObject(GET_APPLICATION_BY_UNIVERSITY_ID,
                universityApplicationRowMapper, universityId);
    }

    private final RowMapper<UniversityApplication> universityApplicationRowMapper = ((resultSet, rowNumber) -> {
        return new UniversityApplication(resultSet.getInt("ID"),
                resultSet.getInt("UniversityID"), resultSet.getString(
                        "Motivation"), resultSet.getString("Status"),
                resultSet.getString("RejectionReason"));
    });
}
