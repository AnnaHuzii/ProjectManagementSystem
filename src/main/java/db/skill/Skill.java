package db.skill;

import lombok.Data;

@Data
public class Skill {
    private long skillId;
    private Industry industry;
    private Level skill_level;

    public enum Industry {
        java,
        c_plus_plus,
        c_sharp,
        js
    }

    public enum Level {
        junior,
        middle,
        senior
    }
}

