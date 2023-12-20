package com.example.demo.service;

import com.example.demo.openapi.model.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream().map(
                userEntity -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(userEntity.getId());
                    userDto.setName(userEntity.getName());
                    userDto.setSurname(userEntity.getSurname());
                    userDto.setUsername(userEntity.getUsername());
                    return userDto;
                }
        ).collect(Collectors.toList());
    }
}
