package org.example.dao.custom;

import org.example.bo.custom.Course_Refistration;
import org.example.dao.CrudDao;

public interface Course_registrationDao extends CrudDao<Course_Refistration> {
    String getNewId();
}
