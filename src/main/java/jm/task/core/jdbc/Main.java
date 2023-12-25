package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Петр", "Петров", (byte) 54);
        userService.saveUser("Сасуке", "Узумаки", (byte) 16);
        userService.saveUser("Хуба", "Буба", (byte) 50);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
