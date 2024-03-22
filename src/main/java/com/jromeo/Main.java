package com.jromeo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jromeo.dto.AuthDto;
import com.jromeo.dto.CourseDto;
import com.jromeo.dto.StudentDto;
import com.jromeo.http.*;
import com.jromeo.utils.InputScanner;
import com.jromeo.utils.Menu;

import java.util.List;

public class Main {

    static HttpHelper httpHelper = new HttpHelper();
    static AdminApi adminApi = new AdminApi(httpHelper);
    static CourseApi courseApi = new CourseApi(httpHelper);
    static StudentApi studentApi = new StudentApi(httpHelper);
    static AuthApi authApi = new AuthApi();

    /**
     * RörigAB Dokumentation - Metoder vi har som fungerar
     * Register User - Funkar
     * Login again and update the token - funkar
     * Get student details - funkar
     * Get all students - funkar
     * Get course by ID - funkar
     * Get all courses - funkar
     * Delete User - ej provat
     * Convert User to Admin - ej provat
     * Update Student - funkar
     * Delete Student - funkar
     * Add Course - funkar
     * Update Course - funkar
     * Delete Course - funkar men kan inte ta bort om det finns kopplade studenter
     * Update User - ej provat - måste ha url
     * Change Password - ej provat
     * Add Student - funkar
     * Assign Course to Student - funkar
     *
     * Metoder som fungerar = 11
     * Metoder som ej provats = 4
     */

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Hello! Welcome to the program.");

        // Ask the user if they want to login/register
        String choice = InputScanner.stringPut("Would you like to login/register to access more features? (yes/no)").toLowerCase();

