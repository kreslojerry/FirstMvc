package org.firstmvc.util;

import org.firstmvc.model.User;
import org.firstmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        User emailValidUser = userService.getUserByEmail(user.getEmail());
        if (emailValidUser != null && emailValidUser.getId() != user.getId())
            errors.rejectValue("email", "", "Этот адрес почты уже занят");

        if (user.getName().isEmpty())
            errors.rejectValue("name", "", "Обязательное поле");

        if (user.getSurname().isEmpty())
            errors.rejectValue("surname","", "Обязательное поле");

        if(user.getEmail().isEmpty())
            errors.rejectValue("email", "", "Обязательное поле");

        if(user.getPassword().length() < 5)
            errors.rejectValue("password", "", "Пароль должен содержать минимум 5 символов");
    }
}
