package ru.vsu.diplom.configuration.processor;

import org.reflections.Reflections;
import ru.vsu.diplom.annotation.SpecificationContainer;
import ru.vsu.diplom.annotation.SpecificationType;
import ru.vsu.diplom.service.container.SpecificationsContainer;
import ru.vsu.diplom.service.specification.Specification;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecificationsProcessor {

    String packageToScan;

    public SpecificationsProcessor(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    public Map <String, SpecificationsContainer> containers() {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationContainer.class);
        return setConfigurations(set.stream().collect(
                Collectors.toMap(
                        this::getSpecificationContainerAnnotation,
                        x -> (SpecificationsContainer) createInstance(x)
                )));
    }

    public Map <String, SpecificationsContainer> setConfigurations(Map <String, SpecificationsContainer> containers) {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationType.class);

        set.forEach(x->containers
                .get(getSpecificationTypeContainerName(x))
                .addSpecification(getSpecificationTypeKey(x), (Specification) createInstance(x))
        );
        return containers;
    }

    private String getSpecificationContainerAnnotation(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationContainer.class))
                .map(SpecificationContainer::name)
                .orElseThrow(ClassCastException::new);
    }

    private String getSpecificationTypeContainerName(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationType.class))
                .map(SpecificationType::containerName)
                .orElseThrow(ClassCastException::new);
    }

    private String getSpecificationTypeKey(Class <?> cls) {
        return Optional.ofNullable(cls.getAnnotation(SpecificationType.class))
                .map(SpecificationType::key)
                .orElseThrow(ClassCastException::new);
    }

    private Object createInstance(Class<?> cls){
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new ClassCastException();
    }

}
