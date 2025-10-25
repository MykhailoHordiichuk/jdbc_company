package ua.hw.jdbc;

import ua.hw.jdbc.company.*;

public class App {
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        EmployeeDAO dao = new EmployeeDAO(connector);

        try {
            // Добавление
            Employee e1 = dao.add(new Employee("John Doe", 30, "Developer", 2500f));
            Employee e2 = dao.add(new Employee("Anna Smith", 28, "QA Engineer", 2200f));
            System.out.println("Inserted:\n" + e1 + "\n" + e2);

            // Получение всех
            System.out.println("\nAll employees:");
            dao.findAll().forEach(System.out::println);

            // Обновление
            e1.setSalary(2800f);
            dao.update(e1);
            System.out.println("\nAfter update John:");
            dao.findById(e1.getId()).ifPresent(System.out::println);

            // Удаление
            dao.delete(e2.getId());
            System.out.println("\nAfter delete Anna, all employees:");
            dao.findAll().forEach(System.out::println);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
