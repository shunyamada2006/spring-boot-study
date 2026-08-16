package com.example.spring_tutorial.form;
import lombok.Data;

@Data
public class UserRegisterForm {
    private String userName;
    private String password;
    private Integer roleId;
}
