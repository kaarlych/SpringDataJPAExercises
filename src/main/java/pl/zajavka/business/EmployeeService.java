package pl.zajavka.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zajavka.infrastructure.database.jparepositories.EmployeeDataJpaRepository;
import pl.zajavka.infrastructure.database.model.EmployeeEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeDataJpaRepository employeeDataJpaRepository;

    @Transactional
    public EmployeeEntity create(EmployeeEntity employee) {
        return employeeDataJpaRepository.save(employee);
    }

    public EmployeeEntity find(final Integer employeeId) {
        return employeeDataJpaRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Employee with id: [%s] doesn't exist", employeeId)));
    }

    public EmployeeEntity find(final String name, final String surname) {
        return employeeDataJpaRepository.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Employee with name: [%s], surname: [%s] doesn't exist", name, surname)));
    }

    public List<EmployeeEntity> findAll() {
        return employeeDataJpaRepository.findAll();
    }

    @Transactional
    public void delete(final String name, final String surname) {
        employeeDataJpaRepository.deleteByNameAndSurname(name, surname);
    }

    @Transactional
    public void deleteAll() {
        employeeDataJpaRepository.deleteAll();

    }
}
