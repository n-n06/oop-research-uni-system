package demo;

import java.io.*;

import javax.xml.crypto.Data;

import database.Database;

import enums.*;
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
						news: while (true) {
							System.out.println("Choose action with news:\n0. Go back\n1. Add news\n2. Remove news");
							choice = Integer.parseInt(br.readLine());
							
							switch (choice) {
							case 0:
								break news;
							case 1:
								String title, content;
								System.out.println("Title: ");
								title = br.readLine();
								System.out.println("Content: ");
								content = br.readLine();
								System.out.println("Type: ");
								NewsType type = (br.readLine().equalsIgnoreCase("r") ? NewsType.RESEARCH : NewsType.GENERAL);
								Database.instance.getNewsRepo().addNews(new News(title, type, content));
								break;
							case 2:
								int newsId;
								System.out.println("Id of the news to remove: ");
								newsId = Integer.parseInt(br.readLine());
								Database.instance.getNewsRepo().removeNews(new News(newsId));
								break;
							}
						}
						break;
					case 2:
						Database.instance.getJournalRepo().displayJournals();
						break;
					case 3:
						currentUser.viewPersonalProfile();
						break;
					case 4:
						UserFactory uf = new UserFactory();
						String firstName, lastName, email, genderInput;
						int age;
						Gender gender;
						System.out.println("First name: ");
						firstName = br.readLine();
						System.out.println("Last name: ");
						lastName = br.readLine();
						email = firstName.toLowerCase() + lastName.toLowerCase() + "@kbtu.kz";
						System.out.println("Age: ");
						age = Integer.parseInt(br.readLine());
						System.out.println("Gender (Type M for male and F for female): ");
						gender = br.readLine().equalsIgnoreCase("F") ? Gender.FEMALE : Gender.MALE;
						Database.instance.getUsersRepo().addUser(uf.makeUser(firstName, lastName, email, age, gender, UserType.ADMIN));
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
