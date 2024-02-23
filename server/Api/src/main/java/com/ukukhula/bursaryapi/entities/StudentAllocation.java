package com.ukukhula.bursaryapi.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentAllocation {
    private int id;
    private int studentId;
    private BigDecimal amount;
    private int year;

    public StudentAllocation(int id, int studentId, BigDecimal amount, int year){
        this.id = id;
        this.studentId = studentId;
        this.amount = amount;
        this.year = year;
    }
}
