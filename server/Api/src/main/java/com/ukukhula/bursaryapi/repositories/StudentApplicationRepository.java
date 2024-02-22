package com.ukukhula.bursaryapi.repositories;

 import com.ukukhula.bursaryapi.entities.StudentApplication;

import java.util.List;

public interface StudentApplicationRepository {
    StudentApplication findByStudentID(int studentID);

    List<StudentApplication> getAllStudentsApplications();

    Integer updateStudentsApplicationStatus(int studentID, String status);
    Integer updateStudentsApplicationColumnValue(int studentID, String columnName, String value); //don't allow edit of status column unless status in review
}
