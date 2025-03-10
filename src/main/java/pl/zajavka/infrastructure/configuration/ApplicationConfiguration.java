package pl.zajavka.infrastructure.configuration;
import org.springframework.context.annotation.*;
import pl.zajavka.EmployeeRunner;

@Configuration
@ComponentScan(basePackageClasses = EmployeeRunner.class)
@Import(PersistenceJPAConfiguration.class)
public class ApplicationConfiguration {


}
