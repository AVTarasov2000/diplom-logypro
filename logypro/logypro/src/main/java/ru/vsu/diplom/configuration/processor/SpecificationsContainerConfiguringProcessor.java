package ru.vsu.diplom.configuration.processor;

import lombok.Getter;
import ru.vsu.diplom.annotation.SpecificationContainer;
import ru.vsu.diplom.annotation.SpecificationType;
import ru.vsu.diplom.service.container.SpecificationsContainer;
import ru.vsu.diplom.service.specification.Specification;

import org.reflections.Reflections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecificationsContainerConfiguringProcessor {

    private final String packageToScan;
    @Getter
    private final Map <String, ? extends SpecificationsContainer> containers;

    public SpecificationsContainerConfiguringProcessor(String packageToScan) {
        this.packageToScan = packageToScan;
        containers = containers();
        setConfigurations(containers);
    }

    public SpecificationsContainer getContainer(String name){
        return containers.get(name);
    }

    private Map <String, ? extends SpecificationsContainer> containers() {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationContainer.class);
        return set.stream().collect(
                Collectors.toMap(
                        this::getSpecificationContainerAnnotation,
                        x -> (SpecificationsContainer) createInstance(x)
                ));
    }

    private void setConfigurations(Map <String, ? extends SpecificationsContainer> containers) {
        Reflections reflections = new Reflections(packageToScan);
        Set <Class <?>> set = reflections.getTypesAnnotatedWith(SpecificationType.class);

        set.forEach(x->containers
                .get(getSpecificationTypeContainerName(x))
                .addSpecification(getSpecificationTypeKey(x), (Specification) createInstance(x))
        );
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
            cls.getConstructor().setAccessible(true);
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new ClassCastException();
    }
}
