package com.ukukhula.bursaryapi.entities;

import lombok.Data;

@Data
public class UniversityApplication {
    private int id;
    private int universityId;
    private String motivation;
    private String status;
    private String rejectionReason;

    public UniversityApplication(int id, int universityId, String motivation, String status, String rejectionReason) {
        this.id = id;
        this.universityId = universityId;
        this.motivation = motivation;
        this.status = status;
        this.rejectionReason = rejectionReason;
    }
}
