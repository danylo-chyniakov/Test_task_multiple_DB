package com.example.demo.web;

import com.example.demo.openapi.api.DefaultApi;
import com.example.demo.openapi.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController implements DefaultApi {

    private final UserService userService;

    public UsersController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserDto>> usersGet() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
