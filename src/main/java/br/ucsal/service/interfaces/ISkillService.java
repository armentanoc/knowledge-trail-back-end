package br.ucsal.service.interfaces;

import br.ucsal.domain.skills.Skill;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISkillService {
    Page<Skill> findAll(Pageable pageable);
    Skill create(Skill skill);
    Skill update(Long skillId, Skill updatedData);
    void delete(Long skillId);
    Optional<Skill> findById(Long id);      
    Optional<Skill> findByName(String name);
    Skill getOrThrow(Long skillId);
}
