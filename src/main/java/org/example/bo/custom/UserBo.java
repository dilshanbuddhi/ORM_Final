package org.example.bo.custom;

import org.example.bo.SuperBo;
import org.example.dto.UserDto;

import java.util.List;

public interface UserBo extends SuperBo {
    boolean registerAdmin(UserDto admin);

    List<UserDto> getAllUsers();

    UserDto getdata(String username);

    UserDto getdatabyRole(String role);


    boolean save(UserDto userDto);

    boolean deleteUser(String id);

    boolean updateUser(UserDto userDto);
}
