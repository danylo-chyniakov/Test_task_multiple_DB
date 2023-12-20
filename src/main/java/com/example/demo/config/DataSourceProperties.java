package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties
public class DataSourceProperties {

    private List<DataSourcePropertyEntry> dataSources;

    @Data
    public static class DataSourcePropertyEntry {

        private String name;
        private String strategy;
        private String url;
        private String table;
        private String user;
        private String password;
        private Mapping mapping;

        @Data
        public static class Mapping {

            private String id;
            private String username;
            private String name;
            private String surname;
        }
    }
}
