package org.example.bo.custom.BoIMPL;

import org.example.bo.custom.UserBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.UserDao;
import org.example.dto.UserDto;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = (UserDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.USER);
    @Override
    public boolean registerAdmin(UserDto e) {
        return userDao.registerAdmin(new User(e.getId(), e.getUsername(), e.getEmail(), e.getPassword(), e.getRole()));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        System.out.print(users.size());
        List<UserDto> userDtos= new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole()));
        }
        return userDtos;
    }

    @Override
    public UserDto getdata(String username) {
        User user = userDao.getdata(username);
        if (user == null) {
            return null;

        } else {
            return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        }
    }

    @Override
    public UserDto getdatabyRole(String role) {
        User user = userDao.getDataByrole(role);
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public boolean save(UserDto userDto) {
        return userDao.save(new User( userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getRole()));
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.delete(id);
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        return userDao.update(new User(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getRole()));
    }
}
