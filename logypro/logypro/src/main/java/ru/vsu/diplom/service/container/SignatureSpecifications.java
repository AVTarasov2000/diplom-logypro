package ru.vsu.diplom.service.container;

import org.springframework.stereotype.Component;
import ru.vsu.diplom.annotation.SpecificationContainer;

import java.lang.annotation.ElementType;

@Component
@SpecificationContainer(name = "signatureSpecifications")
public class SignatureSpecifications extends SpecificationsContainer <String, ElementType> {
}
