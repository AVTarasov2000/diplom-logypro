package ru.vsu.diplom;

import ru.vsu.diplom.annotation.SpecificationType;
import ru.vsu.diplom.service.specification.Specification;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@SpecificationType(name = "test")
public class TestSpecType extends Specification {
    @Override
    public boolean suit(TypeElement element) {
        return true;
//        return element.getModifiers().contains(Modifier.PUBLIC);
    }
}
