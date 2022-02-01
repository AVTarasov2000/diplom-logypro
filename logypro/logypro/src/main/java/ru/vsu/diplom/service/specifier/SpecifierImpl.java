package ru.vsu.diplom.service.specifier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.diplom.properties.SpecifierProperties;

import javax.lang.model.element.TypeElement;

@Service
public class SpecifierImpl implements Specifier <TypeElement> {

    @Autowired
    SpecifierProperties properties;

    @Override
    public boolean specify(TypeElement element) {
        return false;
    }
}
