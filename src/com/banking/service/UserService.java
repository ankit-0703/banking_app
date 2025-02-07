package com.banking.service;

import com.banking.entity.User;
import com.banking.repository.UserRepository;

import java.util.Map;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public void printUsers(){
        userRepository.printUsers();
    }
    public User login(String username, String password){
        return userRepository.login(username, password);
    }
    public boolean createUser(String username, String password, String contact){
       return userRepository.createUser(username, password, contact);
    }
    public Double checkBankBalance(String userId){
        return userRepository.checkBankBalance(userId);
    }
    public User getUser(String userId){
        return userRepository.getUser(userId);
    }
    public boolean transferMoney(String UserId, String payeeUserId, Double amount){
        return userRepository.transferMoney(UserId,payeeUserId,amount);
    }

    public void printTransaction(String userId){
        userRepository.printTransaction(userId);
    }
    public void raiseCheckBookRequest(String userId){
        userRepository.raiseCheckBookRequest(userId);
    }
    public Map<String,Boolean> getAllChequeBookRequest(){
        return userRepository.getAllChequeBookRequest();
    }
}
