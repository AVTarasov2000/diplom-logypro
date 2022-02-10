package ru.vsu.diplom.service.container;

import ru.vsu.diplom.service.specification.Specification;

import java.util.Map;

public abstract class SpecificationsContainer<T, P> {

    SpecificationsContainer(){}

    private Map<T, Specification <P>> specifications;

    public Specification<P> getSpecification(T key){
        return specifications.get(key);
    }

    public void addSpecification(T key, Specification <P> val){
        specifications.put(key, val);
    }
}
