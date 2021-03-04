package org.firstmvc.controller;

import org.firstmvc.model.User;
import org.firstmvc.service.UserService;
import org.firstmvc.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

//    @Autowired
//    public MainController(UserDAO userService,
//                          UserValidator userValidator) {
//        this.userService = userService;
//        this.userValidator = userValidator;
//    }

    @GetMapping("/")
    public String main(@RequestParam(value = "name",
            required = false,
            defaultValue = "nobody") String name,
                       Model model) {
        model.addAttribute("name", name);
        return "/index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "/users";
    }

    //Отображение страницы редактирования
    @GetMapping("/users/{id}/edit")
    public String editView(@PathVariable("id") int id,
                          Model model) {
        User user = userService.getUserById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "/edit";
    }

    //Обновление данных пользователя
    @PostMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") int id,
                           @ModelAttribute /*@Valid*/ User user,
                           BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors())
            return "/edit";
        userService.editUser(id, user);
        return "redirect:/users";
    }

    //Удаление пользователя
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    //Отображение страницы добавления
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/newUser";
    }

    //Добавление нового пользователя
    @PostMapping("/new")
    public String addUser(@ModelAttribute /*@Valid*/ User user,
                          BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors())
            return "/newUser";
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String loginView(@RequestParam(value = "error", required = false) Boolean error,
                            Model model) {
        if (error != null && error)
            model.addAttribute("error", true);
        return "/login";
    }

}
