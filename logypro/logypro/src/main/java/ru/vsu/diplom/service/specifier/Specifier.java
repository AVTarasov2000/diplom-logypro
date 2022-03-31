package ru.vsu.diplom.service.specifier;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public interface Specifier {
    public Boolean specify(TypeElement element);
    public Boolean specify(ExecutableElement element);
}
