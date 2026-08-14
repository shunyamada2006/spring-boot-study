package com.example.spring_tutorial.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_tutorial.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUserName(String userName);
}