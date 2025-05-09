package br.ucsal.service.interfaces;

import java.util.List;

import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.videos.Video;

public interface ITrailService {
    List<Video> getVideosByTrailId(Long trailId);
    Skill getSkillByTrailId(Long trailId);
    List<Trail> findBySkillId(Long skillId);
}
