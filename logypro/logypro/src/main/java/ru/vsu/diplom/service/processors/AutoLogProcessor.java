package ru.vsu.diplom.service.processors;

import com.google.auto.service.AutoService;
import lombok.RequiredArgsConstructor;
import ru.vsu.diplom.configuration.processor.SpecificationsContainerConfiguringProcessor;
import ru.vsu.diplom.properties.SpecifierProperties;
import ru.vsu.diplom.service.specifier.Specifier;
import ru.vsu.diplom.service.specifier.SpecifierImpl;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("ru.vsu.diplom.annotations.AutoLog")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
@RequiredArgsConstructor
public class AutoLogProcessor extends AbstractProcessor {

    private final Specifier specifier;

    @Override
    public boolean process(Set <? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            Map <Boolean, List <Element>> annotatedMethods = annotatedElements.stream().collect(
                    Collectors.partitioningBy(element ->
                            ((ExecutableType) element.asType()).getParameterTypes().size() == 1
                                    && element.getSimpleName().toString().startsWith("set")));

            List<Element> setters = annotatedMethods.get(true);
            List<Element> otherMethods = annotatedMethods.get(false);

//            specifier.specify(); //todo специфировать
        }
        return true;
    }
}
