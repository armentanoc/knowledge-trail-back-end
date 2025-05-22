package br.ucsal.domain.trails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.ucsal.domain.BaseEntity;
import br.ucsal.domain.users.Employee;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trail_progress")
public class TrailProgress extends BaseEntity {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Trail trail;

    @ElementCollection
    private Set<Long> watchedVideos = new HashSet<>();

    private boolean completed;
    private LocalDate completionDate;

    public TrailProgress() {
        // default for JPA
    }

    public Set<Long> getWatchedVideoIds() {
        return watchedVideos;
    }

    public void setWatchedVideoIds(Set<Long> watchedVideos) {
        this.watchedVideos = watchedVideos;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public void setEmployee(Employee employee) {
        if (employee != null) {
            this.employee = employee;
        }
    }

    public void setTrail(Trail trail) {
        if (trail != null) {
            this.trail = trail;
        }
    }
}
