package com.ukukhula.bursaryapi.entities;

import java.math.BigDecimal;

import jakarta.websocket.Decoder.Binary;

public class StudentDocuments {
    private int id;
    private int applicationId;
    private byte[] amount;
    private byte[] IDCopy;
    private byte[] AcademicTranscript;
    private byte[] CurriculumVitae;

    public StudentDocuments(int id, int applicationId,
            byte[] amount,
            byte[] IDCopy,
            byte[] AcademicTranscript,
            byte[] CurriculumVitae) {
        this.id = id;
        this.applicationId = applicationId;
        this.amount = amount;
        this.AcademicTranscript = AcademicTranscript;
        this.CurriculumVitae = CurriculumVitae;
    }
}