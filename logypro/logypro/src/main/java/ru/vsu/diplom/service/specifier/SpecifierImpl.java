package ru.vsu.diplom.service.specifier;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.diplom.properties.SpecifierProperties;
import ru.vsu.diplom.service.container.SpecificationsContainer;

import javax.lang.model.element.TypeElement;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpecifierImpl implements Specifier <TypeElement> {

    private final SpecifierProperties properties;
    private final Map<String, SpecificationsContainer> specificationsContainers;

    // TODO: 02.02.2022 после формирования мап, прописсать валидацию по ним(там лямбдачки будут) 
    @Override
    public boolean specify(TypeElement element) {
        return false;
    }
}
