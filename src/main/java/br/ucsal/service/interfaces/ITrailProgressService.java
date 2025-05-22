package br.ucsal.service.interfaces;

import java.util.List;
import java.util.Set;

import br.ucsal.domain.users.Employee;
import br.ucsal.dto.skills.SkillMinimal;

public interface ITrailProgressService {
    boolean watchVideo(Long employeeId, Long trailId, Long videoId);
    boolean unwatchVideo(Long employeeId, Long trailId, Long videoId);
    Set<Long> getWatchedVideoIds(Long employeeId, Long trailId);
    List<SkillMinimal> getCompletedSkillsByEmployeeId(Long employeeId);
    List<Employee> getEmployeesBySkillIdWithCompletedTrails(Long skillId);
}
