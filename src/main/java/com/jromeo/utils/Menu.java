package com.jromeo.utils;

public class Menu {
    public static void MainMenu() {
        oMainMenu();
    }
    public static void DisplayAuthMenu() {
        AuthMenu();
    }
    public static void DisplayPublicMenu() {
        PublicAccessMenu();
    }
    public static void DisplayAdminMenu() {
        AdminAccessMenu();
    }
    public static void DisplayUserMenu() {
        UserAccessMenu();
    }
    private static void oMainMenu() {
        System.out.println("Welcome to the Main menu!");
        System.out.println("1. Public Access Menu");
        System.out.println("2. Admin Access Menu");
        System.out.println("3. User Access Menu");
        System.out.println("0. Exit program!");
    }
    private static void PublicAccessMenu() {
        System.out.println("Welcome to Public Access Menu:");
        System.out.println("1. Get Student Details");
        System.out.println("2. Get All Students");
        System.out.println("3. Get Course by ID");
        System.out.println("4. Get All Courses");
        System.out.println("0. Exit");
    }

    private static void AdminAccessMenu() {
        System.out.println("Welcome to Admin Access Menu:");
        System.out.println("1. Delete User");
        System.out.println("2. Convert User to Admin");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Add Course");
        System.out.println("6. Update Course");
        System.out.println("7. Delete Course");
        System.out.println("0. Exit");
    }

    private static void UserAccessMenu() {
        System.out.println("Welcome to User Access Menu:");
        System.out.println("1. Update User");
        System.out.println("2. Change Password");
        System.out.println("3. Add Student");
        System.out.println("4. Assign Course to Student");
        System.out.println("0. Exit");
    }

    private static void AuthMenu() {
        System.out.println("Welcome to Authentication Menu:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
    }


}
