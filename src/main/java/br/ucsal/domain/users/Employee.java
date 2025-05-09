package br.ucsal.domain.users;

import br.ucsal.domain.BaseEntity;
import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.trails.TrailProgress;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  

    @ManyToMany
    @JoinTable(name = "user_skill_interests")
    private Set<Skill> interestedSkills = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TrailProgress> trailProgresses = new ArrayList<>();

    public Employee() {
		// default for JPA
	}

    public Employee(User user) {
		this.user = user;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user != null) {
            this.user = user;
        }
    }

    public Set<Skill> getInterestedSkills() {
        return interestedSkills;
    }

    public void setInterestedSkills(Set<Skill> interestedSkills) {
        if(interestedSkills != null)
            this.interestedSkills = interestedSkills;
    }

    public List<TrailProgress> getTrailProgresses() {
        return trailProgresses;
    }

    public void setTrailProgresses(List<TrailProgress> trailProgresses) {
        this.trailProgresses = trailProgresses;
    }
}
