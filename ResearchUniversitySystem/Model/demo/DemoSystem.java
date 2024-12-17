package demo;

import java.io.*;

import database.Database;

import enums.NewsType;
import enums.UserType;
import social.updates.Journal;
import social.updates.News;
import users.UserFactory;
import users.User;

import users.employees.*;

public class DemoSystem {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice;
		String stringInput1;
		String stringInput2;
		
		
		User currentUser = new Admin();
		
		try {
			Database.instance.getNewsRepo().addNews(new News("Test News", NewsType.GENERAL, "This is a test uwu"));
			
			System.out.println("🏛Welcome!🏛");	
			while (true) {
				
				System.out.println("Enter your username:");
				stringInput1 = br.readLine();
				System.out.println("Enter your password:");
				stringInput2 = br.readLine();
				
				if (Database.instance.getUsersRepo().login(stringInput1, stringInput2)) {
					System.out.println("You've logged succesfully");
					currentUser = Database.instance.getUsersRepo().getUser(stringInput1);
					break;
				}else {
					System.out.println("Incorrect username of password. Please, try again.");
					
				}
			}

			
			main: while (true) {
				System.out.println("Choose you action:\n0. Quit\n1. View news\n2. View journals\n3. ViewProfile\n4. Add User\n5. View users");
				choice = Integer.parseInt(br.readLine());
				
				try {
					switch (choice) {
					case 1:
						Database.instance.getNewsRepo().displayAllNews();
						break;
					case 2:
						Database.instance.getJournalRepo().displayJournals();
						break;
					case 3:
						currentUser.viewPersonalProfile();
						break;
					case 4:
						UserFactory uf = new UserFactory();
						String firstName, lastName, email, pass;
						System.out.println("First name: ");
						firstName = br.readLine();
						System.out.println("Last name: ");
						lastName = br.readLine();
						email = firstName.toLowerCase() + lastName.toLowerCase() + "@kbtu.kz";
						Database.instance.getUsersRepo().addUser(uf.makeUser(firstName, lastName, email, UserType.ADMIN));
						break;
					
					case 5:
						Database.instance.getUsersRepo().viewAllUsers((Admin)currentUser);
						break;
						
					case 0:
						break main;
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				

			}

		
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Database.write();
		}

	}

}
