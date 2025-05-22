package br.ucsal.service.interfaces;

import java.util.List;
import java.util.Set;

import br.ucsal.domain.users.Employee;
import br.ucsal.dto.skills.SkillMinimal;

public interface ITrailProgressService {
    boolean watchVideo(Long userId, Long trailId, Long videoId);
    boolean unwatchVideo(Long userId, Long trailId, Long videoId);
    Set<Long> getWatchedVideoIds(Long userId, Long trailId);
    List<SkillMinimal> getCompletedSkillsByUserId(Long employeeId);
    List<Employee> getEmployeesBySkillIdWithCompletedTrails(Long skillId);
}
