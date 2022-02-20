package ru.vsu.diplom.annotation;

public @interface LoggingType {
    String[] name() default "";
}
