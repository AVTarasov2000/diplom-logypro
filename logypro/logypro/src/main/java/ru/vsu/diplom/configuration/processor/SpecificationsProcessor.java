package ru.vsu.diplom.configuration.processor;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import ru.vsu.diplom.annotation.SpecificationContainer;
import ru.vsu.diplom.annotation.SpecificationType;
import ru.vsu.diplom.service.container.SpecificationsContainer;

import java.util.*;
import java.util.stream.Collectors;

public class SpecificationsProcessor {

    @Value("logypro.package-to-scan")
    String packageToScan;

    public Map <String, List <SpecificationsContainer>> containers() {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationContainer.class);
        return set.stream().collect(
                Collectors.groupingBy(
                        this::getSpecificationContainerAnnotation,
                        Collectors.mapping(x -> {
                            try {
                                return (SpecificationsContainer) x.newInstance();
                            } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            throw new ClassCastException();
                        }, Collectors.toList())
                ));

    }

    public void configurations() {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationType.class);

        List <String> collect = set.stream()
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
    }

    private String getSpecificationContainerAnnotation(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationContainer.class))
                .map(SpecificationContainer::name)
                .orElseThrow(ClassCastException::new);
    }

    private String getSpecificationTypeAnnotation(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationType.class))
                .map(SpecificationType::name)
                .orElseThrow(ClassCastException::new);
    }

}
