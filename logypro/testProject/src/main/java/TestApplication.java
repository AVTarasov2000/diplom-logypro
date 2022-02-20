
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vsu.diplom.configuration.processor.SpecificationsContainerConfiguringProcessor;
import ru.vsu.diplom.properties.SpecifierProperties;
import ru.vsu.diplom.service.specifier.Specifier;
import ru.vsu.diplom.service.specifier.SpecifierImpl;

import java.util.Collections;

@Configuration
@SpringBootApplication(scanBasePackages = ":testProject")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public Specifier getSpecifier(){
        return new SpecifierImpl(
                new SpecifierProperties(Collections.singletonList("test"), "OR")
                , new SpecificationsContainerConfiguringProcessor("/logypro"));
    }
}
