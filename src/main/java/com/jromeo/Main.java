package com.jromeo;

import com.jromeo.dto.AuthDto;
import com.jromeo.http.AuthApi;
import com.jromeo.utils.InputScanner;
import com.jromeo.utils.Menu;

public class Main {
    static AuthApi authApi = new AuthApi();

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Hello! Welcome to the program.");

        // Ask the user if they want to login/register
        String choice = InputScanner.stringPut("Would you like to login/register to access more features? (yes/no)").toLowerCase();

        // Display the appropriate menu based on the user's choice
        if (choice.equals("yes")) {
            handleLoginOrRegister();
        } else if (choice.equals("no")) {
            Menu.DisplayPublicMenu();
            handlePublicMenuChoice();
        } else {
            System.out.println("Invalid choice. Exiting program.");
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
        boolean loggedIn = authApi.register(loginCredentials);
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
                break;
            case 2:
                // Perform action for Convert User to Admin
                break;
            case 3:
                // Perform action for Update Student
                break;
            case 4:
                // Perform action for Delete Student
                break;
            case 5:
                // Perform action for Add Course
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
