package ru.vsu.diplom.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecifierProperties {
    private List<String> specificationTypes;
    private String connectionType;
}
