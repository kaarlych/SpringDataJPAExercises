package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.infrastructure.database.EmployeeRepository;
import pl.zajavka.infrastructure.database.model.EmployeeEntity;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void checkTransactionalSuccess() {
        employeeRepository.deleteAll();

        EmployeeEntity employee1 = employeeRepository.insertEmployee(EmployeeData.someEmployee1());
        EmployeeEntity employee2 = employeeRepository.insertEmployee(EmployeeData.someEmployee2());
        EmployeeEntity employee3 = employeeRepository.insertEmployee(EmployeeData.someEmployee3());
        System.out.println("###Employee 1: "
                + employeeRepository.getEmployee(employee1.getEmployeeId()));
        System.out.println("###Employee 2: "
                + employeeRepository.getEmployee(employee2.getEmployeeId()));
        employeeRepository.updateEmployee(employee3.getEmployeeId(), new BigDecimal("10348.91"));
        System.out.println("###Employee updated: "
                + employeeRepository.getEmployee(employee3.getEmployeeId()));
        employeeRepository.listEmployees()
                .forEach(employee -> System.out.println("###Employee: " + employee));
        employeeRepository.deleteEmployee(employee2.getEmployeeId());
        employeeRepository.listEmployees()
                .forEach(employee -> System.out.println("###Employee: " + employee));
    }

    @Transactional
    public void checkTransactionalFailure() {
        employeeRepository.deleteAll();
        employeeRepository.insertEmployee(EmployeeData.someEmployee1());
        employeeRepository.insertEmployee(EmployeeData.someEmployee2());
        employeeRepository.insertEmployee(EmployeeData.someEmployee3());
    }
}