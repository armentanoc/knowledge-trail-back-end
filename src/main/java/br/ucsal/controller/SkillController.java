package br.ucsal.controller;

import br.ucsal.domain.skills.Skill;
import br.ucsal.service.interfaces.ISkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skills")
@Tag(name = "Skills", description = "Operations related to skill management.")
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @GetMapping
    @Operation(summary = "Get all skills (paginated)")
    public ResponseEntity<Page<Skill>> getAllSkills(Pageable pageable) {
        Page<Skill> skills = skillService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(skills);
    }

    @PostMapping
    @Operation(summary = "Create a skill")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.create(skill);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSkill);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a skill")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill updatedData) {
        Skill updatedSkill = skillService.update(id, updatedData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSkill);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a skill")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get skill by ID")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillService.findById(id)
                .map(skill -> ResponseEntity.ok(skill))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search")
    @Operation(summary = "Get skill by name")
    public ResponseEntity<Skill> getSkillByName(@RequestParam String name) {
        return skillService.findByName(name)
                .map(skill -> ResponseEntity.ok(skill))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
