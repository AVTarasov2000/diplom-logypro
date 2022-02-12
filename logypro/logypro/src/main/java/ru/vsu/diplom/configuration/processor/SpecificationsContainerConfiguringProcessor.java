package ru.vsu.diplom.configuration.processor;

import lombok.Getter;
import ru.vsu.diplom.annotation.SpecificationType;
import ru.vsu.diplom.service.specification.Specification;

import org.reflections.Reflections;

import javax.lang.model.element.TypeElement;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class SpecificationsContainerConfiguringProcessor {

    private final String packageToScan;
    @Getter
    private final Map <String, Specification> configurations;

    public Boolean specify(TypeElement element, BinaryOperator<Boolean> accumulator){
        return configurations.values().stream()
                .map(x->x.suit(element))
                .reduce(accumulator)
                .orElse(false);
    }

    public Boolean specifyOr(TypeElement element){
        return specify(element, (a, b)->a||b);
    }

    public Boolean specifyAnd(TypeElement element){
        return specify(element, (a, b)->a&&b);
    }

    public boolean specify(TypeElement element, String name){
        return configurations.get(name).suit(element);
    }

    public SpecificationsContainerConfiguringProcessor(String packageToScan) {
        this.packageToScan = packageToScan;
        configurations = prepareConfigurations();
    }

    private Map <String, Specification> prepareConfigurations() {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationType.class);
        return set.stream()
                .collect(Collectors.toMap(
                        this::getSpecificationTypeAnnotationName,
                        this::createInstance));
    }

    private String getSpecificationTypeAnnotationName(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationType.class))
                .map(SpecificationType::name)
                .orElseThrow(ClassCastException::new);
    }

    private Specification createInstance(Class<?> cls){
        try {
            cls.getConstructor().setAccessible(true);
            return (Specification) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new ClassCastException();
    }
}
