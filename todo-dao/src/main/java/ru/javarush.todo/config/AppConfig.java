package ru.javarush.todo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Value("${dataBaseConnectionSettings.dialect}")
    private String sqlDialect;
    @Value("${dataBaseConnectionSettings.driver}")
    private String sqlDriver;
    @Value("${dataBaseConnectionSettings.hbm2ddl}")
    private String hbm2ddl;

    @Value("${dataBaseConnectionSettings.jdbcDriver}")
    private String driverClassName;
    @Value("${dataBaseConnectionSettings.jdbcUrl}")
    private String jdbcUrl;
    @Value("${dataBaseConnectionSettings.username}")
    private String username;
    @Value("${dataBaseConnectionSettings.password}")
    private String password;

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("ru.javarush.todo.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        //properties.put(Environment.DIALECT, sqlDialect);
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        //properties.put(Environment.DRIVER, sqlDriver);
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        //properties.put(Environment.HBM2DDL_AUTO, hbm2ddl);
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/todo");
        //dataSource.setJdbcUrl(jdbcUrl);

        dataSource.setUsername("root");
        //dataSource.setUsername(username);
        dataSource.setPassword("qwerty");
        //dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        return transactionManager;
    }
}
