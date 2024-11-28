package org.example.bo.custom;

import javafx.collections.ObservableList;
import org.example.bo.SuperBo;
import org.example.dto.ProgramDto;
import org.example.entity.Programme;

public interface ProgramBo extends SuperBo {
    boolean addProgram(Programme programme);

    ObservableList<ProgramDto> getAllProgram();

    boolean deleteProgram(String programId);

    ProgramDto searchProgram(String programId);

    boolean updateProgram(ProgramDto programDto);

    ProgramDto searchProgramByname(String cname);

    String getID(String pname);
}
