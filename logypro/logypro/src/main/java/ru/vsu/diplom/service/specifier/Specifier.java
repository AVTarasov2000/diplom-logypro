package ru.vsu.diplom.service.specifier;

import javax.lang.model.element.TypeElement;

public interface Specifier {
    public Boolean specify(TypeElement element);
}
