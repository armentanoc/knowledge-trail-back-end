package br.ucsal.service.interfaces;

import br.ucsal.domain.users.Employee;

import java.util.List;
import java.util.Optional;
import br.ucsal.dto.users.DeleteResponse;

public interface IEmployeeService {

    Employee add(Employee client);

    Optional<Employee> get(Long clientId);

    List<Employee> getAll();

    DeleteResponse delete(Long clientId);
}
