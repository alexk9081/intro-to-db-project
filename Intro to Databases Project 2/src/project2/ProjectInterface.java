package project2;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * 
 * @author Alex, Juan, and Richfield
 *
 */
public class ProjectInterface extends JFrame {

    static Scanner in = new Scanner(System.in);
    static Connection rdsConnection;

    /**
     * Gets username and password from user and attempts to connect to the UNF
     * oracle sql server.
     * On success, the connection to the database is stored in the rdsConnection
     * variable which can be used across the class
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

        /* Connection */ rdsConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@" + hostname + ":" + port + ":orcl",
                userName, password);
    }

    /**
     * Developer method to initialize databases
     */
    private static void createTables() {
        // method used to ask user if they want to create table.
        try (
                Statement stmt = rdsConnection.createStatement();) {

            System.out.print("enter new database table");
            String sql = getString();
            /*
             * example of how input should be
             * CREATE TABLE REGISTRATION " +
             * "(id INTEGER not NULL, " +
             * " first VARCHAR(255), " +
             * " last VARCHAR(255), " +
             * " age INTEGER, " +
             * " PRIMARY KEY ( id ))";
             */

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Forks user off into the specific add data methods based on user input
     * 
     * @throws SQLException
     */
    private static void addData() throws SQLException {
        String userInput = "";

        while (!userInput.equals("q")) {
            System.out.println(
                    "Add data: (st) Student | (d) Department | (i) Instructor | (c) Course | (se) Section | (sc) Student Courses | (g) Grade | (q) Exit");
            do {
                userInput = in.nextLine();
            } while (userInput.equals(""));

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

        // TODO Implement me
    }

    // get string method re-usable
    public static String getString() {
        try {
            StringBuffer buffer = new StringBuffer();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }

    // get int method re-useable
    public static int getInt()

    {
        String s = getString();
        return Integer.parseInt(s);
    }

    private static void addStudent() throws SQLException {
        PreparedStatement pstmt = rdsConnection.prepareStatement(
                "INSERT INTO STUDENT(n_number, degree, sex, birthdate, ssn, current_street, current_city, current_state, current_zip, current_phone, first_name, middle_initial, last_name, student_class, permanent_street, permanent_city, permanent_state, permanent_zip, permanent_phone, minor, major) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        int done = 1;

        while (done != 0) {
            System.out.println("\nEnter n_number: ");
            int n_number = getInt();
            System.out.println("\nEnter degree: ");
            String degree = getString();
            System.out.println("\nEnter sex: ");
            String sex = getString();
            System.out.println("\nEnter birth date: ");
            int birthdate = getInt();
            System.out.println("\nEnter ssn: ");
            int ssn = getInt();
            System.out.println("\nEnter current street: ");
            String current_street = getString();
            System.out.println("\nEnter current city: ");
            String current_city = getString();
            // --------------------------------------
            System.out.println("\nEnter current state: ");
            String current_state = getString();
            System.out.println("\nEnter current zip code ");
            int current_zip = getInt();
            System.out.println("\nEnter current phone number: ");
            int current_phone = getInt();
            // -------------------------------------------
            System.out.println("\nEnter first name: ");
            String first_name = getString();
            System.out.println("\nEnter middle initial: ");
            String middle_initial = getString();
            System.out.println("\nEnter last name: ");
            String last_name = getString();
            System.out.println("\nEnter student's class: ");
            String student_class = getString();
            // ---------------------------------------------
            System.out.println("\nEnter permanent street: ");
            String permanent_street = getString();
            System.out.println("\nEnter permanent city ");
            String permanent_city = getString();
            System.out.println("\nEnter permanent state: ");
            String permanent_state = getString();
            System.out.println("\nEnter permanent zip code: ");
            int permanent_zip = getInt();
            System.out.println("\nEnter permanent phone number ");
            int permanent_phone = getInt();
            System.out.println("\nEnter student's minor: ");
            String minor = getString();
            System.out.println("\nEnter student's major: ");
            String major = getString();

            pstmt.setInt(1, n_number);
            pstmt.setString(2, degree);
            pstmt.setString(3, sex);
            pstmt.setInt(4, birthdate);
            pstmt.setInt(5, ssn);
            pstmt.setString(6, current_street);
            pstmt.setString(7, current_city);
            pstmt.setString(8, current_state);
            pstmt.setInt(9, current_zip);
            pstmt.setInt(10, current_phone);
            pstmt.setString(11, first_name);
            pstmt.setString(12, middle_initial);
            pstmt.setString(13, last_name);
            pstmt.setString(14, student_class);
            // ------------------------------------------------
            pstmt.setString(15, permanent_street);
            pstmt.setString(16, permanent_city);
            pstmt.setString(17, permanent_state);
            pstmt.setInt(18, permanent_zip);
            pstmt.setInt(19, permanent_phone);
            pstmt.setString(20, minor);
            pstmt.setString(21, major);

            int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");

            System.out.println("\nHit 0 for exit, " +
                    "or enter any other number for another insert: ");
            done = getInt();
        } // while done
          // rdsConnection.close();
    }

    private static void addDepartment() throws SQLException {
        PreparedStatement pstmt = rdsConnection.prepareStatement(
                "INSERT INTO DEPARTMENT(code, department_name, college, office_number, office_phone) " +
                        "VALUES (?, ?, ?, ?, ?)");
        int done = 1;

        while (done != 0) {
            System.out.println("\nEnter code: ");
            String code = getString();
            System.out.println("\nEnter department name: ");
            String department_name = getString();
            System.out.println("\nEnter college: ");
            String college = getString();
            System.out.println("\nEnter office number: ");
            int office_number = getInt();
            System.out.println("\nEnter office phone: ");
            int office_phone = getInt();

            pstmt.setString(1, code);
            pstmt.setString(2, department_name);
            pstmt.setString(3, college);
            pstmt.setInt(4, office_number);
            pstmt.setInt(5, office_phone);
            int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");

            System.out.println("\nHit 0 for exit, " +
                    "or enter any other number for another insert: ");
            done = getInt();
        } // while done
          // rdsConnection.close();
    }

    private static void addInstructor() throws SQLException {
        PreparedStatement pstmt = rdsConnection.prepareStatement(
                "INSERT INTO INSTRUCTOR(n_number, ssn, instructor_name, age, personal_phone, office_phone, address, department) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        int done = 1;

        while (done != 0) {
            System.out.println("\nEnter n-number: ");
            int n_number = getInt();
            System.out.println("\nEnter ssn: ");
            int ssn = getInt();
            System.out.println("\nEnter name: ");
            String instructor_name = getString();
            System.out.println("\nEnter age: ");
            int age = getInt();
            System.out.println("\nEnter personal phone number: ");
            int personal_phone = getInt();
            System.out.println("\nEnter office phone number: ");
            int office_phone = getInt();
            System.out.println("\nEnter address: ");
            String address = getString();
            System.out.println("\nEnter department: ");
            String department = getString();

            pstmt.setInt(1, n_number);
            pstmt.setInt(2, ssn);
            pstmt.setString(3, instructor_name);
            pstmt.setInt(4, age);
            pstmt.setInt(5, personal_phone);
            pstmt.setInt(6, office_phone);
            pstmt.setString(7, address);
            pstmt.setString(8, department);

            int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");

            System.out.println("\nHit 0 for exit, " +
                    "or enter any other number for another insert: ");
            done = getInt();
        } // while done
          // rdsConnection.close();
    }

    private static void addCourse() throws SQLException {

        PreparedStatement pstmt = rdsConnection.prepareStatement(
                "INSERT INTO COURSE(course_number, description, course_level, course_name, semester_hours, department) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?)");
        int done = 1;

        while (done != 0) {
            System.out.println("\nEnter course number: ");
            String course_number = getString();
            System.out.println("\nEnter the description: ");
            String description = getString();
            System.out.println("\nEnter course level: ");
            int course_level = getInt();
            System.out.println("\nEnter course name: ");
            String course_name = getString();
            System.out.println("\nEnter semester hours: ");
            int semester_hours = getInt();
            System.out.println("\nEnter department: ");
            String department = getString();

            pstmt.setString(1, course_number);
            pstmt.setString(2, description);
            pstmt.setInt(3, course_level);
            pstmt.setString(4, course_name);
            pstmt.setInt(5, semester_hours);
            pstmt.setString(6, department);
            int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");

            System.out.println("\nHit 0 for exit, " +
                    "or enter any other number for another insert: ");
            done = getInt();
        } // while done
          // rdsConnection.close();
    }

    private static void addSection() throws SQLException {
        PreparedStatement pstmt = rdsConnection
                .prepareStatement("INSERT INTO SECTION(section_number, course, section_year, semester, instructor) " +
                        "VALUES (?, ?, ?, ?, ?)");
        int done = 1;

        while (done != 0) {
            System.out.println("\nEnter section number: ");
            int section_number = getInt();
            System.out.println("\nEnter course: ");
            String course = getString();
            System.out.println("\nEnter year: ");
            int section_year = getInt();
            System.out.println("\nEnter semester: ");
            String semester = getString();
            System.out.println("\nEnter instructer number: ");
            int instructer = getInt();

            pstmt.setInt(1, section_number);
            pstmt.setString(2, course);
            pstmt.setInt(3, section_year);
            pstmt.setString(4, semester);
            pstmt.setInt(5, instructer);

            int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");

            System.out.println("\nHit 0 for exit, " +
                    "or enter any other number for another insert: ");
            done = getInt();
        } // while done
          // rdsConnection.close();

    }

    private static void addStudentToCourse() throws SQLException {
        PreparedStatement pstmt = rdsConnection
                .prepareStatement("INSERT INTO ENROLLED_IN(student, course, section, section_year, semester, grade) " +
                        "VALUES (?, ?, ?, ?, ?, ?)");
        int done = 1;

        while (done != 0) {
            System.out.println("\nEnter student: ");
            int student = getInt();
            System.out.println("\nEnter course: ");
            String course = getString();
            System.out.println("\nEnter section: ");
            int section = getInt();
            System.out.println("\nEnter year: ");
            int section_year = getInt();
            System.out.println("\nEnter semester: ");
            String semester = getString();
            System.out.println("\nEnter grade: ");
            String grade = getString();

            pstmt.setInt(1, student);
            pstmt.setString(2, course);
            pstmt.setInt(3, section_year);
            pstmt.setInt(4, section);
            pstmt.setString(5, semester);
            pstmt.setString(6, grade);

            int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");

            System.out.println("\nHit 0 for exit, " +
                    "or enter any other number for another insert: ");
            done = getInt();
        } // while done
          // rdsConnection.close();

    }

    private static void addGrade() {
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        int grade;
        String course;
        int section;
        int student;

        int done = 1;
        // read in code
        while (done != 0) {
                System.out.print("Enter student number: ");
                student = getInt();
                System.out.print("Enter grade: ");
                grade = getInt();
                System.out.print("Enter course: ");
                course = getString();
                System.out.print("Enter section: ");
                section = getInt();
                String q = "update ENROLLED_IN " + 
                            "set grade = " + grade +
                            "where course = " + course + " or section = " + section + " and student = " + student;

        PreparedStatement pstmt = rdsConnection.prepareStatement(q);

        int NumRows = pstmt.executeUpdate();
            System.out.println("\n" + NumRows + " row(s) inserted");


            ResultSet rset = pstmt.executeQuery();

            System.out.println("\nHit 0 for exit, " +
            "or enter any other number for another insert: ");
                done = getInt();
            } 
            
            System.out.println("\n");

    }

    private static void getGradeReport() {
        int studentNnumber = -1;

        // Get Input
        while (studentNnumber < 0) {
            System.out.println("Enter Student N Number or (q) Exit: ");

            try {
                studentNnumber = in.nextInt();
            } catch (InputMismatchException e) {
                if (in.nextLine().equals("q")) {
                    System.out.println("Exiting...");
                    return;
                }
                ;
                System.out.println("Invalid Student Number");

            }
        }

        System.out.println("Getting grade report for student n" + studentNnumber);
        // TODO Implement me

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
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    private static void getDepartmentCourses() throws SQLException {
        int departmentCode;
        String departmentName;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        String q = "select c.course_name, c.course_number " +
                "from course c " +
                "inner join department d " +
                "on c.department = d.code " +
                "where d.code = ?";

        PreparedStatement pstmt = rdsConnection.prepareStatement(q);

        System.out.println("\nEnter department code, \nThen course name " +
                "and course number " +
                "will be displayed\n");

        String c = "";

        // read in code
        while (c != "q") {
            System.out.print("Code (enter q for exit): ");
            c = getString();
            if (c.equals("q")){
                System.out.println("Exiting...");
                return;
            };

            pstmt.setString(1, c);

            ResultSet rset = pstmt.executeQuery();

            System.out.println("\n");

            // Iterate through the result and print the employee names
            while (rset.next()) {
                String coursename = rset.getString("course_name");
                String coursenumber = rset.getString("course_Number");
                System.out.println(coursename + ":" + coursenumber);
            } // while rset
        }
        System.out.println("\n");
        // while c

    }

    private static void getInstructorCourseSections() throws SQLException {
        int instructorNnumber = -1;

        // Get Input
        while (instructorNnumber < 0) {
            System.out.println("Enter Instructor N Number or (q) Exit: ");
            try {
                instructorNnumber = in.nextInt();
            } catch (InputMismatchException e) {
                if (in.nextLine().equals("q")) {
                    System.out.println("Exiting...");
                    return;
                }
                ;
                System.out.println("Invalid Instructor Number");

            }
        }

        System.out.println("Getting course sections for instructor n" + instructorNnumber);
        // TODO Implement me
        while (!in.nextLine().equals("q")) {
            String q = "select * from s.SECTION " +
                    "inner join i.INSTRUCTOR " +
                    "on s.instructor = i.n_number " +
                    "where i.n_number = " + instructorNnumber;
            Statement stmt = rdsConnection.createStatement();
            ResultSet rset = stmt.executeQuery(q);

            System.out.println("\n");

            while (rset.next()) {
                int section = rset.getInt("section_number");
                String course = rset.getString("course");
                int section_year = rset.getInt("section_year");
                String semester = rset.getString("semester");
                int instructor = rset.getInt("instructor");
                System.out.println(section + ":" + course + ":" +
                        section_year + ":" + semester + ":" + instructor);
            } // while rset

            System.out.println("\n");
        }
    }

    /**
     * Gets database connection then allows user to fork into their desired method.
     * All GET methods are stand-alone while ADD methods are further forked using
     * the addData() method
     * 
     */
    public static void main(String[] args) {
        // create gui window
        /*
         * ProjectInterface window = new ProjectInterface();
         * 
         * window.setSize(500, 500);
         * window.setVisible(true);
         * window.setTitle("UNF");
         * // add swing obj here
         * window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         */

        try {
            getRemoteDatabaseConnection();

            String userInput = "";
            while (!userInput.equals("q")) {
                System.out.println(
                        "Menu: (e) Enter Data | (s) Instructor Course Sections | (d) Department Courses | (g) Grade Report | (q) Quit");
                do {
                    userInput = in.nextLine();
                } while (userInput.equals(""));

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        in.close();
    }
}
