package br.ucsal.controller;

import br.ucsal.service.interfaces.ITrailProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.ucsal.dto.skills.SkillMinimal;
import br.ucsal.domain.users.Employee;

import java.util.Set;
import java.util.List;

@RestController
@RequestMapping("trail-progress")
@Tag(name = "Trail Progress", description = "Operations related to employee video progress in trails.")
public class TrailProgressController {

    @Autowired
    private ITrailProgressService trailProgressService;

    @PostMapping("/watch")
    @Operation(summary = "Mark a video as watched for a specific trail and employee.")
    public ResponseEntity<String> watchVideo(
            @RequestParam Long employeeId,
            @RequestParam Long trailId,
            @RequestParam Long videoId) {

        boolean success = trailProgressService.watchVideo(employeeId, trailId, videoId);
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.toString(success));
    }

    @PostMapping("/unwatch")
    @Operation(summary = "Mark a video as unwatched for a specific trail and employee.")
    public ResponseEntity<String> unwatchVideo(
            @RequestParam Long employeeId,
            @RequestParam Long trailId,
            @RequestParam Long videoId) {

        boolean success = trailProgressService.unwatchVideo(employeeId, trailId, videoId);
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.toString(success));
    }

    @GetMapping
    @Operation(summary = "Retrieve trail progress for an employee.")
    public ResponseEntity<Set<Long>> getProgress(
            @RequestParam Long employeeId,
            @RequestParam Long trailId) {

        Set<Long> watchedVideoIds = trailProgressService.getWatchedVideoIds(employeeId, trailId);
        return ResponseEntity.ok(watchedVideoIds);
    }

    @GetMapping("/skils-by-employee")
    @Operation(summary = "Get all skills validated by an employee (i.e., where trail progress is completed).")
    public ResponseEntity<List<SkillMinimal>> getCompletedSkillsByEmployee(
            @RequestParam Long employeeId) {

        List<SkillMinimal> skills = trailProgressService.getCompletedSkillsByEmployeeId(employeeId);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/employees-by-skill")
    @Operation(summary = "Get all employees who have validated a skill through trail completion.")
    public ResponseEntity<List<Employee>> getEmployeesBySkill(
            @RequestParam Long skillId) {

        List<Employee> employees = trailProgressService.getEmployeesBySkillIdWithCompletedTrails(skillId);
        return ResponseEntity.ok(employees);
    }

}
