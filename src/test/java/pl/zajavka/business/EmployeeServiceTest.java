package pl.zajavka.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.zajavka.infrastructure.configuration.ApplicationConfiguration;
import pl.zajavka.infrastructure.database.jparepositories.EmployeeDataJpaRepository;
import pl.zajavka.infrastructure.database.model.EmployeeEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void firstTestForEmployeeFunctionality() {
        //given
        employeeService.create(someEmployee());
        //when
        List<EmployeeEntity> allEmployees = employeeService.findAll();
        // then
        Assertions.assertEquals(1, allEmployees.size());
    }

    private EmployeeEntity someEmployee() {
        return EmployeeEntity.builder()
                .name("Tomek")
                .surname("Romek")
                .salary(BigDecimal.valueOf(9547.11))
                .since(OffsetDateTime.of(2020, 10, 10, 12, 12, 12, 0, ZoneOffset.UTC))
                .build();
    }
}