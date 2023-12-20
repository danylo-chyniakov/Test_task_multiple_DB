package com.example.demo.dao;

import com.example.demo.model.UserEntity;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Data
public abstract class UserDAO {
    protected JdbcTemplate jdbcTemplate;
    protected RowMapper<UserEntity> rowMapper;

    public abstract List<UserEntity> findAll();
}