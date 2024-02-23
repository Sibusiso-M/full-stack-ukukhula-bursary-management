package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.UniversityApplication;

public interface UniversityApplicationService {
    UniversityApplication getApplicationByUniversityId(int universityId);
}
