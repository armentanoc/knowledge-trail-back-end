package br.ucsal.service;

import br.ucsal.domain.skills.Skill;
import br.ucsal.infrastructure.ISkillRepository;
import br.ucsal.service.interfaces.ISkillService;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SkillService implements ISkillService {

	@Autowired
	private ISkillRepository repository;

	@Override
	public Page<Skill> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Skill create(Skill skill) {
		return repository.save(skill);
	}

	@Override
	public Skill update(Long skillId, Skill updatedData) {
		Skill skill = getOrThrow(skillId);
		skill.setName(updatedData.getName());
		skill.setDescription(updatedData.getDescription());
		return repository.save(skill);
	}

	@Override
	public void delete(Long skillId) {
		if (!repository.existsById(skillId)) {
			throw new EntityNotFoundException("Skill not found");
		}
		repository.deleteById(skillId);
	}

	@Override
	public Optional<Skill> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Skill> findByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

	@Override
	public Skill getOrThrow(Long skillId) {
		return repository.findById(skillId)
				.orElseThrow(() -> new EntityNotFoundException("Trail not found"));
	}
}
