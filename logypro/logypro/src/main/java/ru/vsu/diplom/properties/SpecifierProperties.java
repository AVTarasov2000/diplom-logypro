package ru.vsu.diplom.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "specifier")
public class SpecifierProperties {
    List<String> signatureSpecifications;
    List<String> annotationSpecifications;
    List<String> specificationTypes;
}
