package ru.vsu.diplom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vsu.diplom.service.specifications.AnnotationSpecifications;
import ru.vsu.diplom.service.specifications.SignatureSpecifications;
import ru.vsu.diplom.service.specifications.SpecificationsContainer;

import java.util.Map;

@Configuration
public class SpecificationConf {

    // TODO: 02.02.2022 сформировать мапы спецификаций и контейнеров спецификаций
    @Bean
    public AnnotationSpecifications getAnnotationSpec(){
        return new AnnotationSpecifications();
    }

    @Bean
    public SignatureSpecifications getSignatureSpec(){
        return null;
    }

    @Bean
    public Map <String, SpecificationsContainer> getContainers(){
        return null;
    }
}
