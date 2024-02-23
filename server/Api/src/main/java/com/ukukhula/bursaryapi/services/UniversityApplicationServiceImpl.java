package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.UniversityApplication;
import com.ukukhula.bursaryapi.repositories.UniversityApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class UniversityApplicationServiceImpl implements UniversityApplicationService {
    final
    UniversityApplicationRepository universityApplicationRepository;

    public UniversityApplicationServiceImpl(UniversityApplicationRepository universityApplicationRepository) {
        this.universityApplicationRepository = universityApplicationRepository;
    }


    @Override
    public UniversityApplication getApplicationByUniversityId(int universityId) {
        return universityApplicationRepository.getApplicationByUniversityId(universityId);
    }
}
