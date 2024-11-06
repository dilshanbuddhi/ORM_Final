package org.example.dao.custom.IMPL;

import config.FactoryConfiguration;
import jakarta.persistence.NoResultException;
import org.example.dao.custom.StudentDao;
import org.example.entity.Student;
import org.example.entity.User;
import org.hibernate.Session;

import java.util.List;

public class StudetDaoImpl implements StudentDao {

    @Override
    public boolean addStudent(Student student, User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        // Set the user on the student object that will be saved
        student.setUser(user);

        // Save the student entity with the associated user
        session.save(student);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Student> getAllStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from Student";

        return session.createQuery(hql, Student.class).list();
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "update Student set name = :name, email = :email, tel = :tel, address = :address, dob = :dob where id = :id";
        session.createQuery(hql)
                .setParameter("name", student.getName())
                .setParameter("email", student.getEmail())
                .setParameter("tel", student.getTel())
                .setParameter("address", student.getAddress())
                .setParameter("dob", student.getDob())
                .setParameter("id", student.getId())
                .executeUpdate();
        session.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        String hql = "delete from Student where id = :id";
        session.createQuery(hql)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Student search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student = null;

        try {
            String hql = "FROM Student WHERE id = :id";
            student = session.createQuery(hql, Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("No user found with username: " + id);
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return student; // Will return null if no user is found
    }
}
