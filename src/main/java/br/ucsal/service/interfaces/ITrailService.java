package br.ucsal.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.videos.Video;
import br.ucsal.dto.trails.TrailMinimal;
import br.ucsal.dto.trails.VideoMinimal;

public interface ITrailService {
    List<Video> getVideos(Long trailId);
    Skill getSkill(Long trailId);
    Page<Trail> findBySkill(Long skillId, Pageable pageable);
    Page<Trail> findAll(Pageable pageable);

    Trail create(TrailMinimal trail);
    Trail update(Long trailId, TrailMinimal updatedData);

    Trail addVideos(Long trailId, List<VideoMinimal> videos);
    Trail replaceVideos(Long trailId, List<VideoMinimal> videos);

    void delete(Long trailId);
    Trail getOrThrow(Long trailId);
}
