package ru.vsu.diplom;

import ru.vsu.diplom.annotation.SpecificationFunc;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;

@SpecificationFunc(name = "publicFunc")
public class TestSpecFunc2 extends ru.vsu.diplom.service.specification.SpecificationFunc {
    @Override
    public boolean suit(ExecutableElement element) {
        return element.getModifiers().contains(Modifier.PUBLIC);
    }
}
