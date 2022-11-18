package project2;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
	Users should be able to perform the following tasks using the front-end:
	
	Add a student, department, course, instructor, and course section.
	Add students to a given course/section.
	Given a student's Nnumber generate their grade report. 
		Grade report should include student information, course/section, letter grade, grade point for each grade, and the grade point average (GPA) 
			(you can find how to calculate GPA here).
	Given a department name or code find the courses offered. 
	Given an instructor's Nnumber list all the course sections they have taught.
	Add a grade to a given student for a given course/section.
 */

/*
	Normalize the relations developed in Part 1 if applicable and update the related diagrams developed in Part 1 accordingly.
	Implement the back-end: Implement necessary tables based on your normalized relational schema. For each table created, you should include:
		name (be descriptive) of each attribute
		type of each attribute (consider the storage space implications of each choice)
		attribute constraints (e.g.,NOT NULL, uniqueness, default value)
		primary key
		foreign keys (all of them)
		referential integrity constraints
		CHECK clauses (if needed)
		short comment linking the table creation command to the entity name of your ER diagram (use#character to comment)
	Implement the front-end that can perform the tasks described above under requirements and interact with the back-end.The front-end can be a command-line interface or a graphical user interface (GUI). Extra credit will be given to developing a GUI. 
	The database system should be easy to navigate and use.
 */

public class ProjectInterface {
	static Scanner in = new Scanner(System.in);
	
	private static void createTables() {
		
	}

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
	
	public static void main(String[] args) {
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
		
		in.close();
	}
}