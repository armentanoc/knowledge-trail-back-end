package br.ucsal.infrastructure;

import br.ucsal.domain.users.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByOrderByIdAsc();
}
