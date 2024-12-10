package demo;

import java.io.*;

import database.Database;

public class DemoSystem {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice;
		String stringInput1;
		String stringInput2;
		
		try {
				login :while(true) {
				System.out.println("🏛Welcome!🏛");	
				System.out.println("Enter your username:");
				stringInput1 = br.readLine();
				System.out.println("Enter your password:");
				stringInput2 = br.readLine();
				if (Database.instance.getUsersRepo().login(stringInput1, stringInput2)) {
					
					
					loggedUser=null;
					loggedUser=Database.DATA.getUsers().get(new Credentials(strIn, strIn1));
					System.out.println("You've logged succesfully");
					Database.DATA.getLogs().add(loggedUser + " logged into system at " + new Date());
					loggedUser.run();
				}else {
					System.out.println("Incorrect username of password. Please, try again.");
				}
			}
			
		}catch(Exception e) {
			System.out.println("Oopsiees");
			System.out.println(e.getMessage());
			save();
		}

	}

}
