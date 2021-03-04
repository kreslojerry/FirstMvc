package org.firstmvc.dao;


import org.firstmvc.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUserList();
    void addUser(User user);
    void deleteUser(int id);
    void editUser(int id, User updateUser);
    User getUserById(int id);
    User getUserByEmail(String email);
}
