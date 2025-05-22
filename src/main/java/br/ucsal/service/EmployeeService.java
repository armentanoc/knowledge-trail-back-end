package br.ucsal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ucsal.infrastructure.IEmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import br.ucsal.domain.users.Employee;
import br.ucsal.service.interfaces.IEmployeeService;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository repository;

	@Override
	public Employee getOrThrow(Long employeeId) {
		return repository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found"));
	}

	@Override
	public Employee getByUserIdOrThrow(Long userId) {
		return repository.findByUserId(userId)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found"));
	}

}