# ProjectManagementSystem
Система розроблена для MySQL

В папці hw_m3_1: у файлі initDB.sql - Структура бази даних;
                 у файлі populateDB.sql - Дані для завантаження бази даних
                 
В папці hw_m3_2: файли з запитами і оновленнями бази даних.
Перед тим як розпочати роботу з ситемою потрібно завантажети всі файли в MySQL

У файлі prefs.json - шлях до базі, логін і пароль.  

Домашнее задание 4

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
