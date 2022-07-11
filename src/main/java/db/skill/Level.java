package db.skill;

public enum Level {
    JUNIOR("junior"),
    MIDDLE("middle"),
    SENIOR("senior");

    private final String levelName;

    Level(String levelName)
    {
        this.levelName = levelName;
    }

    public String getLevelName (){
        return levelName;
    }
}
