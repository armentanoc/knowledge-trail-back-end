package br.ucsal.dto.trails;

import java.util.List;
import java.util.Optional;

public record TrailMinimal(String title, List<VideoMinimal> videos, Optional<Long> skillId) {
}
