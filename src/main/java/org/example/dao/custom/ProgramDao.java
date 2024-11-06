package org.example.dao.custom;

import org.example.dao.CrudDao;
import org.example.entity.Programme;

import java.util.List;

public interface ProgramDao extends CrudDao<Programme> {
    boolean save(Programme programme);

    List<Programme> getAll();
}
