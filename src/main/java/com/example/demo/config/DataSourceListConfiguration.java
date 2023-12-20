package com.example.demo.config;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.UserEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Data
public class DataSourceListConfiguration {

    private DataSourceProperties dataSourceProperties;

    public DataSourceListConfiguration(@Autowired DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public List<UserDAO> dataSourceList() {

        return dataSourceProperties.getDataSources().stream()
                .map(datasourceCFG -> {
                    UserDAO dao = new UserDAO() {
                        @Override
                        public List<UserEntity> findAll() {
                            return jdbcTemplate.query("select * from " + datasourceCFG.getTable(), rowMapper);
                        }
                    };

                    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource(datasourceCFG));
                    RowMapper<UserEntity> rowMapper = getUserEntityRowMapper(datasourceCFG);


                    dao.setJdbcTemplate(jdbcTemplate);
                    dao.setRowMapper(rowMapper);
                    return dao;
                })
                .collect(Collectors.toList());
    }

    private RowMapper<UserEntity> getUserEntityRowMapper(DataSourceProperties.DataSourcePropertyEntry datasourceCFG) {
        return (rs, rowNum) -> UserEntity.builder()
                .id(rs.getString(datasourceCFG.getMapping().getId()))
                .surname(datasourceCFG.getMapping().getSurname())
                .username(datasourceCFG.getMapping().getUsername())
                .name(datasourceCFG.getMapping().getName())
                .build();
    }

    private DriverManagerDataSource getDataSource(DataSourceProperties.DataSourcePropertyEntry datasourceCFG) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(datasourceCFG.getUrl());
        dataSource.setUsername(datasourceCFG.getUser());
        dataSource.setPassword(datasourceCFG.getPassword());

        return dataSource;
    }
}
