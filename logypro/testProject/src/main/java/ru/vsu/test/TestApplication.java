package ru.vsu.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.vsu.test")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
//
//    @Bean
//    public Specifier getSpecifier(){
//        return new SpecifierImpl(
//                new SpecifierProperties(Collections.singletonList("test"), "OR")
//                , new SpecificationsContainerConfiguringProcessor("ru.vsu.test"));
//    }
}
