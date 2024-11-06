package org.example.dao.custom.IMPL;

import config.FactoryConfiguration;
import org.example.dao.custom.ProgramDao;
import org.example.entity.Programme;
import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class ProgramDaoImpl implements ProgramDao {
    @Override
    public boolean save(Programme programme) {
        Session session = FactoryConfiguration.getInstance().getSession();

        session.beginTransaction();
        session.save(programme);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Programme> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from Programme";

        return session.createQuery(hql, Programme.class).list();
    }
}
