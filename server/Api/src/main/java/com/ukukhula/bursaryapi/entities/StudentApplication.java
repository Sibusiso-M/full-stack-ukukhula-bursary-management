package com.ukukhula.bursaryapi.entities;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class StudentApplication {
  private int id;
  private int studentID;
  private String motivation;
  private BigDecimal bursaryAmount;
  private String status;
  private String rejectionReason;
  private Date date;

  public StudentApplication(int id, int studentID, String motivation, BigDecimal bursaryAmount, String status,
      String rejectionReason, Date date) {
    this.id = id;
    this.studentID = studentID;
    this.motivation = motivation;
    this.bursaryAmount = bursaryAmount;
    this.status = status;
    this.rejectionReason = rejectionReason;
    this.date = date;
  }
}
