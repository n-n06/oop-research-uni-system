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
    	Database.instance.getUsersRepo().getAllUsers().forEach(u->System.out.println(u + "\n"));
    }
    public void viewUserInfo(String email) {
        System.out.println(Database.instance.getUsersRepo().getUser(email));
    }

    private void processLogFile() {
        System.out.println("🗒Reading log file: " + logFile.getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Process the log line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void readLogs() {
    	
        return ;
    }

}