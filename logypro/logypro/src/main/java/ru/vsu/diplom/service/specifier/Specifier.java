package ru.vsu.diplom.service.specifier;

import ru.vsu.diplom.service.logging.Logging;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.List;

public interface Specifier {
    public Boolean specify(TypeElement element);
    public Boolean specify(ExecutableElement element);
    public List <Logging> getLogging();
}
