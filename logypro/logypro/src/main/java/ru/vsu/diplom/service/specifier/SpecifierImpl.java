package ru.vsu.diplom.service.specifier;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.diplom.configuration.processor.SpecificationsContainerConfiguringProcessor;
import ru.vsu.diplom.properties.SpecifierProperties;

import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

@Service
@RequiredArgsConstructor
public class SpecifierImpl implements Specifier {

    private final SpecifierProperties properties;
    private final SpecificationsContainerConfiguringProcessor specificationsContainerConfiguringProcessor;
    private Map<String, BinaryOperator <Boolean>> operations = new HashMap();
    {
        operations.put("OR",(a, b)->a||b);
        operations.put("AND",(a, b)->a&&b);
    }

    @Override
    public Boolean specify(TypeElement element) {
        BinaryOperator<Boolean> operator =
                properties.getConnectionTypes() == null?
                        operations.get(properties.getConnectionTypes()):
                        operations.get("OR");
        return properties.getSpecificationTypes().stream()
                .map(s -> specificationsContainerConfiguringProcessor.specify(element, s))
                .reduce(operator)
                .orElse(false);
    }
}
