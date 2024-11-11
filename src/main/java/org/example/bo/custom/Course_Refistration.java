package org.example.bo.custom;

import org.example.bo.SuperBo;

public interface Course_Refistration extends SuperBo {
    void register(String number, String date, String programId, String stId, String paymentStatus, String amountPaid);
}
