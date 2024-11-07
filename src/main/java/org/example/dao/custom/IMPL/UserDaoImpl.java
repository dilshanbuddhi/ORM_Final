package org.example.dao.custom.IMPL;

import config.FactoryConfiguration;
import jakarta.persistence.NoResultException;
import org.example.dao.custom.UserDao;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean ifHaveAdmins() {
        boolean ishaveadmins = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from User where role = 'admin'";
        try {
            ishaveadmins = session.createQuery(hql).list().size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ishaveadmins;
    }

    @Override
    public boolean registerAdmin(User user) {
        System.out.println(user);
        boolean result = false;
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "from User";
        return session.createQuery(hql, User.class).list();
    }

    @Override
    public User getdata(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = null;

        try {
            String hql = "FROM User WHERE username = :username";
            user = session.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
            System.out.println("No user found with username: " + username);
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return user;
    }

    @Override
    public User getDataByrole(String role) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = null;

        try {
            String hql = "FROM User WHERE role = :role";
            user = session.createQuery(hql, User.class)
                    .setParameter("role", role)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no result is found
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return user; // Will return null if no user is found

    }

    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction();
        session.save(user);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        String hql = "delete from User where id = :id";
        session.createQuery(hql)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        return true;
    }

}
