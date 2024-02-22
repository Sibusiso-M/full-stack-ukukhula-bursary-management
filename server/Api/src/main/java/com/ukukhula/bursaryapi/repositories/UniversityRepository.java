package com.ukukhula.bursaryapi.repositories;

import java.util.List;

import com.ukukhula.bursaryapi.entities.University;

public interface UniversityRepository {

  Integer addUniversity(String name);

  List<University> getAllUniversities();

  University getUniversityById(int id);
}