package ru.vsu.diplom.service.specification;

import javax.lang.model.element.ExecutableElement;

public abstract class SpecificationFunc {
    public abstract boolean suit(ExecutableElement element);
}
