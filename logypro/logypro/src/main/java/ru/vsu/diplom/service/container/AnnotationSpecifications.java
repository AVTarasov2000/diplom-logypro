package ru.vsu.diplom.service.container;

import org.springframework.stereotype.Component;
import ru.vsu.diplom.annotation.SpecificationContainer;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

@Component
@SpecificationContainer(name = "annotationSpecifications")
public class AnnotationSpecifications extends SpecificationsContainer <Annotation, ElementType> {
}
