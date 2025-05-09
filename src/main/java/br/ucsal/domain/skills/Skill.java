package br.ucsal.domain.skills;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.domain.BaseEntity;
import br.ucsal.domain.trails.Trail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<Trail> trails = new ArrayList<>();

    protected Skill() {
		// default for JPA
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null)
            this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null)
            this.description = description;
    }

    public List<Trail> getTrails() {
        return trails;
    }

    public void setTrails(List<Trail> trails) {
        if (trails != null)
            this.trails = trails;
    }
}
