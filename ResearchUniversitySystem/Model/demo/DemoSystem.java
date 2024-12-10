package demo;

import java.io.*;

import database.Database;
import enums.NewsType;
import enums.UserType;
import menuInfo.Journal;
import menuInfo.News;
import users.UserFactory;

public class DemoSystem {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice;
		String stringInput1;
		String stringInput2;
		
		try {
			
			System.out.println("🏛Welcome!🏛");	
			System.out.println("Enter your username:");
			stringInput1 = br.readLine();
			System.out.println("Enter your password:");
			stringInput2 = br.readLine();
			
			
			Database.instance.getNewsRepo().addNews(new News("Test  general news!", NewsType.GENERAL));
			Database.instance.getNewsRepo().addNews(new News("Test  research news!", NewsType.RESEARCH));
			Database.instance.getJournalRepo().addJournal(new Journal("Test Journal"));
			
			
			if (Database.instance.getUsersRepo().login(stringInput1, stringInput2)) {
				System.out.println("You've logged succesfully");
			}else {
				System.out.println("Incorrect username of password. Please, try again.");
			}
			while (true) {
				System.out.println("Choose you action:\n0. Quit\n1. View news\n2. View journals\n3. ViewProfile\n4. Add User\5. View users");
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
						Database.instance.getUsersRepo().getUser(stringInput1).viewPersonalProfile();
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
					
					case 0:
						break;
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
