package br.ucsal.dto.trails;

import java.util.Optional;

public record TrailMinimalUpdate(String title,  Optional<Long> skillId) {
}
