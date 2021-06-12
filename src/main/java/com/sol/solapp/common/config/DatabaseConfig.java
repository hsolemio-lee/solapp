package com.sol.solapp.common.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@Slf4j
public class DatabaseConfig {
    private final ApplicationContext applicationContext;

    @Value("${spring.datasource.url}")
    private String primaryJdbcUrl;

    @Value("${spring.datasource.username}")
    private String primaryUserName;

    @Value("${spring.datasource.driver-class-name}")
    private String primaryDriver;

    @Value("${spring.datasource.password}")
    private String primaryPassword;

    @Bean(name = "datasource")
    @Primary
    public HikariDataSource primaryDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setUsername(primaryUserName);
        hikariDataSource.setPassword(primaryPassword);
        hikariDataSource.setDriverClassName(primaryDriver);
        hikariDataSource.setJdbcUrl(primaryJdbcUrl);
        hikariDataSource.setMaximumPoolSize(10);

        return hikariDataSource;
    }

}
