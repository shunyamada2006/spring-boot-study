package com.example.spring_tutorial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_tutorial.entity.User;
import com.example.spring_tutorial.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(String userName, String password, int roleId) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("ユーザー名を入力してください。");
        }
        if (!userRepository.findByUserName(userName).isEmpty()) {
            throw new IllegalArgumentException("そのユーザー名は既に使用されています。");
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRoleId(roleId);

        userRepository.save(user);
    }

    // ユーザーの一括取得メソッド
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}


