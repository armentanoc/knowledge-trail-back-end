package br.ucsal.domain.videos;

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
    private int position;

    @ManyToOne
    private Trail trail;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Trail getTrail() {
        return trail;
    }

    public void setTrail(Trail trail) {
        if (trail != null)
            this.trail = trail;
    }
}
