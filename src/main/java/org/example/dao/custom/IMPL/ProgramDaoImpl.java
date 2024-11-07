package org.example.dao.custom.IMPL;

import config.FactoryConfiguration;
import jakarta.persistence.NoResultException;
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

    @Override
    public boolean delete(String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();

        session.beginTransaction();
        String hql = "delete from Programme where programId = :programId";
        session.createQuery(hql)
                .setParameter("programId", programId)
                .executeUpdate();
        session.getTransaction().commit();
        return false;
    }

    @Override
    public Programme search(String programId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Programme programme = null;

        try {
            String hql = "FROM Programme WHERE programId = :programId";
            programme = session.createQuery(hql, Programme.class)
                    .setParameter("programId", programId)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("No user found with programm: " + programme);
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return programme; // Will return null if no user is found

    }

    @Override
    public boolean update(Programme programme) {
        Session session = FactoryConfiguration.getInstance().getSession();

        session.beginTransaction();
        session.update(programme);
        session.getTransaction().commit();
        return true;
    }
}
