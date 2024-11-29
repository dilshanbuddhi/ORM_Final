package org.example.bo.custom;

import org.example.bo.SuperBo;
import org.example.dto.Student_programDto;

import java.util.List;

public interface Course_Refistration extends SuperBo {

    void register(String number, String date, String programId, String stId, String paymentStatus, double amountPaid, double remaining);

    List<String> getAllProgrambyId(String id);

    String getspid(String sid, String pid);

    boolean isRegister(String stId, String programId);
}
