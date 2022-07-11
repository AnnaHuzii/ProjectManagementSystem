package db.skill;

public enum Industry {
    JAVA("java"),
    C_PLUS_PLUS("C++"),
    C_SHARP("C#"),
    JS("JS");

    private final String industryName;

    Industry(String industryName)
        {
            this.industryName = industryName;
        }

    public String getIndustryName (){
        return industryName;
    }
}