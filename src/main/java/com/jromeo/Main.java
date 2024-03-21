package com.jromeo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jromeo.dto.AuthDto;
import com.jromeo.http.AdminApi;
import com.jromeo.http.AuthApi;
import com.jromeo.utils.InputScanner;
import com.jromeo.utils.Menu;

public class Main {


    static AdminApi adminApi = new AdminApi();
    static AuthApi authApi = new AuthApi();

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
                break;
            case 2:
                // Perform action for Get All Students
                break;
            case 3:
                // Perform action for Get Course by ID
                break;
            case 4:
                // Perform action for Get All Courses
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
                adminApi.deleteUserAsAdmin(authApi.getJwtToken(),InputScanner.stringPut("Enter email of the user you want to delete: "));
                break;
            case 2:
                // Perform action for Convert User to Admin
                adminApi.promoteToAdmin(authApi.getJwtToken(), InputScanner.stringPut("Enter email of the user you want to promote to Admin: "));
                break;
            case 3:
                // Perform action for Update Student
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
                adminApi.addCourse(authApi.getJwtToken(), jsonCourse);

                break;
            case 6:
                // Perform action for Update Course
                break;
            case 7:
                // Perform action for Delete Course
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
                break;
            case 4:
                // Perform action for Assign Course to Student
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
