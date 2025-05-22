package br.ucsal.dto.skills;

public class SkillMinimal {
    private Long id;
    private String name;

    public SkillMinimal(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
