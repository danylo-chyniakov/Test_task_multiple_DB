package com.example.demo.repository;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    List<UserDAO> userDAOS;

    public UserRepository(@Autowired List<UserDAO> userDAOS) {
        this.userDAOS = userDAOS;
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        userDAOS.forEach(userDAO -> users.addAll(userDAO.findAll()));
        return users;
    }
}
