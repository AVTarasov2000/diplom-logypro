package ru.vsu.diplom.service.specification;

import javax.lang.model.element.TypeElement;

public abstract class Specification {
    public abstract boolean suit(TypeElement element);
}
