package br.ucsal.service;

import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.trails.TrailProgress;
import br.ucsal.domain.users.Employee;
import br.ucsal.infrastructure.ITrailProgressRepository;
import br.ucsal.service.interfaces.IEmployeeService;
import br.ucsal.service.interfaces.ISkillService;
import br.ucsal.service.interfaces.ITrailService;
import br.ucsal.service.interfaces.ITrailProgressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import br.ucsal.dto.skills.SkillMinimal;

@Service
public class TrailProgressService implements ITrailProgressService {

    @Autowired
    private ITrailProgressRepository repository;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ITrailService trailService;

    @Autowired
    private ISkillService skillService;

    @Override
    @Transactional
    public boolean watchVideo(Long userId, Long trailId, Long videoId) {
        Employee employee = employeeService.getByUserIdOrThrow(userId); 
        Trail trail = trailService.getOrThrow(trailId);
        TrailProgress progress = createOrUpdateProgress(employee, trail);
        if (!progress.getWatchedVideoIds().contains(videoId)) {
            progress.getWatchedVideoIds().add(videoId);
            checkCompletion(progress, trail);
            repository.save(progress);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean unwatchVideo(Long userId, Long trailId, Long videoId) {
        Employee employee = employeeService.getByUserIdOrThrow(userId); 
        Trail trail = trailService.getOrThrow(trailId);
        TrailProgress progress = repository.findByEmployeeAndTrail(employee, trail)
                .orElseThrow(() -> new EntityNotFoundException("Progress not found"));
        if (progress.getWatchedVideoIds().remove(videoId)) {
            progress.setCompleted(false);
            progress.setCompletionDate(null);
            repository.save(progress);
            return true;
        }
        return false;
    }

    @Override
    public Set<Long> getWatchedVideoIds(Long userId, Long trailId) {
        Employee employee = employeeService.getByUserIdOrThrow(userId); 
        Trail trail = trailService.getOrThrow(trailId);
        TrailProgress progress = repository.findByEmployeeAndTrail(employee, trail).orElse(null);

        if (progress == null) {
            return new HashSet<>();
        }

        return progress.getWatchedVideoIds();
    }

    @Override
    public List<SkillMinimal> getCompletedSkillsByUserId(Long userId) {
        var employee = employeeService.getByUserIdOrThrow(userId); 
        return repository.findCompletedSkillsByEmployeeId(employee.getId());
    }

    @Override
    public List<Employee> getEmployeesBySkillIdWithCompletedTrails(Long skillId) {
        skillService.getOrThrow(skillId);
        return repository.findEmployeesBySkillIdAndCompletedTrue(skillId);
    }

    private TrailProgress createOrUpdateProgress(Employee employee, Trail trail) {
        return repository.findByEmployeeAndTrail(employee, trail)
                .orElseGet(() -> {
                    TrailProgress newProgress = new TrailProgress();
                    newProgress.setEmployee(employee);
                    newProgress.setTrail(trail);
                    return newProgress;
                });
    }

    private void checkCompletion(TrailProgress progress, Trail trail) {
        if (trail.getVideos() != null && progress.getWatchedVideoIds().containsAll(trail.getVideoIds())) {
            progress.setCompleted(true);
            progress.setCompletionDate(LocalDate.now());
        }
    }
}
