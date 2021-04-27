package org.firstmvc.dao;

import org.firstmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JdbcTemplateUserDAO implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUserList() {
        return jdbcTemplate.query("SELECT * FROM Users ORDER BY id", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO Users(name, surname, email, password) VALUES(?, ?, ?, ?)", user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id=?", id);
    }

    @Override
    public void editUser(int id, User updateUser) {
        jdbcTemplate.update("UPDATE Users SET name=?, surname=?, email=? WHERE id=?", updateUser.getName(), updateUser.getSurname(), updateUser.getEmail(), id);
    }

    @Override
    public User getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE id = ?", new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE email=?", new BeanPropertyRowMapper<>(User.class), email)
                .stream().findAny().orElse(null);
    }
}
