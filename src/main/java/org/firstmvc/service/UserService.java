package org.firstmvc.service;


import org.firstmvc.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    void addUser(User user);

    void deleteUser(int id);

    void editUser(int id, User updateUser);

    User getUserById(int id);

    User getUserByEmail(String email);
}
