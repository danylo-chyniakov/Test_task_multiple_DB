package com.example.demo;

import com.example.demo.model.UserEntity;
import com.example.demo.openapi.model.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.web.UsersController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUsersGet() throws Exception {
        List<UserDto> mockUsers = Arrays.asList(
                new UserDto().id("1").username("user1").name("name1").surname("surname1"),
                new UserDto().id("2").username("user2").name("name2").surname("surname2")
        );

        Mockito.when(userService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(mockUsers.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("name1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("surname1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("user2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("name2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].surname").value("surname2"));
    }
}