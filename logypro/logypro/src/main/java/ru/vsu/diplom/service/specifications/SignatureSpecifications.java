package ru.vsu.diplom.service.specifications;

import org.springframework.stereotype.Component;
import ru.vsu.diplom.annotations.SpecificationType;

import java.lang.annotation.ElementType;

@Component
@SpecificationType(name = "signatureSpecifications")
public class SignatureSpecifications extends SpecificationsContainer <String, ElementType> {
}
