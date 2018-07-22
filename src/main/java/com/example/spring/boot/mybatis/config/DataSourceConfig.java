package com.example.spring.boot.mybatis.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数源bean配置
 *
 * @author Quifar
 */
@Configuration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "sharding.jdbc.datasource.ds0")
    @Primary
    public DataSource ds0() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "sharding.jdbc.datasource.ds1")
    public DataSource ds1() {
        return DataSourceBuilder.create().build();
    }

}
