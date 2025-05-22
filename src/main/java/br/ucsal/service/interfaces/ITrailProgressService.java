package br.ucsal.service.interfaces;

import java.util.Set;

public interface ITrailProgressService {
    boolean watchVideo(Long employeeId, Long trailId, Long videoId);
    boolean unwatchVideo(Long employeeId, Long trailId, Long videoId);
    Set<Long> getWatchedVideoIds(Long employeeId, Long trailId);
}
