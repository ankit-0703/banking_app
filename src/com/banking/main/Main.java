package com.banking.main;

import com.banking.entity.User;
import com.banking.service.UserService;

import java.util.Scanner;

public class Main {
                                                                                //We are going to work on login facility.
                                                                                //For that we are going to take the credentials as user input
    private static final Scanner input= new Scanner(System.in);
    //static as we are going to use it many times in entire program
    static Main main=new Main();
    static UserService userService = new UserService();
    public static void main(String[] args) {
        boolean keeprun=true;
        while(keeprun) {
            System.out.println("Enter the username");
            String username = input.next();
            System.out.println("Enter the password");
            String password = input.next();
            //System.out.println("username: " + username + " password: " + password);
            //Validating the credentials

            User user = userService.login(username, password);

            if (user != null && user.getRole().equals("admin")) {
                System.out.println("Welcome " + username);
                main.initAdmin();
            } else if (user != null && user.getRole().equals("user")) {
                System.out.println("Welcome " + username);
                main.initCustomer(user);

            } else {
                System.out.println("Invalid username or password");
            }
            System.out.println("Do you want to exit the program? [Y/N]");
            String answer = input.next();
            if(answer.equals("Y")) {
                keeprun=false;
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
    private void initCustomer(User user){

        boolean flag=true;
        while(flag) {
            System.out.println("1. Log-out");
            System.out.println("2. view balance");
            System.out.println("3. Fund transfer");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("customer logged out successfully");
                    flag = false;
                    break;
                case 2:
                    Double balance=main.checkBankBalance(user.getUsername());
                    if(balance!=null){
                        System.out.println("Your Balance is"+balance);
                    }else{
                        System.out.println("Your balance is empty");
                    }
                    break;
                case 3:
                    main.FundTransfer();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    private Double checkBankBalance(String userId){
        return userService.checkBankBalance(userId);
    }
    private void FundTransfer(){
        System.out.println("Enter the payee account ID");
        String payeeAccountId = input.next();
        User user = getUser(payeeAccountId);
        if(user!=null){
            System.out.println("valid user");
        }
        else{
            System.out.println("please enter valid user");
        }

    }
    private User getUser(String userId){
        return userService.getUser(userId);
    }
}