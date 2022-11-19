package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Alex, Juan, and Richfield
 *
 */
public class ProjectInterface {
	static Scanner in = new Scanner(System.in);
	static Connection rdsConnection;
	
	/**
	 * Gets username and password from user and attempts to connect to the UNF oracle sql server.
	 * On success, the connection to the database is stored in the rdsConnection variable which can be used across the class
	 * 
	 * @throws SQLException on failure such as incorrect credentials
	 */
	private static void getRemoteDatabaseConnection() throws SQLException {
		System.out.println("Database username: ");
		String userName = in.nextLine();
		System.out.println("Database password: ");
		String password = in.nextLine();
		
		String hostname = "cisvm-oracle.unfcsd.unf.edu";
		String port = "1521";

		rdsConnection = DriverManager.getConnection("jdbc:oracle:thin:@" + hostname + ":" + port + ":orcl", userName, password);
	}
	
	/**
	 * Developer method to initialize databases
	 */
	private static void createTables() {
		//TODO implement me
	}

	/**
	 * Forks user off into the specific add data methods based on user input
	 */
	private static void addData() {
		String userInput = "";
		
		while(!userInput.equals("q")) {
			System.out.println("Add data: (st) Student | (d) Department | (i) Instructor | (c) Course | (se) Section | (sc) Student Courses | (g) Grade | (q) Exit");
			do {
				userInput = in.nextLine();				
			}
			while(userInput.equals(""));
			
			switch (userInput) {
				case "st":
					System.out.println("Entering Student Data");
					addStudent();
					break;
	
				case "d":
					System.out.println("Entering Department Data");
					addDepartment();
					break;
	
				case "i":
					System.out.println("Entering Instructor Data");
					addInstructor();
					break;

				case "c":
					System.out.println("Entering Course Data");
					addCourse();
					break;	
				
				case "se":
					System.out.println("Entering Section Data");
					addSection();
					break;
	
				case "sc":
					System.out.println("Entering Student Course Data");
					addStudentToCourse();
					break;
	
				case "g":
					System.out.println("Entering Grade Data");
					addGrade();
					break;
					
				case "q":
					System.out.println("Exiting...");
					break;
				
				default:
					System.out.println("Invalid Menu Input: " + userInput);
					break;
			}
		}
		
		//TODO Implement me
	}
	
	private static void addStudent() {
		//TODO Implement me
	}
	
	private static void addDepartment() {
		//TODO Implement me
	}

	private static void addInstructor() {
		//TODO Implement me
	}
	
	private static void addCourse() {
		//TODO Implement me
	}
	
	private static void addSection() {
		//TODO Implement me
	}
	
	private static void addStudentToCourse() {
		//TODO Implement me
	}
	
	private static void addGrade() {
		//TODO Implement me
	}
	
	private static void getGradeReport() {
		int studentNnumber = -1;
		
		//Get Input
		while (studentNnumber < 0) {
			System.out.println("Enter Student N Number or (q) Exit: ");
			
			try {
				studentNnumber = in.nextInt();
			} catch (InputMismatchException e) {
				if(in.nextLine().equals("q")) {
					System.out.println("Exiting...");
					return;
				};
				System.out.println("Invalid Student Number");
				
			}
		}
		
		System.out.println("Getting grade report for student n" + studentNnumber);
		//TODO Implement me
		
		
		try {
			PreparedStatement statement = rdsConnection.prepareStatement("SELECT * FROM STUDENTS");
			ResultSet res = statement.executeQuery();
	
			ResultSetMetaData metadata = res.getMetaData();
			int columnCount = metadata.getColumnCount();
			
			// Print column names
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(metadata.getColumnName(i));
				if (i != columnCount) {
					System.out.print("| ");
				} else {
					System.out.println();
				}
			}
			
			// Print results
			while (res.next()) {
				String row = "";
				for (int i = 1; i <= columnCount; i++) {
					row += res.getString(i) + ", ";
				}
				System.out.println(row);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	private static void getDepartmentCourses() {
		int departmentCode;
		String departmentName;
		//TODO Implement me
	}
	
	private static void getInstructorCourseSections() {
		int instructorNnumber = -1;
		
		//Get Input
		while (instructorNnumber < 0) {
			System.out.println("Enter Instructor N Number or (q) Exit: ");
			
			try {
				instructorNnumber = in.nextInt();
			} catch (InputMismatchException e) {
				if(in.nextLine().equals("q")) {
					System.out.println("Exiting...");
					return;
				};
				System.out.println("Invalid Instructor Number");
				
			}
		}
		
		System.out.println("Getting course sections for instructor n" + instructorNnumber);
		//TODO Implement me
	}
	
	/**
	 * Gets database connection then allows user to fork into their desired method.
	 * All GET methods are stand-alone while ADD methods are further forked using the addData() method
	 * 
	 */
	public static void main(String[] args) {
		try {
			getRemoteDatabaseConnection();
			
			
			String userInput = "";
			while(!userInput.equals("q")) {
				System.out.println("Menu: (e) Enter Data | (s) Instructor Course Sections | (d) Department Courses | (g) Grade Report | (q) Quit");
				do {
					userInput = in.nextLine();				
				}
				while(userInput.equals(""));
				
				switch (userInput) {
					case "create":
						System.out.println("You better know what you're doing...");
						createTables();
						return;
					
					case "e":
						System.out.println("Entering Data");
						addData();
						break;
		
					case "s":
						System.out.println("Getting Instructor Course Section");
						getInstructorCourseSections();
						break;
						
					case "d":
						System.out.println("Getting Department Courses");
						getDepartmentCourses();
						break;
						
					case "g":
						System.out.println("Getting Student Grade Report");
						getGradeReport();
						break;
						
					case "q":
						System.out.println("Quitting...");
						break;
					
					default:
						System.out.println("Invalid Menu Input: " + userInput);
						break;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		in.close();
	}
}