package com.banking.main;

import com.banking.entity.User;
import com.banking.service.UserService;

import java.util.Scanner;

public class Main {
    //We are going to work on login facility.
    //For that we are going to take the credentials as user input
    private static Scanner input=new Scanner(System.in);
    static Main main=new Main();
    static UserService userService = new UserService();
    public static void main(String[] args) {
        while(true) {

            System.out.println("Enter the username");
            String username = input.next();
            System.out.println("Enter the password");
            String password = input.next();
            System.out.println("username: " + username + " password: " + password);
            //Validating the credentials
            User user = userService.login(username, password);

            if (user != null && user.getRole().equals("admin")) {
                System.out.println("Welcome " + username);
                main.initAdmin();
            } else if (user != null && user.getRole().equals("user")) {
                System.out.println("Welcome " + username);
                main.initCustomer();

            } else {
                System.out.println("Invalid username or password");
            }
        }
    }
    private void initAdmin(){

        boolean flag=true;
        while(flag) {
            System.out.println("1. Log-out");
            System.out.println("2. Create new user");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Admin logged out successfully");
                    flag = false;
                    break;
                case 2:
                    main.createUser();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    private void createUser(){
        System.out.println("Enter the username");
        String username = input.next();
        System.out.println("Enter the password");
        String password = input.next();
        System.out.println("Enter contact Number");
        String contact = input.next();

        boolean result= userService.createUser(username, password, contact);
        if(result){
            System.out.println("User created successfully");
        }else{
            System.out.println("User creation failed");
        }

    }
    private void initCustomer(){

        boolean flag=true;
        while(flag) {
            System.out.println("1. Log-out");
            System.out.println("2. view balance");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("customer logged out successfully");
                    flag = false;
                    break;
                case 2:
                    System.out.println("view balance successfully");;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}