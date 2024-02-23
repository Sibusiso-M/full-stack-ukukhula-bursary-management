package com.ukukhula.bursaryapi.repositories;

import com.ukukhula.bursaryapi.entities.UniversityApplication;

public interface UniversityApplicationRepository {
    UniversityApplication getApplicationByUniversityId(int universityId);
}
