package ru.vsu.diplom.service.container;

import ru.vsu.diplom.service.specification.Specification;

import java.util.Map;

public abstract class SpecificationsContainer<T, P> {

    private Map<T, Specification <P>> specifications;
    public Specification<P> getSpecification(T key){
        return specifications.get(key);
    }
    SpecificationsContainer(){}
}
