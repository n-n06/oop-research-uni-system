package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import courses.*;
import database.Database;
import enums.*;
import research.*;
import social.updates.*;
import social.messages.*;
import users.*;
import users.employees.*;
import users.students.*;

public class Main {
	public static BufferedReader br;
	private static Main app = new Main();
	
	private Main() {
		
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
						app.adminMain((Admin) currentUser);
						break;
					
					case UserType.DEAN:
						app.deanMain((Dean) currentUser);
						break;
				
					case UserType.MANAGER:
						app.managerMain((Manager) currentUser);
						break;
			
					case UserType.STUDENT:
						app.studentMain((Student) currentUser);
						break;
		
					case UserType.TEACHER:
						app.teacherMain((Teacher) currentUser);
						break;
						
					case UserType.RESEARCHER:
						app.researcherEmployeeMain((ResearcherEmployee) currentUser);
						break;
					}
					break main;
				}else {
					System.out.println("Incorrect username of password. Please, try again.");
					
				}
			}

			


		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
	
	

	}
	
	private void adminMain(Admin admin) throws IOException {

		int choice = 0;
		String stringInput;
		adminmain: while (true) {
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
					break;
				case 2:
					Journal journal;
					Database.instance.getJournalRepo().displayJournals();
					
					journals: while (true) {
						try {
							System.out.println("Choose action with journals:\n0. Go back\n1. Read journal\n2. Toggle subscription to journal");
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
							System.out.println("Choose action with messages:\n0. Go back\n1. View all notifications\n2. View work messages only\n3. Send work message");
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
							System.out.println("Choose action with journals:\n0. Go back\n1. View all users\n2. View particular user\n3. Add user \n4. Update user\n5. Delete user");
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
								System.out.println("Choose type of user to make: \n0. Cancel\n1. Admin\n2. Dean\n3. Manager\4. Teacher\n5. Student\n6. Researcher Employee");
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
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, UserType.DEAN, School.values()[school-1]));
										
									} else if (choice == 3) {
										System.out.println("Choose manager type:");
										int typeId = 1;
										for (ManagerType m : ManagerType.values()) {
											System.out.println("" + typeId + ". " + m);
											typeId++;
										}
										managerType = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, ManagerType.values()[managerType-1]));
										
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
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, 
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
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, 
												School.values()[school-1], Degree.values()[degree-1]));
									} else if (choice == 6) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, School.values()[school-1]));
									}
								}
								break;
							case 4:
								System.out.println("Email of the user to update: ");
								stringInput = br.readLine();
								User u = Database.instance.getUsersRepo().getUser(stringInput);
								System.out.println("Choose updates for user: \n");
								if (u.getUserType() == UserType.DEAN) {
									System.out.println("0. Cancel\n 1. School");
									choice = Integer.parseInt(br.readLine());
									if (choice == 0) {
										continue;
									} else if (choice == 1) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										Dean d = (Dean) u;
										d.setSchool(School.values()[school-1]);
										admin.updateUser(d);
									}
								} else if (u.getUserType() == UserType.RESEARCHER) {
									System.out.println("0. Cancel\n 1. School");
									choice = Integer.parseInt(br.readLine());
									if (choice == 0) {
										continue;
									} else if (choice == 1) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										ResearcherEmployee r = (ResearcherEmployee) u;
										r.setSchool(School.values()[school-1]);
										admin.updateUser(r);
									}
									
								} else if (u.getUserType() == UserType.MANAGER) {
									System.out.println("0. Cancel\n 1. Type");
									choice = Integer.parseInt(br.readLine());
									if (choice == 0) {
										continue;
									} else if (choice == 1) {
										System.out.println("Choose Type: \n1. OR\n 2. Department");
										choice = Integer.parseInt(br.readLine());
										Manager m = (Manager) u;
										m.setManagerType(ManagerType.values()[choice-1]);
										admin.updateUser(m);
									}
									
								} else if (u.getUserType() == UserType.STUDENT) {
									Student s = (Student) u;
									System.out.println("0. Cancel\n 1. School\n 2. Increase Degree");
									choice = Integer.parseInt(br.readLine());
									if (choice == 0) {
										continue;
									} else if (choice == 1) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School sc : School.values()) {
											System.out.println("" + schoolId + ". " + sc);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										s.setSchool(School.values()[school-1]);
										admin.updateUser(s);
									} else if (choice == 2) {
										System.out.println("Choose new degree:");
										if (s.getDegree() == Degree.BACHELOR) {
											s.setDegree(Degree.MASTER);
										} else if (s.getDegree() == Degree.MASTER) {
											s.setDegree(Degree.PHD);
										}
										
									}
									
								} else if (u.getUserType() == UserType.TEACHER) {
									System.out.println("0. Cancel\n 1. School\n2. Teacher type");
									choice = Integer.parseInt(br.readLine());
									if (choice == 0) {
										continue;
									} else if (choice == 1) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										Teacher t = (Teacher) u;
										t.setSchool(School.values()[school-1]);
									} else if (choice == 2) {
										System.out.println("Choose teacher type:");
										int teachId = 1;
										for (TeacherType t : TeacherType.values()) {
											System.out.println("" + teachId + ". " + t);
											teachId++;
										}
										choice = Integer.parseInt(br.readLine());
										Teacher t = (Teacher) u;
										t.setTeacherType(TeacherType.values()[choice-1]);
									}
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
					break adminmain;
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
	
	private void deanMain(Dean dean) throws IOException {

		int choice = 0;
	    String stringInput;

	    deanmain: while (true) {
	        try {
	            System.out.println("Choose your action:");
	            System.out.println("0. Quit\n1. View news\n2. View journals\n3. View messages and notifications\n4. View Profile");
	            System.out.println("5. Manage requests");
	            System.out.println("6. View complaints");

	            choice = Integer.parseInt(br.readLine());

	            switch (choice) {
	                case 0: 
	                    break deanmain;
	                    
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
									Database.instance.getNewsRepo().getNews(new News(id)).addComment(new Comment(dean, stringInput));
									break;
								}
							} catch (NumberFormatException e) {
								System.err.println("Please input a valid number " + e.getMessage());
								continue;
							}

						}
						break;
					case 2:
						Journal journal;
						Database.instance.getJournalRepo().displayJournals();
						
						journals: while (true) {
							try {
								System.out.println("Choose action with journals:\n0. Go back\n1. Read journal\n2. Toggle subscription to journal");
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
									if (journal.getSubscribers().contains(dean)) {
										journal.removeSubscriber(dean);
										System.out.println("You have unsubscribed from " + journal.getName());
									} else {
										System.out.println("You have subscribed to " + journal.getName());
										journal.addSubscriber(dean);
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
								System.out.println("Choose action with messages:\n0. Go back\n1. View all notifications\n2. View work messages only\n3. Send work message");
								choice = Integer.parseInt(br.readLine());
								int id;
								
								switch (choice) {
								case 0:
									break msg;
								case 1:
									dean.viewMessages();
									break;
								case 2:
									dean.readWorkMessage();
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
									dean.sendWorkMessage(e, new WorkMessage(dean, e, stringInput));
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
						dean.viewPersonalProfile();
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
									dean.changePassword(stringInput);
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
	                	reqs: while (true) {
							try {
								System.out.println("Choose action with requests:\n0. Go back\n1. View Reqeusts\n2. Sign Request\n3. Decline Reqeust");
								choice = Integer.parseInt(br.readLine());

								int id;
								
								switch (choice) {
								case 0:
									break reqs;
								case 1:
									dean.viewRequests();
									break;
								case 2:
									System.out.println("ID of the request to sign: ");
									choice = Integer.parseInt(br.readLine());
									dean.signRequest(new Request(choice));
									break;
								case 3:
									System.out.println("ID of the request to reject: ");
									choice = Integer.parseInt(br.readLine());
									dean.rejectRequest(new Request(choice));
									break;
								}
							} catch (NumberFormatException e) {
								System.err.println("Please input a valid number " + e.getMessage());
								continue;
							}

						}
	                    break;
	                    
	                case 6:
	                	dean.viewComplaints();
	                	break;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("Please input a valid number: " + e.getMessage());
	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        } finally {
				Database.write();
			}
	    
	    }
	    
		
	}
	
	private void teacherMain(Teacher t) throws IOException {

		int choice = 0;
	    String stringInput;

	    teachermain: while (true) {
	        try {
	            System.out.println("Choose your action:");
	            System.out.println("0. Quit\n1. View news\n2. View journals\n3. View messages and notifications\n4. View Profile");
	            System.out.println("5. Manage courses");
	            System.out.println("6. Check Rating");
	            System.out.println("7. Send request");
	            System.out.println("8. Send complaint");

	            choice = Integer.parseInt(br.readLine());

	            switch (choice) {
	                case 0: 
	                    break teachermain;
	                    
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
									Database.instance.getNewsRepo().getNews(new News(id)).addComment(new Comment(t, stringInput));
									break;
								}
							} catch (NumberFormatException e) {
								System.err.println("Please input a valid number " + e.getMessage());
								continue;
							}

						}
					break;
					case 2:
						Journal journal;
						Database.instance.getJournalRepo().displayJournals();
						
						journals: while (true) {
							try {
								System.out.println("Choose action with journals:\n0. Go back\n1. Read journal\n2. Toggle subscription to journal");
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
									if (journal.getSubscribers().contains(t)) {
										journal.removeSubscriber(t);
										System.out.println("You have unsubscribed from " + journal.getName());
									} else {
										System.out.println("You have subscribed to " + journal.getName());
										journal.addSubscriber(t);
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
									t.viewMessages();
									break;
								case 2:
									t.readWorkMessage();
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
									t.sendWorkMessage(e, new WorkMessage(t, e, stringInput));
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
						t.viewPersonalProfile();
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
									t.changePassword(stringInput);
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
	                	
	                	//Course management
						
	                    break;
	                    
	                case 6:
	                	t.checkRating();
	                	break;
	                	
	                case 7:
	                	System.out.println("Send a request to the dean's office of " + t.getSchool());
						System.out.println("Message: ");
						stringInput = br.readLine();
	                	t.sendRequest(new Request(t, stringInput));
	                	break;
	                	
	                case 8:
	                	System.out.println("Email of the student to send a complaint about: ");
	                	User u = Database.instance.getUsersRepo().getUser(br.readLine());
						if (!(u instanceof Student)) {
							throw new Exception("Cannot write complaints about non-students");
						}
						Student s = (Student) u;
	                	System.out.println("Send a complaint to the dean's office of " + s.getSchool());
						System.out.println("Message: ");
						stringInput = br.readLine();
						System.out.println("Input urgency level from 1 to 3 where 1 is low, 3 is higih");
						int urg = Integer.parseInt(br.readLine());
						UrgencyLevel level = UrgencyLevel.values()[urg-1];
	                	t.sendComplaint(new Complaint(t, stringInput, level));
	                	break;
	                	
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("Please input a valid number: " + e.getMessage());
	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        } finally {
				Database.write();
			}
	    
	    }
		
	}
	
	private static void studentMain(Student s) {
		int choice = 0;
	    String stringInput;

	    studentmain: while (true) {
	        try {
	            System.out.println("Choose your action:");
	            System.out.println("0. Quit\n1. View news\n2. View journals\n3. View Profile");
	            System.out.println("4. View courses");
	            System.out.println("5. View marks");
	            System.out.println("6. View schedule");

	            choice = Integer.parseInt(br.readLine());

	            switch (choice) {
	                case 0: 
	                    break studentmain;
	                    
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
									Database.instance.getNewsRepo().getNews(new News(id)).addComment(new Comment(s, stringInput));
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
									if (journal.getSubscribers().contains(s)) {
										journal.removeSubscriber(s);
										System.out.println("You have unsubscribed from " + journal.getName());
									} else {
										System.out.println("You have subscribed to " + journal.getName());
										journal.addSubscriber(s);
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
						s.viewPersonalProfile();
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
									s.changePassword(stringInput);
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
	                	s.displayOwnCourses();
	                	CourseRegistrationService crs = Database.instance.getRegistration();
	                	CourseRepository courseRepo = Database.instance.getCourseRepo();
	                	courses: while (true) {
							try {
								System.out.println("Choose action with courses:\n0. Go back\n1. Register for course");
								choice = Integer.parseInt(br.readLine());
								
								switch (choice) {
								case 0:
									break courses;
								case 1:
									String id;
									if(crs.checkIsOpen()) {
										courseRepo.displayCourses();
										System.out.println("Write an ID of course to register: ");
										id = br.readLine();
										s.registerForCourse(courseRepo.getCourseByID(id));
										System.out.println("Thank's for request! Manager will verify/decline your request");
									}
									else {
										System.out.println("Registarion for courses is closed");
									}
									
									break;
								}
							} catch (NumberFormatException e) {
								System.err.println("Please input a valid number " + e.getMessage());
								continue;
							}

						}
	                    break;
	                    
	                	case 5:
	                		courseRepo = Database.instance.getCourseRepo();
		                	marks: while (true) {
								try {
									System.out.println("Choose action with marks:\n0. Go back\n1. View Transcript\n 2. View attestaions");
									choice = Integer.parseInt(br.readLine());
									
									switch (choice) {
									case 0:
										break marks;
									case 1:
										s.viewTranscript();
										break;
									case 2:
										if (!s.getCurrentCourses().isEmpty()) {
											courseRepo.displayCourses();
											System.out.println("Write an ID of course to check marks: ");
											String id = br.readLine();
											s.viewMarks(courseRepo.getCourseByID(id));
										}
											break;
									}
								} catch (NumberFormatException e) {
									System.err.println("Please input a valid number " + e.getMessage());
									continue;
								}

							}
		                    break;
		                    
	                	case 6:
	                		courseRepo = Database.instance.getCourseRepo();
	                		schedule: while (true) {
								try {
									System.out.println("Choose action with schedule:\n0. Go back\n1. View Schedule\n 2. Add lesson to schedule");
									choice = Integer.parseInt(br.readLine());
									
									switch (choice) {
									case 0:
										break schedule;
									case 1:
										s.viewSchedule();
										break;
									case 2:	
										int index;
										s.displayOwnCourses();
										System.out.println("Write an ID of course to add lessons to schedule: ");
										String id = br.readLine();
										s.displayLessonsForScheduling(courseRepo.getCourseByID(id));
										index = Integer.parseInt(br.readLine());
										s.pickLessonToSchedule(index, courseRepo.getCourseByID(id));
										
										break;
									}
								} catch (NumberFormatException e) {
									System.err.println("Please input a valid number " + e.getMessage());
									continue;
								}

							}
		                    break;                	      		             
	                	
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("Please input a valid number: " + e.getMessage());
	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        } 
	    
	    }
	}
	
	private void researcherEmployeeMain(ResearcherEmployee r) throws IOException  {
		
	}
	
	private void managerMain(Manager m) throws IOException  {
		
	}
	
	private void researcherGeneralMain() {
		
	}

	
}
