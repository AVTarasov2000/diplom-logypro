package ru.vsu.diplom.service.specifier;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.diplom.configuration.processor.SpecificationsContainerConfiguringProcessor;
import ru.vsu.diplom.properties.SpecifierProperties;

import javax.lang.model.element.TypeElement;

@Service
@RequiredArgsConstructor
public class SpecifierImpl implements Specifier <TypeElement> {

    private final SpecifierProperties properties;
    private final SpecificationsContainerConfiguringProcessor specificationsContainerConfiguringProcessor;

    // TODO: 02.02.2022 после формирования мап, прописсать валидацию по ним
    @Override
    public boolean specify(TypeElement element) {

        return false;
    }
}
