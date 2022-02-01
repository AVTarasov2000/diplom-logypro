package ru.vsu.diplom.service.specifications;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.vsu.diplom.service.specification.Specification;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public abstract class SpecificationsContainer<T, P> {

    private Map<T, Specification <P>> specifications;

    public Specification<P> getSpecification(T key){
        return specifications.get(key);
    }
}
