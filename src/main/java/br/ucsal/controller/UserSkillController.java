package br.ucsal.controller;

import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.users.Employee;
import br.ucsal.infrastructure.IEmployeeRepository;
import br.ucsal.infrastructure.ISkillRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/{userId}/skills")
@Tag(name = "UserSkills", description = "Operations related to user skill management.")
public class UserSkillController {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ISkillRepository skillRepository;

    @GetMapping
    public ResponseEntity<Set<Skill>> getInterestedSkills(@PathVariable Long userId) {
        Employee employee = employeeRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado ou não é employee: " + userId));
        return ResponseEntity.ok(employee.getInterestedSkills());
    }

    @PutMapping
    public ResponseEntity<Employee> updateInterestedSkills(@PathVariable Long userId, @RequestBody List<Long> skillIds) {
        Employee employee = employeeRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado ou não é employee: " + userId));
        Set<Skill> selectedSkills = new HashSet<>(skillRepository.findAllById(skillIds));
        employee.setInterestedSkills(selectedSkills);
        employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }
}
