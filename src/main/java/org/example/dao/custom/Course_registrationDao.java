package org.example.dao.custom;

import org.example.bo.custom.Course_Refistration;
import org.example.dao.CrudDao;
import org.example.entity.Student_programDetail;
import org.hibernate.Session;

public interface Course_registrationDao extends CrudDao<Course_Refistration> {
    String getNewId();

    boolean save(Student_programDetail studentProgramDetail, Session session);

    String getspid(String sid, String pid);

}
