package com.example.demo;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
public class UserRepositoryIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> db1Container = new PostgreSQLContainer<>("postgres:13.3")
            .withInitScript("init_first.sql")
            .withDatabaseName("test")
            .withUsername("testuser")
            .withPassword("testpass");

    @Container
    private static final PostgreSQLContainer<?> db2Container = new PostgreSQLContainer<>("postgres:13.3")
            .withInitScript("init_second.sql")
            .withDatabaseName("test2")
            .withUsername("testuser2")
            .withPassword("testpass2");

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("data-sources[0].name", () -> "data-base-1");
        registry.add("data-sources[0].strategy", () -> "postgres");
        registry.add("data-sources[0].url", db1Container::getJdbcUrl);
        registry.add("data-sources[0].table", () -> "users");
        registry.add("data-sources[0].user", db1Container::getUsername);
        registry.add("data-sources[0].password", db1Container::getPassword);
        registry.add("data-sources[0].mapping.id", () -> "user_id");
        registry.add("data-sources[0].mapping.username", () -> "login");
        registry.add("data-sources[0].mapping.name", () -> "first_name");
        registry.add("data-sources[0].mapping.surname", () -> "last_name");

        registry.add("data-sources[1].name", () -> "data-base-2");
        registry.add("data-sources[1].strategy", () -> "postgres");
        registry.add("data-sources[1].url", db2Container::getJdbcUrl);
        registry.add("data-sources[1].table", () -> "users2");
        registry.add("data-sources[1].user", db2Container::getUsername);
        registry.add("data-sources[1].password", db2Container::getPassword);
        registry.add("data-sources[1].mapping.id", () -> "user_id2");
        registry.add("data-sources[1].mapping.username", () -> "login2");
        registry.add("data-sources[1].mapping.name", () -> "first_name2");
        registry.add("data-sources[1].mapping.surname", () -> "last_name2");
    }


    @Test
    void testGetAllUsers() {
        List<UserEntity> allUsers = userRepository.getAllUsers();
        assertNotNull(allUsers);
        assertEquals(10, allUsers.size());
    }
}
