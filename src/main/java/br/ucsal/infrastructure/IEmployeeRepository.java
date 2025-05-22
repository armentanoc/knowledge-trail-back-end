package br.ucsal.infrastructure;

import br.ucsal.domain.users.Employee;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAll(Pageable pageable);  
    Optional<Employee> findByUserId(Long userId);
    Optional<Employee> findByUserUsername(String username);
}
