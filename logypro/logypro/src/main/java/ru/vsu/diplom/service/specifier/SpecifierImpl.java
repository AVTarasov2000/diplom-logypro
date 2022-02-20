package ru.vsu.diplom.service.specifier;


import lombok.RequiredArgsConstructor;
import ru.vsu.diplom.configuration.processor.SpecificationsContainerConfiguringProcessor;
import ru.vsu.diplom.properties.SpecifierProperties;
import static ru.vsu.diplom.service.utils.OperatorUtils.getOperator;

import javax.lang.model.element.TypeElement;
import java.util.function.BinaryOperator;

@RequiredArgsConstructor
public class SpecifierImpl implements Specifier {

    private final SpecifierProperties properties;
    private final SpecificationsContainerConfiguringProcessor specificationsContainerConfiguringProcessor;

    @Override
    public Boolean specify(TypeElement element) {
        BinaryOperator<Boolean> operator = getOperator(properties.getConnectionType());
        return properties.getSpecificationTypes().stream()
                .map(s -> specificationsContainerConfiguringProcessor.specify(element, s))
                .reduce(operator)
                .orElse(false);
    }
}
