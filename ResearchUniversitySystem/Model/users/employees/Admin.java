package users.employees;

import java.util.Comparator;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    private void processLogFile(File logFile ) {
        System.out.println("🗒Reading log file: " + logFile.getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
        	//the most efficient one as you said :)
            String logline;
            while ((logline = reader.readLine()) != null) {
                System.out.println(logline); 
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void readLogs() {
        String logDir = "./logs"; 

        try {
            Files.list(Paths.get(logDir))
                    .filter(p->p.getFileName().toString().matches("app\\.\\d+\\.\\d+\\.log"))
                    .sorted(Comparator.comparing(Path::toString))
                    .forEach(p->processLogFile(p.toFile()));
        } catch (IOException e) {
        	System.err.println(e.getMessage());
        }
    }
    
    @Override
    public String toString() {
    	return "🧑‍💻Admin " + super.toString();
    }

}