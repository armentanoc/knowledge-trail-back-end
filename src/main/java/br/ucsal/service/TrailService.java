package br.ucsal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.videos.Video;
import br.ucsal.dto.trails.TrailMinimal;
import br.ucsal.dto.trails.VideoMinimal;
import br.ucsal.infrastructure.ITrailRepository;
import br.ucsal.service.interfaces.ISkillService;
import br.ucsal.service.interfaces.ITrailService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class TrailService implements ITrailService {

	@Autowired
	private ITrailRepository repository;

	@Autowired
	private ISkillService skillService;

	@Override
	public List<Video> getVideos(Long trailId) {
		Trail trail = getOrThrow(trailId);
		return trail.getVideos();
	}

	@Override
	public Skill getSkill(Long trailId) {
		Trail trail = getOrThrow(trailId);
		return trail.getSkill();
	}

	@Override
	public Page<Trail> findBySkill(Long skillId, Pageable pageable) {
		return repository.findBySkillId(skillId, pageable);
	}

	@Override
	public Page<Trail> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Trail create(TrailMinimal trail) {
		var skillId = trail.skillId().orElse(null);

		Trail trailToCreate = new Trail(trail.title());

		if(trail.videos() != null) {
			addVideosToTrailFromMinimal(trail.videos(), trailToCreate);
		}		

		if (skillId != null) {
			Skill skill = skillService.getOrThrow(skillId);
			trailToCreate.setSkill(skill);
		}

		return repository.save(trailToCreate);
	}

	@Override
	public Trail addVideos(Long trailId, List<VideoMinimal> videos) {
		Trail trail = getOrThrow(trailId);
		return addVideosToTrailFromMinimal(videos, trail);
	}

	@Override
	public Trail replaceVideos(Long trailId, List<VideoMinimal> videos) {
		Trail trail = getOrThrow(trailId);
		trail.getVideos().clear();
		return addVideosToTrailFromMinimal(videos, trail);
	}

	@Override
	public Trail update(Long trailId, TrailMinimal updatedData) {
		Trail trail = getOrThrow(trailId);
		trail.setTitle(updatedData.title());
		var skillId = updatedData.skillId().orElse(null);

		if(updatedData.videos() != null) {
			trail.getVideos().clear();
			addVideosToTrailFromMinimal(updatedData.videos(), trail);
		}	

		if (skillId != null) {
			Skill skill = skillService.getOrThrow(skillId);
			trail.setSkill(skill);
		}

		return repository.save(trail);
	}

	@Override
	public void delete(Long trailId) {
		if (!repository.existsById(trailId)) {
			throw new EntityNotFoundException("Trail not found");
		}
		repository.deleteById(trailId);
	}

	public Trail getOrThrow(Long trailId) {
		return repository.findById(trailId)
				.orElseThrow(() -> new EntityNotFoundException("Trail not found"));
	}

	private Trail addVideosToTrailFromMinimal(List<VideoMinimal> videos, Trail trail) {
		
		for (VideoMinimal video : videos) {
			Video videoToInsert = new Video(video.title(), video.url());
			videoToInsert.setTrail(trail);
			trail.getVideos().add(videoToInsert);
		}

		return repository.save(trail);
	}
}