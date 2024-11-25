package org.example.bo.custom;

import org.example.bo.SuperBo;

import java.util.List;

public interface Course_Refistration extends SuperBo {
    void register(String number, String date, String programId, String stId, String paymentStatus, double amountPaid, double remaining);

    List<String> getAllProgrambyId(String id);
}
