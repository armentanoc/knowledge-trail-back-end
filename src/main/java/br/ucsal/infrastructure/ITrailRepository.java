package br.ucsal.infrastructure;

import br.ucsal.domain.trails.Trail;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrailRepository extends JpaRepository<Trail, Long> {
    Page<Trail> findAll(Pageable pageable);  
    Page<Trail> findBySkillId(Long skillId, Pageable pageable);
    Optional<Trail> findByTitleContainingIgnoreCase(String titlePart);
}
