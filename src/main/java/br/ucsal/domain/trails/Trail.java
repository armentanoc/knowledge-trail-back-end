package br.ucsal.domain.trails;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.domain.BaseEntity;
import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.videos.Video;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "trails")
public class Trail extends BaseEntity {    
    
    private String title;
    
    @ManyToOne
    private Skill skill;
    
    @OneToMany(mappedBy = "trail", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    protected Trail() {
		// default for JPA
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null)
            this.title = title;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        if(skill != null)
            this.skill = skill;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public boolean setVideos(List<Video> videos) {
        if(videos != null)  {
            this.videos = videos;
            return true;
        }
        return false;
    }   
}