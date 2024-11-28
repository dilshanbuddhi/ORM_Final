package org.example.dao.custom;

import org.example.dao.CrudDao;
import org.example.entity.Programme;

import java.util.List;

public interface ProgramDao extends CrudDao<Programme> {
    boolean save(Programme programme);

    List<Programme> getAll();

    boolean delete(String programId);

    Programme search(String programId);

    boolean update(Programme programme);

    Programme searchByName(String cname);

    String getID(String pname);
}

