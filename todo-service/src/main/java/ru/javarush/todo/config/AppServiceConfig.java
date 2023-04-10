package ru.javarush.todo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.javarush.todo")
@Import(AppDaoConfig.class)
public class AppServiceConfig {
}