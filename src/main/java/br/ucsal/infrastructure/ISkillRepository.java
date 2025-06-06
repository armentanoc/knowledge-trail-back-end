package br.ucsal.infrastructure;

import br.ucsal.domain.skills.Skill;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkillRepository extends JpaRepository<Skill, Long> {
    Page<Skill> findAll(Pageable pageable);  
    Optional<Skill> findByNameIgnoreCase(String name);
}
