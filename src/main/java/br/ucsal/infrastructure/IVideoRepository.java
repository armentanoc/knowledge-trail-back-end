package br.ucsal.infrastructure;

import br.ucsal.domain.videos.Video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAll(Pageable pageable);  
}
