package ru.vsu.diplom.service.processors;

import com.google.auto.service.AutoService;
import lombok.RequiredArgsConstructor;
import ru.vsu.diplom.service.specifier.Specifier;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;
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
            Set <? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            List <Element> matched = annotatedElements.stream()
                    .map(TypeElement.class::cast)
                    .filter(specifier::specify)
                    .collect(Collectors.toList());

            matched.forEach(x-> System.out.println(x.getSimpleName()));
        }
        return true;
    }
}
