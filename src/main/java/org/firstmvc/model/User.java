package org.firstmvc.model;

import javax.persistence.*;


@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
//    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;
//    @Size(min = 1, max = 15, message = "Фамилия дожна иметь длину от 1 до 15 символов")
    private String surname;
//    @Email(message = "Неправильный формат адреса почты")
//    @NotEmpty(message = "Адрес почты не должен быть пустым")
    private String email;

    private String password;

    public User(){}

    public User(int id,String name, String surname, String email) {
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User copyWithoutId(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        return this;
    }
}
