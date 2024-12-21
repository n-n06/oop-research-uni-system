package users.employees;

import database.Database;
import enums.Gender;
import users.User;

public class Admin extends Employee {
    public Admin() {
    }
    
    public Admin(String email) {
    	setEmail(email);
    }
    
    public Admin(String firstName, String lastName, int age, Gender gender) {
    	super(firstName, lastName, age, gender);
    }
    
    public void addUser(User user) {
    	Database.instance.getUsersRepo().addUser(user);
    }

    public void updateUser(User user) {
        Database.instance.getUsersRepo().updateUser(user);
    }

    public void deleteUser(User user) {
        Database.instance.getUsersRepo().removeUser(user);
    }

    public void viewAllUsers() {
    	System.out.println(Database.instance.getUsersRepo().getAllUsers());
    }
    public void viewUserInfo(String email) {
        System.out.println(Database.instance.getUsersRepo().getUser(email));
    }

    public void readLogs() {
        // TODO implement here
        return ;
    }

}