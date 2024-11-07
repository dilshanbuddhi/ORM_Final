package org.example.dao.custom;

import org.example.dao.CrudDao;
import org.example.entity.User;

import java.util.List;

public interface UserDao extends CrudDao<User> {
    boolean ifHaveAdmins();

    boolean registerAdmin(User user);

    List<User> getAllUsers();

    User getdata(String username);

    User getDataByrole(String role);

    boolean save(User user);

    boolean delete(String id);

    boolean update(User user);
}
