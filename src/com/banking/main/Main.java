package com.banking.main;

import com.banking.entity.User;
import com.banking.service.UserService;

import java.util.Map;
import java.util.Scanner;

public class Main {

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
        String UserId="";
        boolean flag=true;
        while(flag) {
            System.out.println("1. Log-out");
            System.out.println("2. Create new user");
            System.out.println("3. see all transactions");
            System.out.println("4. check balances");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Admin logged out successfully");
                    flag = false;
                    break;
                case 2:
                    main.createUser();
                    break;
                case 3:
                    System.out.println("Enter the customer ID");
                    UserId = input.next();
                    printTransaction(UserId);
                    break;
                case 4:
                    System.out.println("Enter the customer ID");
                    UserId = input.next();
                    Double result=checkBankBalance(UserId);
                    System.out.println("Bank balance for"+UserId+" = "+result);

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
            System.out.println("4. See all transaction");
            System.out.println("5. Raise Checkbook Request");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    flag = false;
                    System.out.println("customer logged out successfully");

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
                    main.FundTransfer(user);
                    break;
                case 4:
                    main.printTransaction(user.getUsername());
                    break;
                case 5:
                    String userId="";
                    Map<String,Boolean> map=getAllChequeBookRequest();
                    if(map.containsKey(userId) && map.get(userId)){
                        System.out.println("You have already raised this request and it is already approved");
                    } else if (map.containsKey(userId)&& !map.get(userId)) {
                        System.out.println("You already raised this request and it is under process of approval");
                    }
                    else{
                        raiseCheckBookRequest(user.getUsername());
                        System.out.println("Request raised successfully");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    private Map<String,Boolean> getAllChequeBookRequest(){
        return userService.getAllChequeBookRequest();
    }
    private void raiseCheckBookRequest(String userId){
        userService.raiseCheckBookRequest(userId);
    }

    private void printTransaction(String userId){
        userService.printTransaction(userId);
    }
    private Double checkBankBalance(String userId){
        return userService.checkBankBalance(userId);
    }

    private void FundTransfer(User userdetail){
        System.out.println("Enter the payee account ID");
        String payeeAccountId = input.next();
        User user = getUser(payeeAccountId);
        if(user!=null){
            System.out.println("Enter the amount to transfer");
            Double amount = input.nextDouble();
            //we will now check the given amount should be less than the persons current bank balance
            Double userAccountBalance=checkBankBalance(userdetail.getUsername());
            if(userAccountBalance>=amount){
                boolean result = userService.transferMoney(userdetail.getUsername(), payeeAccountId, amount);
                if(result){
                    System.out.println("Transfer successful");
                }else{
                    System.out.println("Transfer failed");
                }
            }else{
                System.out.println("Insufficient balance");
            }
        }
        else{
            System.out.println("please enter valid user");
        }

    }
    private User getUser(String userId){
        return userService.getUser(userId);
    }
}