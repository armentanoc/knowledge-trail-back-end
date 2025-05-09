package br.ucsal.infrastructure;

import br.ucsal.domain.trails.TrailProgress;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrailProgressRepository extends JpaRepository<TrailProgress, Long> {
    Page<TrailProgress> findAll(Pageable pageable);  
}
