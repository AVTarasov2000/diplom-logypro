package ru.vsu.diplom.service.specifications;

import org.springframework.stereotype.Component;
import ru.vsu.diplom.annotations.SpecificationType;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

@Component
@SpecificationType(name = "annotationSpecifications")
public class AnnotationSpecifications extends SpecificationsContainer <Annotation, ElementType> {
}
