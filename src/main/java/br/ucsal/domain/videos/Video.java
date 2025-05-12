package br.ucsal.domain.videos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.ucsal.domain.BaseEntity;
import br.ucsal.domain.trails.Trail;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video extends BaseEntity {
    private String title;
    private String url;

    @ManyToOne
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Trail trail;

    public Video(String title, String url) {
        this.title = title;
        this.url = url;
    }

    protected Video() {
		// default for JPA
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null)
            this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url != null)
            this.url = url;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        if (trail != null)
            this.trail = trail;
    }
}
