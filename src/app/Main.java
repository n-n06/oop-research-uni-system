package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import courses.*;
import database.Database;
import enums.*;
import research.*;
import social.updates.*;
import social.messages.*;
import users.*;
import users.employees.*;
import users.students.*;
import utilities.comparators.StudentAlphabetComparator;
import utilities.comparators.StudentGpaComparator;
import utilities.comparators.TeacherAlphabetComparator;
import utilities.comparators.TeacherRatingComparator;

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
		} finally {
			Database.write();
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
								break;
							case 2:
								System.out.println("Email of the user you would like to view: ");
								stringInput = br.readLine();
								admin.viewUserInfo(stringInput);
								break;
							case 3:
								System.out.println("Choose type of user to make: \n0. Cancel\n1. Admin\n2. Dean\n3. Manager\n4. Teacher\n5. Student\n6. Researcher Employee");
								id = Integer.parseInt(br.readLine());
								String firstName, lastName;
								int age, school, managerType, degree, teacherType;
								Gender gender;
								if (id == 0) {
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
									if (id == 1) {
										admin.addUser(uf.makeUser(firstName, lastName, age, gender));
										
									} else if (id == 2) {
										System.out.println("Choose school:");
										int schoolId = 1;
										for (School s : School.values()) {
											System.out.println("" + schoolId + ". " + s);
											schoolId++;
										}
										school = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, UserType.DEAN, School.values()[school-1]));
										
									} else if (id == 3) {
										System.out.println("Choose manager type:");
										int typeId = 1;
										for (ManagerType m : ManagerType.values()) {
											System.out.println("" + typeId + ". " + m);
											typeId++;
										}
										managerType = Integer.parseInt(br.readLine());
										admin.addUser(uf.makeUser(firstName, lastName, age, gender, ManagerType.values()[managerType-1]));
										
									} else if (id == 4) {
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
										
									} else if (id == 5) {
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
									} else if (id == 6) {
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

	                case 5:;
						courses: while (true) {
							try {
								System.out.println("Choose action with profile:\n0. Go back\n1. View Own Courses.\n2.View lessons of course \n3. View schedule. ");
								choice = Integer.parseInt(br.readLine());
								int id;
								
								switch (choice) {
								case 0:
									break courses;
								case 1:
									t.viewOwnCourses();
									break;
								case 2:
									put_mark : while (true) {
										CourseRepository cr = Database.instance.getCourseRepo();
										System.out.println("Write ID of course:");
										String courseid = br.readLine();
										Course course = cr.getCourseByID(courseid);
										t.viewOwnLessons(course);
									
										System.out.println("Choose action with courses: \n0. Go back\n1. Put marks for lesson.");
										Integer choice1 = Integer.parseInt(br.readLine());
										switch(choice1) {
										case 0:
											break put_mark;
										case 1:
											System.out.println("Write lesson ID to put:");
											Integer lessonID = Integer.parseInt(br.readLine());
											Lesson lesson = course.getLessonByID(choice1);
											System.out.println("Write Student email to put:");
											String studentID = br.readLine();
											Student s = (Student)Database.instance.getUsersRepo().getUser(studentID);
											System.out.println("Write point to put:");
											Double point = Double.parseDouble(br.readLine());
											Mark m = new Mark();
											m.addFirstAttestationMark(point);
											course.getGradeBook().put(s, m);
//											t.putMarkToLesson(lesson, s , point, 1);
											
											break;
										}
									}
									case 3:
										t.viewSchedule();
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
	
	private static void studentMain(Student s) throws IOException {
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
						break;
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
	        } finally {
				Database.write();
			}
	    
	    }
	}
	
	private void researcherEmployeeMain(ResearcherEmployee r) throws IOException  {
		
	}
	
	private void managerMain(Manager m) throws IOException  {

		int choice = 0;
		String stringInput;
		managermain: while (true) {
			try {
				System.out.println("Choose your action:\n0. Quit\n1. View news\n2. View journals\n3. View messages and notifications\n4. View Profile\n5. View Student info\n6. View Teacher info\n7. Manage requests\n8. Manage courses");
				choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 0:
					break managermain;
				
				case 1:
					Database.instance.getNewsRepo().displayAllNews();
					news: while (true) {
						try {
							System.out.println("Choose action with news:\n0. Go back\n1. Add comment\n2. Add news");
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
								Database.instance.getNewsRepo().getNews(new News(id)).addComment(new Comment(m, stringInput));
								break;
							case 2:
								String s1, s2, s3;
								NewsType nt;
								System.out.println("Title: ");
								s1= br.readLine();
								System.out.println("Type (R for research, G - general): ");
								s2 = br.readLine();
								nt = (s2.equalsIgnoreCase("R") ? NewsType.RESEARCH : NewsType.GENERAL);
								System.out.println("Content: ");
								s3 = br.readLine();
								m.postNews(new News(s1, nt, s3));
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
							System.out.println("Choose action with journals:\n0. Go back\n1. Read journal\n2. Toggle subscription to journal\n3. Add journal");
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
								if (journal.getSubscribers().contains(m)) {
									journal.removeSubscriber(m);
									System.out.println("You have unsubscribed from " + journal.getName());
								} else {
									System.out.println("You have subscribed to " + journal.getName());
									journal.addSubscriber(m);
								}
								break;
							
							case 3:
								String s1,s2,s3;
								System.out.println("Name: ");
								s1 = br.readLine();
								System.out.println("Topic: ");
								s2 = br.readLine();
								System.out.println("Description: ");
								s3 = br.readLine();
								Database.instance.getJournalRepo().addJournal(new Journal(s1,s2,s3, Language.ENGLISH));
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
								m.viewMessages();
								break;
							case 2:
								m.readWorkMessage();
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
								m.sendWorkMessage(e, new WorkMessage(m, e, stringInput));
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
					m.viewPersonalProfile();
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
								m.changePassword(stringInput);
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
					studinfo: while(true) {
						try {
					
							System.out.println("View Student info: \n0. Go back\n1. Default\n2. GPA comparator\n3. Alphabetical comparator");
							choice = Integer.parseInt(br.readLine());
							if (choice == 0) {
								break studinfo;
							} else if (choice == 1) {
								m.viewStudentsInfo();
							} else if (choice == 2) {
								m.viewStudentsInfo(new StudentGpaComparator());
							} else if (choice == 3) {
								m.viewStudentsInfo(new StudentAlphabetComparator());
							} else {
								System.out.println("Input valid option");
								continue;
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
				case 6:
					teacherinfo: while(true) {
						try {
							System.out.println("View Teacher info: \n0. Go back\n1. Default\n2. GPA comparator\n3. Alphabetical comparator");
							choice = Integer.parseInt(br.readLine());
							if (choice == 0) {
								break teacherinfo;
							} else if (choice == 1) {
								m.viewTeachersInfo();
							} else if (choice == 2) {
								m.viewTeachersInfo(new TeacherRatingComparator());
							} else if (choice == 3) {
								m.viewTeachersInfo(new TeacherAlphabetComparator());
							} else {
								System.out.println("Input valid option");
								continue;
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
				case 7:
					reqs: while (true) {
						try {
							System.out.println("Manage requests: \n0. Go back\n1. View all requests\n2. View request\n3. Accept request\n4. Decline request");
							choice = Integer.parseInt(br.readLine());
							Request r = null;
							School sc = null;
							if (choice == 0) {
								break reqs;
							} else if (choice == 1) {
								int school;
								System.out.println("Choose school:");
								int schoolId = 1;
								for (School s : School.values()) {
									System.out.println("" + schoolId + ". " + s);
									schoolId++;
								}
								school = Integer.parseInt(br.readLine());
								m.viewAllRequests(School.values()[school-1]);
							} else if (choice == 2) {
								
								int school;
								System.out.println("Choose school:");
								int schoolId = 1;
								for (School s : School.values()) {
									System.out.println("" + schoolId + ". " + s);
									schoolId++;
								}
								school = Integer.parseInt(br.readLine());
								System.out.println("Pick an id:");
								choice = Integer.parseInt(br.readLine());
								sc = School.values()[school-1];
								r = Database.instance.getReqeustRepo().getRequest(sc, new Request(choice));
								m.viewRequest(new Request(choice),School.values()[school-1]);
							} else if (choice == 3) {
								if (r == null) {
									System.out.println("Select a request first");
									continue;
								}
								m.acceptRequest(r, sc);
							} else if (choice == 4) {
								if (r == null) {
									System.out.println("Select a request first");
									continue;
								}
								m.declineRequest(r, sc);
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
				case 8:
					course: while(true) {
						String s1, s2 = "";
						try {
							System.out.println("Choose action: \n0. Go back\n1. Add course\n2. View course\n3. Assign course to teacher\n4. View courses\n5. Open reg\n6. Close reg\n7. Verify\n8. Fill Lessons");
							choice = Integer.parseInt(br.readLine());
							if (choice == 0) {
								break course;
							} else if (choice == 1) {
								System.out.println("Course id: ");
								s1 = br.readLine();
								System.out.println("Course name: ");
								s2 = br.readLine();
								Course c = new Course(s1, s2);
								m.addCourse(c);
							} else if (choice == 2) {
								System.out.println("Course id: ");
								s1 = br.readLine();
								m.viewCourse(s1);
							} else if (choice == 3) {
								Database.instance.getCourseRepo().displayCourses();
								System.out.println("Course id: ");
								s1 = br.readLine();
								System.out.println("Teacher email: ");
								s2 = br.readLine();
								Course c = Database.instance.getCourseRepo().getCourseByID(s1);
								Teacher t = (Teacher) Database.instance.getUsersRepo().getUser(s2);
								m.assignCourseToTeacher(c, t);
								System.out.println("course added to teacher");
							} else if (choice == 4) {
								Database.instance.getCourseRepo().displayCourses();
							} else if (choice == 5) {
								m.openRegistration();
							} else if (choice == 6) {
								m.closeRegistration();
							} else if (choice == 7) {
								m.viewAllRegRequest();
								int reqId;
								System.out.println("Input registration request id");
								reqId = Integer.parseInt(br.readLine());
								m.verifyRegistration(reqId);
							} else if (choice == 8) {
								Teacher t;
								Course c1;
								System.out.println("Id of the course: ");
								c1 = Database.instance.getCourseRepo().getCourseByID(br.readLine());
								System.out.println("Teacher email: ");
								t = (Teacher) Database.instance.getUsersRepo().getUser(br.readLine());
								LocalDate d1 = LocalDate.of(2024,12,23);
								m.addLessonToCourse(c1, 414, d1, DayOfWeek.MONDAY, 
										new TimeWindow(LocalTime.of(10, 0), LocalTime.of(11, 0)), LessonType.PRACTICE, t);
							}
						} catch (NumberFormatException e) {
							System.err.println("Please input a valid number " + e.getMessage());
							continue;
						} catch (ClassCastException e) {
							System.err.println(s2 + " is not a teacher!");
							continue;
						}
						catch (Exception e) {
							System.err.println(e.getMessage());
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
			finally {
				Database.write();
			}
	    }
	}
	
	private void researcherGeneralMain() {
		
	}

	
}
