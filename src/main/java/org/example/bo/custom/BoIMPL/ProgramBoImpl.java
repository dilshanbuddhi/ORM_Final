package org.example.bo.custom.BoIMPL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.bo.custom.ProgramBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.ProgramDao;
import org.example.dto.ProgramDto;
import org.example.entity.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgramBoImpl implements ProgramBo {
    ProgramDao programDao = (ProgramDao) DaoFactory.daoFactory.getDao(DaoFactory.DaoTypes.PROGRAM);
    @Override
    public boolean addProgram(Programme programme) {
        return programDao.save(new Programme(programme.getProgramId(), programme.getName(), programme.getDuration(), programme.getFees()));
    }

    @Override
    public ObservableList<ProgramDto> getAllProgram() {
        List<ProgramDto> programDtos = new ArrayList<>();
        List<Programme> program = programDao.getAll();
        for (Programme p : program) {
            programDtos.add(new ProgramDto(p.getProgramId(), p.getName(), p.getDuration(), p.getFees()));
        }

        return FXCollections.observableArrayList(programDtos);
    }
}
