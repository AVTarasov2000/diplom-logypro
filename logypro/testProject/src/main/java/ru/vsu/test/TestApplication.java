package ru.vsu.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.vsu.test")

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public void getSpecifier(){
        TestAutolog testAutolog = new ru.vsu.test.TestAutolog();
        System.out.println(testAutolog.test("test1"));

        testAutolog.test1("test2");

    }
}
