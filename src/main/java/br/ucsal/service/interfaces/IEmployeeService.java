package br.ucsal.service.interfaces;

import br.ucsal.domain.users.Employee;

public interface IEmployeeService {
    Employee getOrThrow(Long employeeId);
}
