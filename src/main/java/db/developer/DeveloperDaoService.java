package db.developer;

import db.company.CompanyDaoService;
import db.project.ProjectDaoService;
import db.skill.Industry;
import db.skill.Level;
import storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoService {
    public static List<Developer> developers = new ArrayList<>();

    private final PreparedStatement getInfoByFullName;
    private final PreparedStatement getSkillsByFullName;
    private final PreparedStatement getProjectsByFullName;
    private final PreparedStatement getQuantityJavaDevelopers;
    private final PreparedStatement getListMiddleDevelopers;
    private final PreparedStatement selectMaxId;
    private final PreparedStatement addDeveloper;
    private final PreparedStatement addProjectDeveloper;
    private final PreparedStatement getIdSkillByIndustryAndSkillLevel;
    private final PreparedStatement addDeveloperSkill;
    private final PreparedStatement existsByIdSt;
    private final PreparedStatement getIdByFullName;
    private final PreparedStatement deleteDeveloperFromDevelopersById;

    public DeveloperDaoService(Connection connection) throws SQLException {
        PreparedStatement getAllInfoSt = connection.prepareStatement(
                "SELECT * FROM developers"
        );
        try (ResultSet resultSet = getAllInfoSt.executeQuery()) {

            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setDeveloperId(resultSet.getLong("id"));
                developer.setFullName(resultSet.getString("full_name"));
                developer.setBirthDate(resultSet.getDate("birth_date"));
                String sex = resultSet.getString("sex");
                Sex sexName = null;
                if (sex.equals(Sex.MALE.getSexName())){
                    sexName = Sex.MALE;
                }else if (sex.equals(Sex.FEMALE.getSexName())){
                    sexName = Sex.FEMALE;
                }else if (sex.equals(Sex.UNKNOWN.getSexName())){
                    sexName = Sex.UNKNOWN;
                }
                developer.setSex(sexName);
                developer.setEmail(resultSet.getString("email"));
                developer.setSkype(resultSet.getString("skype"));
                developer.setSalary(resultSet.getFloat("salary"));
                developers.add(developer);
            }
        }

        getInfoByFullName = connection.prepareStatement(
                "SELECT *" +
                        "FROM developers " +
                        "WHERE full_name  LIKE ?"
        );

        getSkillsByFullName = connection.prepareStatement(
                "SELECT industry, skill_level " +
                        "FROM developers " +
                        "JOIN developers_skills ON developers.id = developers_skills.developer_id " +
                        "JOIN skills ON developers_skills.skill_id = skills.id " +
                        "WHERE full_name LIKE ?"
        );

        getProjectsByFullName = connection.prepareStatement(
                "SELECT  name " +
                        "FROM developers " +
                        "JOIN projects_developers ON developers.id = projects_developers.developer_id " +
                        "JOIN project ON projects_developers.project_id = project.id " +
                        "WHERE full_name  LIKE ?"
        );

        getQuantityJavaDevelopers = connection.prepareStatement(
                "SELECT COUNT(industry) AS quantityIndustryDevelopers " +
                        "FROM developers " +
                        "JOIN developers_skills ON developers.id = developers_skills.developer_id " +
                        "JOIN skills ON developers_skills.skill_id = skills.id " +
                        "WHERE industry = 'Java'"
        );

        getListMiddleDevelopers = connection.prepareStatement(
                "SELECT full_name, industry " +
                        "FROM developers " +
                        "JOIN developers_skills ON developers.id = developers_skills.developer_id " +
                        "JOIN skills ON developers_skills.skill_id = skills.id " +
                        "WHERE skill_level = 'middle'"
        );

        selectMaxId = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM developers"
        );

        addDeveloper = connection.prepareStatement(
                "INSERT INTO developers  VALUES ( ?, ?, ?, ?, ?, ?, ?)");

        addProjectDeveloper = connection.prepareStatement(
                "INSERT INTO projects_developers  VALUES ( ?, ?)");

        getIdSkillByIndustryAndSkillLevel = connection.prepareStatement(
                "SELECT id FROM skills WHERE industry LIKE ? AND skill_level LIKE ?");

        addDeveloperSkill = connection.prepareStatement(
                "INSERT INTO developers_skills  VALUES ( ?, ?)");

        existsByIdSt = connection.prepareStatement(
                "SELECT count(*) > 0 AS developerExists FROM developers WHERE id = ?"
        );

        getIdByFullName = connection.prepareStatement(
                "SELECT id FROM developers WHERE full_name LIKE ? AND birth_date LIKE ?"
        );

        deleteDeveloperFromDevelopersById = connection.prepareStatement(
                "DELETE FROM developers WHERE id = ?"
        );

    }


    public void getAllFullName() {
        System.out.println("??.??.?? ???????? ??????????????????????");
        for (Developer developer : developers) {
            System.out.println("\t" + developer.getDeveloperId() + ". " + developer.getFullName());
        }
    }

    public void getInfoByFullName(String fullName) throws SQLException {
        getInfoByFullName.setString(1, "%" + fullName + "%");
        try (ResultSet resultSet = getInfoByFullName.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("??.??.??. -  " + resultSet.getString("full_name") + ";  ");
                System.out.println("\t???????? ???????????????????? -  " + resultSet.getDate("birth_date") + ";  ");
                System.out.println("\t?????????? -  " + resultSet.getString("sex") + ";  ");
                System.out.println("\temail -  " + resultSet.getString("email") + ";  ");
                System.out.println("\tskype -  " + resultSet.getString("skype") + ";  ");
                System.out.println("\t???????????????? -  " + resultSet.getInt("salary"));
            }
        }
    }

    public void getSkillsByFullName(String fullName) throws SQLException {
        getSkillsByFullName.setString(1, "%" + fullName + "%");
        System.out.println("\t?????????????? ??????????: ");
        try (ResultSet resultSet = getSkillsByFullName.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("\t\t" + resultSet.getString("industry") + " -  " + resultSet.getString("skill_level") + ";  ");
            }
        }
    }

    public void getProjectsByFullName(String fullName) throws SQLException {
        getProjectsByFullName.setString(1, "%" + fullName + "%");
        System.out.println("\t?????????????? ???????????? ?? ????????????????: ");
        try (ResultSet rs = getProjectsByFullName.executeQuery()) {
            while (rs.next()) {
                System.out.println("\t\t" + rs.getString("name") + ";  ");
            }
        }
    }

    public void getQuantityJavaDevelopers() throws SQLException {
        int count;
        try (ResultSet resultSet = getQuantityJavaDevelopers.executeQuery()) {
            resultSet.next();
            count = resultSet.getInt("quantityIndustryDevelopers");
        }
        System.out.println("?? ???????? ?????????????????? ????????????????  " + count + " Java-????????????????????");
    }

    public void getListMiddleDevelopers() throws SQLException {
        System.out.println("???????????? ???????? ?????????????????????? ?? ???????????? ?????????? middle: ");
        try (ResultSet rs = getListMiddleDevelopers.executeQuery()) {
            while (rs.next()) {
                System.out.print(rs.getString("full_name"));
                System.out.println(",  ???????? - " + rs.getString("industry"));
            }
        }
    }

    public long getIdByFullName(String fullName, Date birthDate) throws SQLException {
        long id;
        getIdByFullName.setString(1, "%" + fullName + "%");
        getIdByFullName.setDate(2, Date.valueOf(String.valueOf(birthDate)));
        try (ResultSet rs = getIdByFullName.executeQuery()) {
            rs.next();
            id = rs.getInt("id");
        }
        return id;
    }

    public int addDeveloper(String fullName, Date birthDate, Sex sex, String email, String skype) throws SQLException {

        long newDeveloperId;
        try (ResultSet rs = selectMaxId.executeQuery()) {
            rs.next();
            newDeveloperId = rs.getLong("maxId");
        }
        newDeveloperId++;
        addDeveloper.setLong(1, newDeveloperId);
        addDeveloper.setString(2, fullName);
        addDeveloper.setDate(3, birthDate);
        addDeveloper.setString(4, String.valueOf(sex));
        addDeveloper.setString(5, email);
        addDeveloper.setString(6, skype);

        String company = "RASK In-AGRO";
        System.out.println("\t?????????? ????????????????, ?? ???????? ?????? ???????? ?????????????????? : " + company);

        System.out.println("\t???? ???????????????? ?????????????????? ???????????????? ??????????????:");
        ArrayList<String> companyProjects = new CompanyDaoService(Storage.getInstance().getConnection()).getCompanyProjects(company);
        for (String project : companyProjects) {
            System.out.println("\t\t" + project);
        }

        String project = "KUP Agro";
        System.out.println("\t?????????? ?????????????????? ???????? ?????????????????? ??????????????: " + project);
        long projectId = new ProjectDaoService(Storage.getInstance().getConnection()).getIdProjectByName(project);
        addProjectDeveloper.setLong(1, projectId);
        addProjectDeveloper.setLong(2, newDeveloperId);

        int salary = 5690;
        System.out.println("\t?????????????????? ?????????? ???????????????????? : " + salary);

        addDeveloper.setInt(7, salary);
        Developer developer = new Developer();
        developer.setDeveloperId(newDeveloperId);
        developer.setFullName(fullName);
        developer.setBirthDate(birthDate);
        developer.setSex(sex);
        developer.setEmail(email);
        developer.setSkype(skype);
        developer.setSalary(salary);
        developers.add(developer);

        Industry industry = Industry.C_PLUS_PLUS;
        String industryName = industry.getIndustryName();
        System.out.println("\t?????????????? ?????????? ?????????????????????????? (Java, C++, C#, JS) : " + industryName);

        Level level = Level.SENIOR;
        String lenelName = level.getLevelName();
        System.out.println("\t???????????? ?????????? ???????? ?????????????????????????? (junior, middle, senior) : " + lenelName);

        getIdSkillByIndustryAndSkillLevel.setString(1, "%" + industryName + "%");
        getIdSkillByIndustryAndSkillLevel.setString(2, "%" + lenelName + "%");
        long skillId;
        try (ResultSet rs = getIdSkillByIndustryAndSkillLevel.executeQuery()) {
            rs.next();
            skillId = rs.getLong("id");
        }

        addDeveloperSkill.setLong(1, newDeveloperId);
        addDeveloperSkill.setLong(2, skillId);

        addDeveloper.executeUpdate();
        addProjectDeveloper.executeUpdate();
        addDeveloperSkill.executeUpdate();
        if (existsDeveloper(newDeveloperId)) {
            System.out.println("?????????????????? ?????????????? ????????????????????????");
        } else System.out.println("?????????????? ??????????????. ?????????????????? ???? ?????????????????????? ?? ???????? ??????????");

        return +1;
    }

    public boolean existsDeveloper(long id) throws SQLException {
        existsByIdSt.setLong(1, id);
        try (ResultSet rs = existsByIdSt.executeQuery()) {
            rs.next();
            return rs.getBoolean("developerExists");
        }
    }

    public void deleteDeveloper(String fullName, Date birthDate) throws SQLException {
        long idToDelete = getIdByFullName(fullName, birthDate);

        deleteDeveloperFromDevelopersById.setLong(1, idToDelete);
        deleteDeveloperFromDevelopersById.executeUpdate();

        developers.removeIf(nextDeveloper -> nextDeveloper.getDeveloperId() == idToDelete);
    }

    public int editDeveloper(String fullName, Date birthDate, Sex sex, String email, String skype) throws SQLException {

        long newDeveloperId;
        try (ResultSet rs = selectMaxId.executeQuery()) {
            rs.next();
            newDeveloperId = rs.getLong("maxId");
        }
        newDeveloperId++;
        addDeveloper.setLong(1, newDeveloperId);
        addDeveloper.setString(2, fullName);
        addDeveloper.setDate(3, birthDate);
        addDeveloper.setString(4, String.valueOf(sex));
        addDeveloper.setString(5, email);
        addDeveloper.setString(6, skype);

        String company = "RASK In-AGRO";
        System.out.println("\t?????????? ????????????????, ?? ???????? ?????? ???????? ?????????????????? : " + company);

        System.out.println("\t???? ???????????????? ?????????????????? ???????????????? ??????????????:");
        ArrayList<String> companyProjects = new CompanyDaoService(Storage.getInstance().getConnection()).getCompanyProjects(company);
        for (String project : companyProjects) {
            System.out.println("\t\t" + project);
        }

        String project = "Kazakhstan BI-Group";
        System.out.println("\t?????????? ?????????????????? ???????? ?????????????????? ??????????????: " + project);
        long projectId = new ProjectDaoService(Storage.getInstance().getConnection()).getIdProjectByName(project);
        addProjectDeveloper.setLong(1, projectId);
        addProjectDeveloper.setLong(2, newDeveloperId);

        int salary = 2500;
        System.out.println("\t???????? ?????????????????? ?????????? ???????????????????? : " + salary);

        addDeveloper.setInt(7, salary);
        Developer developer = new Developer();
        developer.setDeveloperId(newDeveloperId);
        developer.setFullName(fullName);
        developer.setBirthDate(birthDate);
        developer.setSex(sex);
        developer.setEmail(email);
        developer.setSkype(skype);
        developer.setSalary(salary);
        developers.add(developer);


        Industry industry = Industry.JAVA;
        String industryName = industry.getIndustryName();
        System.out.println("\t?????????????? ?????????? ?????????????????????????? (Java, C++, C#, JS) : " + industryName);


        Level level = Level.SENIOR;
        String lenelName = level.getLevelName();
        System.out.println("\t???????????? ?????????? ???????? ?????????????????????????? ??????????????(junior, middle, senior) : " + lenelName);

        getIdSkillByIndustryAndSkillLevel.setString(1, "%" + industryName + "%");
        getIdSkillByIndustryAndSkillLevel.setString(2, "%" + lenelName + "%");
        long skillId;
        try (ResultSet rs = getIdSkillByIndustryAndSkillLevel.executeQuery()) {
            rs.next();
            skillId = rs.getLong("id");
        }

        addDeveloperSkill.setLong(1, newDeveloperId);
        addDeveloperSkill.setLong(2, skillId);

        addDeveloper.executeUpdate();
        addProjectDeveloper.executeUpdate();
        addDeveloperSkill.executeUpdate();
        if (existsDeveloper(newDeveloperId)) {
            System.out.println("?????????????????? ?????????????? ????????????????????????");
        } else System.out.println("?????????????? ??????????????. ?????????????????? ???? ?????????????????????? ?? ???????? ??????????");

        return +1;
    }

    public void editDeveloper(long id) throws SQLException {
        deleteDeveloperFromDevelopersById.setLong(1, id);
        deleteDeveloperFromDevelopersById.executeUpdate();

        developers.removeIf(nextDeveloper -> nextDeveloper.getDeveloperId() == id);
    }

}
