/*
    Необхідно створити консольний додаток на основі БД із домашнього завдання модуля 3, яке:

    з'єднується з БД;

    дозволяє виконувати операції CRUD (CREATE, READ, UPDATE, DELETE);

    вивести на консоль:

        зарплату(сумму) всіх розробників окремого проекту;

        список розробників окремого проекту;

        список всіх Java-розробників;

        список всіх розробників з рівнем middle;

        список проектів у наступному форматі: дата створення - назва проекту - кількість розробників на цьому проекті.

    Також: створити заготовки операцій(закомментований запит) для створення нових проектів, розробників, клієнтів.

    Не забывать про правильні зв'язки між таблицями. Результатом виконання має бути створений на віддаленному репозиторій
    на GitHub під назвою ProjectManagementSystem.
 */

import db.company.CompanyDaoService;
import db.customer.CustomerDaoService;
import db.developer.DeveloperDaoService;
import db.project.ProjectDaoService;
import storage.Storage;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        Storage storage = Storage.getInstance();

        DeveloperDaoService developerDaoService = new DeveloperDaoService(storage.getConnection());

        CompanyDaoService companyDaoService = new CompanyDaoService(storage.getConnection());

        CustomerDaoService customerDao = new CustomerDaoService(storage.getConnection());

        ProjectDaoService projectDao = new ProjectDaoService(storage.getConnection());

//        System.out.println("Дані по таблиці Developers");
//        System.out.println("-------------------------");
//
//        //  Вивести всіх рохробників по ПІП
//        developerDaoService.getAllFullName();
//        System.out.println("-------------------------");
//
//        // По ПІП розробника отримати всі його дані (Huzii Anna)
//        String fullNameInput2 = "Huzii Anna";
//        developerDaoService.getInfoByFullName(fullNameInput2);
//        developerDaoService.getSkillsByFullName(fullNameInput2);
//        developerDaoService.getProjectsByFullName(fullNameInput2);
//        System.out.println("-------------------------");
//
//        // Дізнатися скільки всього Java - розробників
//        developerDaoService.getQuantityJavaDevelopers();
//        System.out.println("-------------------------");
//
//        // Дізнатися скільки всех middle - розробників
//        developerDaoService.getListMiddleDevelopers();
//        System.out.println("-------------------------");
//
//        // Добавити розробника Petrov Petro
//        System.out.println("Для створення нового розробника будуть веденні наступні дані:");
//        String fullNameInput5 = "Petrov Petro";
//        System.out.println("\tПрізвище і ім'я: " + fullNameInput5);
//        Date birthDate5 = Date.valueOf(LocalDate.parse("1984-10-25"));
//        System.out.println("\tДата народження: " + birthDate5);
//        Sex sex5 = Sex.MALE;
//        String sexName5 = sex5.getSexName();
//        System.out.println("\tСтать (\"male, female, unknown\"): " + sexName5);
//        String email5 = "petrov_petro@ukr.net";
//        System.out.println("\temail: " + email5);
//        String skype5 = "petrov_petro";
//        System.out.println("\tskype : " + skype5);
//
//        developerDaoService.addDeveloper(fullNameInput5, birthDate5,
//                sex5, email5, skype5);
//        System.out.println("-------------------------");
//
//         // Змінити дані розробника Onishchenko Elena
//        System.out.println("Для внесення змін потрібно оновити поля");
//        String fullNameInput6 = "Onishchenko Elena";
//        Date birthDateInput6 = Date.valueOf(LocalDate.parse("1990-01-02"));
//        System.out.println("Дані будуть оновлені по розробнику " + fullNameInput6 + ", Дата народження: " + birthDateInput6);
//        Sex sex6 = Sex.FEMALE;
//        String sexName6 = sex6.getSexName();
//        System.out.println("\tСтать (\"male, female, unknown\"): " + sexName6);
//        String email6 = "onishchenko.l@ukr.net";
//        System.out.println("\tНовий email: " + email6);
//        String skype6 = "lena.stadnik";
//        System.out.println("\tНовий skype: " + skype6);
//
//        long idToDelete = 0;
//        try {
//           idToDelete = developerDaoService.getIdByFullName(fullNameInput6, birthDateInput6);
//        }  catch (SQLException e) {
//        System.out.println("В базе даних такого розробника не існує. Ведіть корректно дані.");
//
//        }
//        developerDaoService.editDeveloper(idToDelete);
//        developerDaoService.editDeveloper(fullNameInput6, birthDateInput6, sex6, email6, skype6);
//        System.out.println("-------------------------");
//
//        // Видалити розробника
//        String fullNameInput7 = "Demchenko Bogdan";
//        System.out.print("\tПрізвище і ім'я розробника, що видаляємо: " + fullNameInput7);
//        Date birthDateInput7 = Date.valueOf(LocalDate.parse("1992-11-13"));
//        System.out.println(", Дата народження: " + birthDateInput7);
//        developerDaoService.deleteDeveloper(fullNameInput7, birthDateInput7);
//        System.out.println("Розробник успішно видалений з бази даних.");
//        System.out.println("-------------------------");

//        System.out.println("Дані по таблиці Projects");
//        System.out.println("-------------------------");
//
//        // Вивести всі назви проектів
//        projectDao.getAllNames();
//        System.out.println("-------------------------");
//
//        // Отримати розшитені дані по назві проекту Kazakhstan BI-Group
//        String projectNameInput2 = "Kazakhstan BI-Group";
//        System.out.println("Назва проекту по якому виводяться дані: " + projectNameInput2);
//        projectDao.getInfoByName(projectNameInput2);
//        System.out.println("-------------------------");
//
//        // Отримати список всіх розробників конкретного проекту KUP Agro
//        String projectNameInput3 = "KUP Agro";
//        System.out.println("Назва проекту, по якому потрібно вивести розробників: " + projectNameInput3);
//        projectDao.getListDevelopers(projectNameInput3);
//        System.out.println("-------------------------");
//
//       // Суму зарплат всіх розробників проекту Quarter
//
//       String projectNameInput4 = "Quarter";
//       System.out.println("Назва проекту, по якому потрібно вивести суму зарплат всіх розробників: " + projectNameInput4);
//       projectDao.getBudgetByProjectName(projectNameInput4);
//       System.out.println("-------------------------");
//
//      // Список проектів в наступному форматі:
//      //Дата створення проекту - Назва проекту - Кількість розробників на цьому проекті
//       System.out.println("Список проектів (Дата створення проекту - Назва проекту - Кількість розробників на цьому проекті): ");
//       projectDao.getProjectsListInSpecialFormat();
//       System.out.println("-------------------------");

//        System.out.println("Дані по таблиці Companies");
//        System.out.println("-------------------------");
//
//        // Вивести всі компанії і їх опис");
//        companyDaoService.getAllNames();
//        System.out.println("-------------------------");
//
//       // Добавити компанію BI-DON. Опис "Development of computer games and mobile applications"
//       companyDaoService.addCompany();
//       System.out.println("-------------------------");
//
//
//       System.out.println("Дані по таблиці Customers");
//       System.out.println("-------------------------");
//
//       // Вивести всіх замовників і їх опис
//       customerDao.getAllNames();
//       System.out.println("-------------------------");
//
//       // Добавити замовника
//       customerDao.addCustomer();
//       System.out.println("-------------------------");
    }
}


