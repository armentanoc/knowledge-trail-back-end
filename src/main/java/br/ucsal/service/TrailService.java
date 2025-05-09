package br.ucsal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.videos.Video;
import br.ucsal.infrastructure.ITrailRepository;
import br.ucsal.service.interfaces.ITrailService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TrailService implements ITrailService {

	@Autowired
	private ITrailRepository repository;

	@Override
	public List<Video> getVideosByTrailId(Long trailId) {
		Trail trail = repository.findById(trailId)
				.orElseThrow(() -> new EntityNotFoundException("Trail not found"));
		return trail.getVideos();
	}

	@Override
	public Skill getSkillByTrailId(Long trailId) {
		Trail trail = repository.findById(trailId)
									.orElseThrow(() -> new EntityNotFoundException("Trail not found"));
		return trail.getSkill(); 
	}

	@Override
    public List<Trail> findBySkillId(Long skillId) {
        return repository.findBySkillId(skillId);  
    }
}
