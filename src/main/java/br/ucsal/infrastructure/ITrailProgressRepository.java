package br.ucsal.infrastructure;

import br.ucsal.domain.trails.TrailProgress;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.users.Employee;
import br.ucsal.dto.skills.SkillMinimal;

@Repository
public interface ITrailProgressRepository extends JpaRepository<TrailProgress, Long> {
    Page<TrailProgress> findAll(Pageable pageable);  
    Optional<TrailProgress> findByEmployeeAndTrail(Employee employee, Trail trail);
    
    @Query("SELECT DISTINCT new br.ucsal.dto.skills.SkillMinimal(s.id, s.name) " +
            "FROM TrailProgress tp " +
            "JOIN tp.trail t " +
            "JOIN t.skill s " +
            "WHERE tp.completed = true AND tp.employee.id = :employeeId")
    List<SkillMinimal> findCompletedSkillsByEmployeeId(Long employeeId);

    @Query("SELECT DISTINCT tp.employee " +
            "FROM TrailProgress tp " +
            "JOIN tp.trail t " +
            "WHERE t.skill.id = :skillId AND tp.completed = true")
    List<Employee> findEmployeesBySkillIdAndCompletedTrue(Long skillId);
}
