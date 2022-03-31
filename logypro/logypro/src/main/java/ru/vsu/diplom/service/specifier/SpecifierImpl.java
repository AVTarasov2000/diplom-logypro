package ru.vsu.diplom.service.specifier;


import lombok.RequiredArgsConstructor;
import ru.vsu.diplom.configuration.processor.SpecificationsContainerConfiguringProcessor;
import ru.vsu.diplom.properties.SpecifierProperties;
import ru.vsu.diplom.service.logging.Logging;

import static ru.vsu.diplom.service.utils.OperatorUtils.getOperator;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

@RequiredArgsConstructor
public class SpecifierImpl implements Specifier {

    private final SpecifierProperties properties;
    private final SpecificationsContainerConfiguringProcessor specificationsContainerConfiguringProcessor;

    @Override
    public Boolean specify(TypeElement element) {
        BinaryOperator<Boolean> operator = getOperator(properties.getConnectionType());
        return properties.getSpecificationTypes().stream()
                .filter(Objects::nonNull)
                .map(s -> specificationsContainerConfiguringProcessor.specify(element, s))
                .reduce(operator)
                .orElse(false);
    }

    @Override
    public Boolean specify(ExecutableElement element) {
        BinaryOperator<Boolean> operator = getOperator(properties.getConnectionType());
        return properties.getSpecificationTypes().stream()
                .filter(Objects::nonNull)
                .map(s -> specificationsContainerConfiguringProcessor.specify(element, s))
                .reduce(operator)
                .orElse(false);
    }

    @Override
    public List <Logging> getLogging() {
        return specificationsContainerConfiguringProcessor.getLoging();
    }
}
