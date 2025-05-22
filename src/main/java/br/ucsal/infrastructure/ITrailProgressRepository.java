package br.ucsal.infrastructure;

import br.ucsal.domain.trails.TrailProgress;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.users.Employee;

@Repository
public interface ITrailProgressRepository extends JpaRepository<TrailProgress, Long> {
    Page<TrailProgress> findAll(Pageable pageable);  
    Optional<TrailProgress> findByEmployeeAndTrail(Employee employee, Trail trail);
}
