package ru.vsu.diplom.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "specifier")
@Data
public class SpecifierProperties {
    List<String> specificationTypes;
    String connectionTypes;
}
