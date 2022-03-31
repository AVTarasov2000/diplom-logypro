package ru.vsu.diplom.configuration.processor;

import lombok.Getter;
import ru.vsu.diplom.annotation.LoggingType;
import ru.vsu.diplom.annotation.SpecificationType;
import ru.vsu.diplom.service.logging.Logging;
import ru.vsu.diplom.service.specification.Specification;

import org.reflections.Reflections;
import ru.vsu.diplom.service.specification.SpecificationFunc;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class SpecificationsContainerConfiguringProcessor {

    private final Reflections reflections;
    @Getter
    private final Map <String, Specification> configurations;
    @Getter
    private final Map <String, SpecificationFunc> configurationsFunc;
    @Getter
    private final List <Logging> loging;

    public Boolean specify(TypeElement element, BinaryOperator <Boolean> accumulator) {
        return configurations.values().stream()
                .filter(Objects::nonNull)
                .map(x -> x.suit(element))
                .reduce(accumulator)
                .orElse(false);
    }

    public Boolean specifyOr(TypeElement element) {
        return specify(element, (a, b) -> a || b);
    }

    public Boolean specifyAnd(TypeElement element) {
        return specify(element, (a, b) -> a && b);
    }

    public boolean specify(TypeElement element, String name) {
        if(!configurations.containsKey(name)){
            throw new IllegalArgumentException("нет класса со значением " + name);
        }
        return configurations.get(name).suit(element);
    }

    public boolean specify(ExecutableElement element, String name) {
        if(!configurations.containsKey(name)){
            throw new IllegalArgumentException("нет класса со значением " + name);
        }
        return configurationsFunc.get(name).suit(element);
    }

    public SpecificationsContainerConfiguringProcessor(String packageToScan) {
        this.reflections = new Reflections(packageToScan);
        configurations = prepareConfigurations();
        configurationsFunc = prepareConfigurationsFunc();
        loging = prepareLoging();
    }

    private List<Logging> prepareLoging() {
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(LoggingType.class);
        return set.stream()
                .map(this::createLogInstance)
                .collect(Collectors.toList());
    }

    private Map <String, Specification> prepareConfigurations() {
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationType.class);
        return set.stream().collect(Collectors.toMap(
                this::getSpecificationTypeAnnotationName,
                this::createInstance));
    }

    private Map <String, SpecificationFunc> prepareConfigurationsFunc() {
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(ru.vsu.diplom.annotation.SpecificationFunc.class);
        return set.stream().collect(Collectors.toMap(
                this::getSpecificationFuncAnnotationName,
                this::createFuncSpecInstance));
    }

    private String getSpecificationTypeAnnotationName(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationType.class))
                .map(SpecificationType::name)
                .orElseThrow(ClassCastException::new);
    }

    private String getSpecificationFuncAnnotationName(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(ru.vsu.diplom.annotation.SpecificationFunc.class))
                .map(ru.vsu.diplom.annotation.SpecificationFunc::name)
                .orElseThrow(ClassCastException::new);
    }

    private Specification createInstance(Class <?> cls) {
        try {
            cls.getConstructor().setAccessible(true);
            return (Specification) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new ClassCastException();
    }

    private SpecificationFunc createFuncSpecInstance(Class <?> cls) {
        try {
            cls.getConstructor().setAccessible(true);
            return (SpecificationFunc) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new ClassCastException();
    }

    private Logging createLogInstance(Class <?> cls) {
        try {
            cls.getConstructor().setAccessible(true);
            return (Logging) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new ClassCastException();
    }
}
