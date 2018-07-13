package com.evan.blog.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("com.evan.blog.repository")
public class DataSourceConfiguration {
    @Value(value = "${jdbc.driver}")
    private String jdbcDriver;

    @Value(value = "${jdbc.url}")
    private String jdbcUrl;

    @Value(value = "${jdbc.username}")
    private String jdbcUser;

    @Value(value = "${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createComboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setPassword(jdbcPassword);

        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }
}
