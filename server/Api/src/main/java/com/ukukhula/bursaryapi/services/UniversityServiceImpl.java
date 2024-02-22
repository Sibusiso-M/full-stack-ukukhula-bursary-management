package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.University;
import com.ukukhula.bursaryapi.repositories.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UniversityServiceImpl implements UniversityService {
  final
  UniversityRepository universityRepository;

  public UniversityServiceImpl(UniversityRepository universityRepository) {
    this.universityRepository = universityRepository;
  }

  @Override
  public University addUniversity(String name) {
    Integer id = universityRepository.addUniversity(name);
    return universityRepository.getUniversityById(id);
  }

  @Override
  public University getUniversityById(int id) {
    return universityRepository.getUniversityById(id);
  }

  @Override
  public List<University> getAllUniversities() {
    return universityRepository.getAllUniversities();
  }
}
