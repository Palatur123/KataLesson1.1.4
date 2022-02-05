package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static final UserService userService = new UserServiceImpl();
    public static final User user1 = new User("Roman", "Palaturyan" , (byte) 23 );
    public static final User user2 = new User("Artem", "Donscov" , (byte) 21);
    public static final User user3 = new User("Alesha", "Popovich" , (byte) 99 );
    public static final User user4 = new User("Arkadiy", "Zmey" , (byte) 30 );

    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