        // Display the appropriate menu based on the user's choice
        if (choice.equals("yes")) {
            handleLoginOrRegister();
            MainMenu();
        } else if (choice.equals("no")) {
            Menu.DisplayPublicMenu();
            handlePublicMenuChoice();
        } else {
            System.out.println("Invalid choice. Exiting program.");
        }
    }
    public static void MainMenu() {

        // Get user choice
        int choice = InputScanner.intPut("Enter your choice: ");
        switch (choice) {
            case 1:
                Menu.DisplayPublicMenu();
                handlePublicMenuChoice();
                break;
            case 2:
                Menu.DisplayAdminMenu();
                handleAdminMenuChoice();
                break;
            case 3:
                Menu.DisplayUserMenu();
                handleUserMenuChoice();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    private static void handleLoginOrRegister() {
        int switchChoice = InputScanner.intPut("Enter 1 to login, Enter 2 to register (and login with the registered account)");
        AuthDto loginCredentials = new AuthDto();
        loginCredentials.setEmail(InputScanner.stringPut("Enter your email: "));
        loginCredentials.setPassword(InputScanner.stringPut("Enter your password: "));
        switch (switchChoice) {
            case 1:
                handleLogin(loginCredentials);
                break;
            case 2:
                handleRegister(loginCredentials);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void handleLogin(AuthDto loginCredentials) {
        boolean loggedIn = authApi.login(loginCredentials);
        if (loggedIn) {
            Menu.MainMenu();
        }
    }

    private static void handleRegister(AuthDto loginCredentials) {
         authApi.register(loginCredentials);
         boolean loggedIn = authApi.login(loginCredentials);
        if (loggedIn) {
            Menu.MainMenu();
        }
    }

    private static void handlePublicMenuChoice() {
        int option = InputScanner.intPut("Enter your choice: ");
        switch (option) {
            case 1:
                // Perform action for Get Student Details
                StudentDto student = adminApi.getStudent(authApi.getJwtToken(), InputScanner.intPut("Enter student ID: "));
                System.out.println(student.toString());
                break;
            case 2:
                // Perform action for Get All Students
                List<StudentDto> students = adminApi.getAllStudents(authApi.getJwtToken());
                for (StudentDto studentDto : students) {
                    System.out.println(studentDto);
                }
                break;
            case 3:
                // Perform action for Get Course by ID
                CourseDto course = courseApi.getCourseById(authApi.getJwtToken(), InputScanner.intPut("Enter course ID: "));
                System.out.println(course);
                break;
            case 4:
                // Perform action for Get All Courses
                List<CourseDto> courses = courseApi.getAllCourses(authApi.getJwtToken());
                for (CourseDto courseDto : courses) {
                    System.out.println(courseDto);
                }
                break;
            case 0:
                System.out.println("Exiting program.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void handleAdminMenuChoice() {
        int option = InputScanner.intPut("Enter your choice: ");
        switch (option) {
            case 1:
                // Perform action for Delete User
                // MÅSTE HITTA GET USERS FÖRST
                adminApi.deleteUserAsAdmin(authApi.getJwtToken(),InputScanner.stringPut("Enter email of the user you want to delete: "));
                break;
            case 2:
                // Perform action for Convert User to Admin
                adminApi.promoteToAdmin(authApi.getJwtToken(), InputScanner.stringPut("Enter email of the user you want to promote to Admin: "));
                break;
            case 3:
                // Perform action for Update Student
                List<StudentDto> students = adminApi.getAllStudents(authApi.getJwtToken());
                for (StudentDto studentDto : students) {
                    System.out.println(studentDto);
                }
                int id = InputScanner.intPut("Enter ID of the Student you want to update: ");
                Gson updateStudent = new Gson();

                // Creating JSON object manually
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name",InputScanner.stringPut("Enter updated student name: ") );
                jsonObject.addProperty("age", InputScanner.intPut("Enter updated Student age: "));
                jsonObject.addProperty("dept", InputScanner.stringPut("Enter updated student department: "));
                String json = updateStudent.toJson(jsonObject);

                adminApi.updateStudent(authApi.getJwtToken(), id , json);
                break;
            case 4:
                // Perform action for Delete Student
                adminApi.deleteStudent(authApi.getJwtToken(), InputScanner.intPut("Enter student ID: "));
                break;
            case 5:
                // Perform action for Add Course
                Gson addCourse = new Gson();
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("title",InputScanner.stringPut("Enter course title: ") );
                jsonObject1.addProperty("abbreviation", InputScanner.stringPut("Enter course abbreviation: "));
                jsonObject1.addProperty("modules",InputScanner.stringPut("Enter course modules"));
                jsonObject1.addProperty("fee",InputScanner.doublePut("Enter course fee: "));
                String jsonCourse = addCourse.toJson(jsonObject1);
                courseApi.addCourse(authApi.getJwtToken(), jsonCourse);

                break;
            case 6:
                // Perform action for Update Course
                List<CourseDto> courses = courseApi.getAllCourses(authApi.getJwtToken());
                for (CourseDto courseDto : courses) {
                    System.out.println(courseDto);
                }
                int courseId = InputScanner.intPut("Enter ID of the Course you want to update: ");
                // Creating JSON object manually
                Gson gson = new Gson();
                JsonObject courseObject = new JsonObject();
                courseObject.addProperty("title",InputScanner.stringPut("Enter updated course title: ") );
                courseObject.addProperty("abbreviation", InputScanner.stringPut("Enter updated Course abbreviation: "));
                courseObject.addProperty("modules", InputScanner.intPut("Enter updated Course modules: "));
                courseObject.addProperty("fee", InputScanner.doublePut("Enter updated Course fee: "));
                String courseJson = gson.toJson(courseObject);

                courseApi.updateCourse(authApi.getJwtToken(), courseId , courseJson);
                break;
            case 7:
                // Perform action for Delete Course;
                courseApi.deleteCourse(authApi.getJwtToken(), InputScanner.intPut("Enter course ID: "));
                break;
            case 0:
                System.out.println("Exiting program.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void handleUserMenuChoice() {
        int option = InputScanner.intPut("Enter your choice: ");
        switch (option) {
            case 1:
                // Perform action for Update User
                break;
            case 2:
                // Perform action for Change Password
                break;
            case 3:
                // Perform action for Add Student
                Gson student = new Gson();
                System.out.println("Add new Student");
                JsonObject studentJson = new JsonObject();
                studentJson.addProperty("name", InputScanner.stringPut("Enter student name: "));
                studentJson.addProperty("age", InputScanner.intPut("Enter student age: "));
                studentJson.addProperty("dept", InputScanner.stringPut("Enter student department: "));
                String studentJ = student.toJson(studentJson);
                studentApi.addStudent(authApi.getJwtToken(), studentJ);
                break;
            case 4:
                // Perform action for Assign Course to Student
                System.out.println("Which student do you want to assign a course to?");
                List<StudentDto> students = adminApi.getAllStudents(authApi.getJwtToken());
                for (StudentDto studentDto : students) {
                    System.out.println(studentDto);
                }
                long studentId = InputScanner.intPut("Enter student ID: ");
                System.out.println("Which course do you want to assign to the student?");
                List<CourseDto> courses = courseApi.getAllCourses(authApi.getJwtToken());
                for (CourseDto courseDto : courses) {
                    System.out.println(courseDto);
                }
                long courseId = InputScanner.intPut("Enter course ID: ");
                studentApi.assignCourseToStudent(authApi.getJwtToken(), studentId, courseId);
                break;
            case 0:
                System.out.println("Exiting program.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}
