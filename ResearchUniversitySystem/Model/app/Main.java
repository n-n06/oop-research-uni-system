package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.Database;
import enums.*;
import research.*;
import social.updates.*;
import social.messages.*;
import users.*;
import users.employees.*;
import users.students.*;
import utilities.logging.LoggerProvider;

public class Main {
	public static BufferedReader br;
	
	private static void adminMain(Admin admin) {
		int choice = 0;
		String stringInput;
		main: while (true) {
			try {
				System.out.println("Choose your action:\n0. Quit\n1. View news\n2. View journals\n3. View messages and notifications\n4. View Profile\n5. Manage Users\n6. Read logs");
				choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					Database.instance.getNewsRepo().displayAllNews();
					news: while (true) {
						try {
							System.out.println("Choose action with news:\n0. Go back\n1. Add comment");
							choice = Integer.parseInt(br.readLine());

							int id;
							
							switch (choice) {
							case 0:
								break news;
							case 1:
								System.out.println("ID of the news you would like to comment: ");
								id = Integer.parseInt(br.readLine());
								System.out.println("Text of your comment: ");
								stringInput = br.readLine();
								Database.instance.getNewsRepo().getNews(new News(id)).addComment(new Comment(admin, stringInput));
								break;
							}
						} catch (NumberFormatException e) {
							System.err.println("Please input a valid number " + e.getMessage());
							continue;
						}

					}
				case 2:
					Journal journal;
					Database.instance.getJournalRepo().displayJournals();
					
					journals: while (true) {
						try {
							System.out.println("Choose action with journals:\n0. Go back\n1. Read journal\n 2. Toggle subscription to journal");
							choice = Integer.parseInt(br.readLine());

							int id;
							
							switch (choice) {
							case 0:
								break journals;
							case 1:
								System.out.println("ID of the journal you would like to read: ");
								id = Integer.parseInt(br.readLine());
								Database.instance.getJournalRepo().getJournal(new Journal(id)).displayArticles();
								break;
							case 2:
								System.out.println("ID of the journal you would like to subscribe/unsubsribe to: ");
								id = Integer.parseInt(br.readLine());
								journal = Database.instance.getJournalRepo().getJournal(new Journal(id));
								if (journal.getSubscribers().contains(admin)) {
									journal.removeSubscriber(admin);
									System.out.println("You have unsubscribed from " + journal.getName());
								} else {
									System.out.println("You have subscribed to " + journal.getName());
									journal.addSubscriber(admin);
								}
								break;
							}
						} catch (NumberFormatException e) {
							System.err.println("Please input a valid number " + e.getMessage());
							continue;
						}

					}
					break;
				
				case 3:
					msg: while (true) {
						try {
							System.out.println("Choose action with messages:\n0. Go back\n1. View all notifications\n 2. View work messages only\n3. Send work message");
							choice = Integer.parseInt(br.readLine());
							int id;
							
							switch (choice) {
							case 0:
								break msg;
							case 1:
								admin.viewMessages();
								break;
							case 2:
								admin.readWorkMessage();
								break;
							case 3:
								System.out.println("Email of the employee to send a work message to: ");
								stringInput = br.readLine();
								User u = Database.instance.getUsersRepo().getUser(stringInput);
								if (!(u instanceof Employee)) {
									throw new Exception("Cannot write work messages to non-employees");
								}
								Employee e = (Employee) u;
								System.out.println("Message: ");
								stringInput = br.readLine();
								admin.sendWorkMessage(e, new WorkMessage(admin, e, stringInput));
								break;
							}
						} catch (NumberFormatException e) {
							System.err.println("Please input a valid number " + e.getMessage());
							continue;
						} catch (Exception e) {
							System.err.println(e.getMessage());
							continue;
						}

					}
					break;
				case 4:
					admin.viewPersonalProfile();
					pf: while (true) {
						try {
							System.out.println("Choose action with profile:\n0. Go back\n1. Change password");
							choice = Integer.parseInt(br.readLine());
							int id;
							
							switch (choice) {
							case 0:
								break pf;
							case 1:
								System.out.println("Input your new password: ");
								stringInput=br.readLine();
								if (stringInput.length() < 7) {
									throw new IllegalArgumentException("Password too short!");
								}
								admin.changePassword(stringInput);
								break;
							}
						} catch (NumberFormatException e) {
							System.err.println("Please input a valid number " + e.getMessage());
							continue;
						} catch (Exception e) {
							System.err.println(e.getMessage());
							continue;
						}

					}
					break;
				case 5:
					UserFactory uf = new UserFactory();
					
					user: while (true) {
						try {
							System.out.println("Choose action with journals:\n0. Go back\n1. View all users\n2. View particular user\n 3. Add user \n4. Update user\n5.Delete user");
							choice = Integer.parseInt(br.readLine());

							int id;
							
							switch (choice) {
							case 0:
								break user;
							case 1:
								admin.viewAllUsers();
							case 2:
								System.out.println("Email of the user you would like to view: ");
								stringInput = br.readLine();
								admin.viewUserInfo(stringInput);
								break;
							case 3:
								System.out.println("Choose type of user to make: \n0. Cancel\n1. Admin\n2. Dean\n3. Manager\4Teacher\n5. Student\n6. Researcher Employee");
								choice = Integer.parseInt(br.readLine());
								String firstName, lastName;
								int age, school, managerType, degree, teacherType;
								Gender gender;
								if (choice == 0) {
									continue;
								} else {
									System.out.println("First name: ");
									firstName = br.readLine();
									System.out.println("Last name: ");
									lastName = br.readLine();
									System.out.println("Age: ");
									age = Integer.parseInt(br.readLine());
									System.out.println("Gender (Type M for male and F for female): ");
									gender = br.readLine().equalsIgnoreCase("F") ? Gender.FEMALE : Gender.MALE;
									if (choice == 1) {
										admin.addUser(uf.makeUser(firstName, lastName, age, gender));
										
									} else if (choice == 2) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, School.values()[school-1]));
										
									} else if (choice == 3) {
										System.out.println("Choose manager type:");
										int typeId = 1;
										for (ManagerType m : ManagerType.values()) {
											System.out.println("" + typeId + ". " + m);
											typeId++;
										}
										managerType = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, managerType, gender, ManagerType.values()[managerType-1]));
										
									} else if (choice == 4) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										System.out.println("Choose teacher type:");
										int tType = 1;
										for (TeacherType t : TeacherType.values()) {
											System.out.println("" + tType + ". " + t);
											tType++;
										}
										teacherType = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, managerType, gender, 
												School.values()[school-1], TeacherType.values()[teacherType-1]));
										
									} else if (choice == 5) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										int d = 1;
										for (Degree studentDegree : Degree.values()) {
											System.out.println("" + d + ". " + studentDegree);
											d++;
										}
										degree = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, managerType, gender, 
												School.values()[school-1], Degree.values()[degree-1]));
									}
								}
								break;
							case 4:
								System.out.println("Email of the user to update: ");
								stringInput = br.readLine();
								User u = Database.instance.getUsersRepo().getUser(stringInput);
								System.out.println("Choose updates for user: \n");
								int listId = 0;
								if (u.getUserType() == UserType.DEAN || u.getUserType() == UserType.TEACHER || u.getUserType() == UserType.STUDENT || u.getUserType() == UserType.RESEARCHER) {
									
								}
								choice = Integer.parseInt(br.readLine());
								if (choice == 0) {
									continue;
								}
								break;
							case 5:
								System.out.println("Email of the user to remove: ");
								stringInput = br.readLine();
								admin.deleteUser(Database.instance.getUsersRepo().getUser(stringInput));
								break;
							}
						} catch (NumberFormatException e) {
							System.err.println("Please input a valid number " + e.getMessage());
							continue;
						}

					}
					break;
				
				case 6:
					admin.readLogs();
					break;
					
				case 0:
					break main;
				}
			} catch (NumberFormatException e){
				System.err.println("Please, input a valid number: " + e.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				Database.write();
			}
			
		}
	}
	
	private static void deanMain(Dean d) {
		
	}
	
	private static void teacherMain(Teacher t) {
		
	}
	
	private static void studentMain(Student s) {
		
	}
	
	private static void researcherEmployeeMain(ResearcherEmployee r) {
		
	}
	
	private static void managerMain(Manager m) {
		
	}
	
	private static void researcherGeneralMain() {
		
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		String stringInput1;
		String stringInput2;
		
		
		User currentUser;		
		Researcher researcherAccount;
		
		try {
			
			System.out.println("🏛Welcome to MEYN University!🏛");	
			main: while (true) {
				
				System.out.println("Enter your username:");
				stringInput1 = br.readLine();
				System.out.println("Enter your password:");
				stringInput2 = br.readLine();
				
				if (Database.instance.getUsersRepo().login(stringInput1, stringInput2)) {
					System.out.println("You've logged in succesfully");
					currentUser = Database.instance.getUsersRepo().getUser(stringInput1);
					switch (currentUser.getUserType()) {
					case UserType.ADMIN:
						adminMain((Admin) currentUser);
						break;
					
					case UserType.DEAN:
						deanMain((Dean) currentUser);
						break;
				
					case UserType.MANAGER:
						managerMain((Manager) currentUser);
						break;
			
					case UserType.STUDENT:
						studentMain((Student) currentUser);
						break;
		
					case UserType.TEACHER:
						teacherMain((Teacher) currentUser);
						break;
						
					case UserType.RESEARCHER:
						researcherEmployeeMain((ResearcherEmployee) currentUser);
						break;
					}
					break main;
				}else {
					System.out.println("Incorrect username of password. Please, try again.");
					
				}
			}

			


	}
	
	


}
