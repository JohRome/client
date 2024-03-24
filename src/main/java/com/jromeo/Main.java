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
    static UserApi userApi = new UserApi();
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
     * Convert User to Admin - måste ha url.
     * Update Student - funkar
     * Delete Student - funkar
     * Add Course - funkar
     * Update Course - funkar
     * Delete Course - funkar men kan inte ta bort om det finns kopplade studenter
     * Update User - - måste ha url
     * Change Password - måste ha url
     * Add Student - funkar
     * Assign Course to Student - funkar
     * <p>
     * Metoder som fungerar = 11
     * Metoder som ej provats = 4
     */

    static boolean isLoggedIn = false; // Initialize isLoggedIn as false initially

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Hello! Welcome to the program.");

        // Start an infinite loop to keep the program running
        while (true) {
            // Ask the user if they want to login/register
            String choice = InputScanner.stringPut("Would you like to login/register to access more features? (yes/no)").toLowerCase();

            // Display the appropriate menu based on the user's choice
            if (choice.equals("yes")) {
                handleLoginOrRegister();
            } else if (choice.equals("no")) {
                Menu.DisplayPublicMenu();
                handlePublicMenuChoice();
                // Break out of the loop if the user chooses not to login/register
                break;
            } else {
                System.out.println("Invalid choice.");
                continue; // Restart the loop to prompt the user again
            }

            // Once login/register is done, enter the main menu loop only if logged in
            if (isLoggedIn) {
                MainMenu();
                break;
            }
        }
    }


    public static void MainMenu() {
        while (true) {
            Menu.MainMenu();
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
                case 0:
                    System.out.println("Exiting program.");
                    System.exit(0); // Exit the program when choice is 0
                    break; // This break is not necessary, but added for clarity
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }


    /**
     * AUTHENTICATION MENU AND RELATED METHODS
     */
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
        isLoggedIn = authApi.login(loginCredentials); // Update isLoggedIn based on login result
        if (isLoggedIn) {
            MainMenu();
        }
    }

    private static void handleRegister(AuthDto loginCredentials) {
        authApi.register(loginCredentials);
        isLoggedIn = authApi.login(loginCredentials); // Update isLoggedIn based on login result
        if (isLoggedIn) {
            MainMenu();
        }
    }

    /**
     * PUBLIC ACCESS MENU AND RELATED METHODS
     */

    private static void handlePublicMenuChoice() {
        try {
            int option = InputScanner.intPut("Enter your choice: ");
            switch (option) {
                case 1:
                    // Perform action for Get Student Details
                    StudentDto student = studentApi.getStudent(authApi.getJwtToken(), InputScanner.intPut("Enter student ID: "));
                    System.out.println(student.toString());
                    break;
                case 2:
                    // Perform action for Get All Students
                    printAllStudents(authApi.getJwtToken());
                    break;
                case 3:
                    // Perform action for Get Course by ID
                    CourseDto course = courseApi.getCourseById(authApi.getJwtToken(), InputScanner.intPut("Enter course ID: "));
                    System.out.println(course);
                    break;
                case 4:
                    // Perform action for Get All Courses
                    printAllCourses(authApi.getJwtToken());
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            // Optionally, you can log the error or perform additional error handling here
        }
    }

    private static void printAllStudents(String jwt) {
        List<StudentDto> students = studentApi.getAllStudents(jwt);
        for (StudentDto studentDto : students) {
            System.out.println(studentDto);
        }
    }

    private static void printAllCourses(String jwt) {
        List<CourseDto> courses = courseApi.getAllCourses(jwt);
        for (CourseDto courseDto : courses) {
            System.out.println(courseDto);
        }
    }

    /**
     * ADMIN ACCESS MENU AND RELATED METHODS
     */

    private static void handleAdminMenuChoice() {
        try {

            int option = InputScanner.intPut("Enter your choice: ");
            switch (option) {
                case 1:
                    // Perform action for Delete User
                    // MÅSTE HITTA GET USERS FÖRST
                    adminApi.deleteUserAsAdmin(authApi.getJwtToken(), InputScanner.stringPut("Enter email of the user you want to delete: "));
                    break;
                case 2:
                    // Perform action for Convert User to Admin
                    adminApi.promoteToAdmin(authApi.getJwtToken(), InputScanner.stringPut("Enter email of the user you want to promote to Admin: "));
                    break;
                case 3:
                    // Perform action for Update Student
                    printAllStudents(authApi.getJwtToken());
                    int id = InputScanner.intPut("Enter ID of the Student you want to update: ");
                    Gson updateStudent = new Gson();

                    // Creating JSON object manually
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name", InputScanner.stringPut("Enter updated student name: "));
                    jsonObject.addProperty("age", InputScanner.intPut("Enter updated Student age: "));
                    jsonObject.addProperty("dept", InputScanner.stringPut("Enter updated student department: "));
                    String json = updateStudent.toJson(jsonObject);

                    studentApi.updateStudent(authApi.getJwtToken(), id, json);
                    break;
                case 4:
                    // Perform action for Delete Student
                    printAllStudents(authApi.getJwtToken());
                    studentApi.deleteStudent(authApi.getJwtToken(), InputScanner.intPut("Enter student ID: "));
                    break;
                case 5:
                    // Perform action for Add Course
                    Gson addCourse = new Gson();
                    JsonObject jsonObject1 = new JsonObject();
                    jsonObject1.addProperty("title", InputScanner.stringPut("Enter course title: "));
                    jsonObject1.addProperty("abbreviation", InputScanner.stringPut("Enter course abbreviation: "));
                    jsonObject1.addProperty("modules", InputScanner.stringPut("Enter course modules"));
                    jsonObject1.addProperty("fee", InputScanner.doublePut("Enter course fee: "));
                    String jsonCourse = addCourse.toJson(jsonObject1);
                    courseApi.addCourse(authApi.getJwtToken(), jsonCourse);

                    break;
                case 6:

                    printAllCourses(authApi.getJwtToken());
                    int courseId = InputScanner.intPut("Enter ID of the Course you want to update: ");
                    // Creating JSON object manually
                    Gson gson = new Gson();
                    JsonObject courseObject = new JsonObject();
                    courseObject.addProperty("title", InputScanner.stringPut("Enter updated course title: "));
                    courseObject.addProperty("abbreviation", InputScanner.stringPut("Enter updated Course abbreviation: "));
                    courseObject.addProperty("modules", InputScanner.intPut("Enter updated Course modules: "));
                    courseObject.addProperty("fee", InputScanner.doublePut("Enter updated Course fee: "));
                    String courseJson = gson.toJson(courseObject);

                    courseApi.updateCourse(authApi.getJwtToken(), courseId, courseJson);
                    break;
                case 7:
                    // Perform action for Delete Course;
                    printAllCourses(authApi.getJwtToken());
                    courseApi.deleteCourse(authApi.getJwtToken(), InputScanner.intPut("Enter course ID: "));
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void handleUserMenuChoice() {
        try {
            int option = InputScanner.intPut("Enter your choice: ");
            switch (option) {
                case 1:
                    // Perform action for Update User
                    Gson userUpdate = new Gson();
                    JsonObject jsonUser = new JsonObject();
                    jsonUser.addProperty("firstname", InputScanner.stringPut("Enter user name: "));
                    jsonUser.addProperty("lastname", InputScanner.stringPut("Enter user lastname: "));
                    jsonUser.addProperty("email", InputScanner.stringPut("Enter user email: "));
                    jsonUser.addProperty("password", InputScanner.stringPut("Enter password: "));
                    String NewJsonUser = userUpdate.toJson(jsonUser);
                    userApi.updateUser(authApi.getJwtToken(), InputScanner.stringPut("Enter email: "), NewJsonUser);
                    break;
                case 2:
                    Gson jsonUserPasswordUpdate = new Gson();
                    JsonObject changeUserPasswordJson = new JsonObject();
                    changeUserPasswordJson.addProperty("currentPassword", InputScanner.stringPut("Enter user name: "));
                    changeUserPasswordJson.addProperty("newPassword", InputScanner.stringPut("Enter user lastname: "));
                    changeUserPasswordJson.addProperty("confirmationPassword", InputScanner.stringPut("Enter user email: "));

                    String NewJsonUserPassword = jsonUserPasswordUpdate.toJson(changeUserPasswordJson);
                    userApi.changePassword(authApi.getJwtToken(), NewJsonUserPassword);
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
                    System.out.println("Which student do you want to assign a course to?");
                    printAllStudents(authApi.getJwtToken());
                    long studentId = InputScanner.intPut("Enter student ID: ");
                    System.out.println("Which course do you want to assign to the student?");
                    printAllCourses(authApi.getJwtToken());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}