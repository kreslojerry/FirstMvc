package org.firstmvc.service;

import org.firstmvc.model.User;
import org.firstmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll(Sort.by("id"));
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(
                user.getPassword()
        ));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void editUser(int id, User updateUser) {
        updateUser.setPassword(passwordEncoder.encode(
                updateUser.getPassword()
        ));
        userRepository.save(
                getUserById(id).copyWithoutId(updateUser)
        );
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
